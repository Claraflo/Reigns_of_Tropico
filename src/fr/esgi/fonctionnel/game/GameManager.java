package fr.esgi.fonctionnel.game;

import java.util.*;


public class GameManager {

    private static GameManager currentGame;
    private Island currentIsland;
    private HashMap<Level,ArrayList<Integer>> matchLevelAndStatIsland;

    public static GameManager getInstance(){
        if (currentGame == null) {
            currentGame = new GameManager();
        }
        return currentGame;
    }

    private GameManager(){
        matchLevelAndStatIsland = new HashMap<>();
        matchLevelAndStatIsland.put(Level.EASY,new ArrayList<>(Arrays.asList(200,15,15,10)));
        matchLevelAndStatIsland.put(Level.MEDIUM,new ArrayList<>(Arrays.asList(150,15,15,30)));
        matchLevelAndStatIsland.put(Level.HARD,new ArrayList<>(Arrays.asList(100,15,15,50)));
    }

    public  boolean initNewGame(Level level) {

        if(matchLevelAndStatIsland.get(level)!=null){
            currentIsland = new Island(level,matchLevelAndStatIsland.get(level));
            return true;
        }

        return false;

    }

    public void launchEvents(){

        EventManager eventManager = new EventManager();
        Scanner scanner = new Scanner(System.in);

        int input=0;
        boolean linked = false;

        //Condition à changer -> tant que condition d'arret non atteinte.
        while(eventManager.getEventsToPlay().size()>0) {

            if(linked){
                eventManager.setCurrentEvent(eventManager.getLinkedEvent(input));
            }else{
                eventManager.setCurrentEvent(eventManager.getEventToPlay());
            }

            System.out.println(eventManager.getCurrentEvent().getEvent());
            for (Map.Entry<Integer,Choice> entry : eventManager.getCurrentEvent().getChoices().entrySet()) {
                System.out.println(entry.getKey()+". "+entry.getValue().getChoice());
            }

            System.out.println("Que doit-on faire général? ");

            input = scanner.nextInt();
            eventManager.applyActionChoice(currentIsland,input);
            linked = eventManager.CheckLinkedEventToChoice(input);

            System.out.println(currentIsland.toString());;
        }
    }



}
