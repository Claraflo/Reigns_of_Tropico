package com.tropico.game;

import com.tropico.graphicUI.DatasUpdateManager;
import com.tropico.graphicUI.Models.FactionPanel;
import com.tropico.graphicUI.Models.MainSceneModel;
import com.tropico.graphicUI.Models.PieChartPanel;
import com.tropico.graphicUI.Models.QuestionPanel;
import com.tropico.graphicUI.SceneManager;
import com.tropico.graphicUI.Utils.Constants;


import java.util.*;

/**
 * Class GameManager
 *
 * Orchestrator of game
 * **/

public class GameManager {

    private static GameManager currentGame;
    private Island currentIsland;
    private Level level;
    private int countSeason;
    private int countRound;
    private HashMap<Level, ArrayList<Integer>> matchLevelAndStatIsland;
    private DatasUpdateManager dataManager = DatasUpdateManager.getInstance();
    private MainSceneModel model;
    private EventManager eventManager;
    private boolean linked;
    private Event eventLinked;

    public static GameManager getInstance() {
        if (currentGame == null) {
            currentGame = new GameManager();
        }
        return currentGame;
    }

    private GameManager() {
        countSeason = 0;
        countRound = 0;
        eventLinked = new Event();
        matchLevelAndStatIsland = new HashMap<>();
        matchLevelAndStatIsland.put(Level.EASY, new ArrayList<>(Arrays.asList(200, 15, 15, 10)));
        matchLevelAndStatIsland.put(Level.MEDIUM, new ArrayList<>(Arrays.asList(150, 15, 15, 30)));
        matchLevelAndStatIsland.put(Level.HARD, new ArrayList<>(Arrays.asList(100, 15, 15, 50)));
        linked = false;
        eventManager = new EventManager();
    }

    public boolean initNewGame(Level level) {

        this.level = level;
        if (matchLevelAndStatIsland.get(level) != null) {
            currentIsland = new Island(level, matchLevelAndStatIsland.get(level));
            linked = false;
            return true;
        }

        return false;

    }

    public void launchEvents() {

        countRound += 1;
        if (linked) {
            eventManager.setCurrentEvent(eventLinked);
        } else {
            eventManager.setCurrentEvent(eventManager.getEventToPlay());
        }


        if (dataManager.getInstance().isIsNewResponse()) {
            eventManager.applyActionChoice(currentIsland, dataManager.getInstance().getCurrentResponseIndex());
            linked = eventManager.CheckLinkedEventToChoice(dataManager.getInstance().getCurrentResponseIndex());
            eventLinked = eventManager.events.get(eventManager.getCurrentEvent().getChoices().get(dataManager.getInstance().getCurrentResponseIndex()).getLikedEventToChoice());
            dataManager.getInstance().setIsNewResponse(false);
        }
        countSeason += 1;
        if (countSeason == 4) {
            checkEndSeason();
        }
        eventManager.checkCapacityEventToPlay();
        model = fillObjectInfo(eventManager.getCurrentEvent(), currentIsland);
        dataManager.UpdateDatas(SceneManager.getInstance(null).getScene(), model);
        checkIfEndGame();
    }

    private void checkEndSeason() {
        countSeason = 0;
    }

    public boolean checkIfEndGame() {

        currentIsland.updateGlobalSatisfaction();

        if (currentIsland.getGlobalSatisfaction() < currentIsland.getConstraint().getThreshold()) {
            return true;
        }
        return false;
    }


    private MainSceneModel fillObjectInfo(Event event, Island island) {

        var model = new MainSceneModel();
        model.questionPanel = new QuestionPanel();
        model.questionPanel.question = event.getEvent();

        for (Map.Entry<Integer, Choice> entry : event.getChoices().entrySet()) {
            model.questionPanel.responses.add(entry.getValue().getChoice());
        }
        System.out.println();
        model.fractionPanel = new FactionPanel();

        var factionInfo = new HashMap<String, Double>();
        for (int i = 0; i < Constants.FACTIONS_NAMES.length; i++) {
            factionInfo.put(Constants.FACTIONS_NAMES[i].toString(), (double) island.getFactions().get(Constants.FACTIONS_NAMES[i]).getSatisfaction());
        }

        model.fractionPanel.setFractions(factionInfo);

        model.pieChartPanel = new PieChartPanel();
        model.pieChartPanel.agriculture = island.getAgriculture();
        model.pieChartPanel.industry = island.getIndustry();

        model.money = island.getTreasure();
        model.population = island.getTotalPopulation();
        island.updateGlobalSatisfaction();
        model.satisfaction = island.getGlobalSatisfaction();

        model.round = countRound;
        model.season = countSeason;
        return model;
    }
}
