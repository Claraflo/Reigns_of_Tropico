package com.tropico.graphicUI;

import java.util.ArrayList;

import com.tropico.game.GameManager;
import com.tropico.game.Level;
import com.tropico.graphicUI.Models.*;
import com.tropico.graphicUI.Utils.Constants;

import javafx.geometry.Insets;
import javafx.geometry.Side;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public final class DatasUpdateManager {

	private static DatasUpdateManager _instance;
	private static int currentResponseIndex;
	private static boolean isNewResponse;
	private static MainSceneModel model;


	private DatasUpdateManager() {
		isNewResponse = false;
	}
	
    public static DatasUpdateManager getInstance() {
        if(_instance == null) {
        	_instance = new DatasUpdateManager();
        }
        
        return _instance;
    }
    
	public void UpdateDatas(Scene scene, MainSceneModel model) 
	{
		updateQuestionPanel(scene, model.questionPanel);
		updateFactionPanel(scene, model.fractionPanel);
		updatePieChart(scene, model.pieChartPanel);
		updateInfos(scene, model);
	}

	private void updateInfos(Scene scene, MainSceneModel model) {
	    
	    Text moneyValue = (Text) scene.lookup("#" + Constants.MONEY_VALUE_ID);
	    moneyValue.setText(model.getMoney() + "$ ");
	    Text satisValue = (Text) scene.lookup("#" + Constants.SATISFACTION_VALUE_ID);
	    satisValue.setText(model.getSatisfaction() + "% ");
	    Text populationValue = (Text) scene.lookup("#" + Constants.POPULATION_VALUE_ID);
	    populationValue.setText(Integer.toString(model.getPopulation()));
	    
	    Text infos = (Text) scene.lookup("#" + Constants.INFO_LABEL_ID);

	    infos.setText("Tours: "+ model.round +"  Saison: "+ model.season +"/4");

	}

	private void updatePieChart(Scene scene, PieChartPanel pieChartPanel) {
		HBox pieChartBox = (HBox) scene.lookup("#" + Constants.PIECHART_PANEL_ID);
	    pieChartBox.getChildren().clear();
	    
        PieChart pieChart = new PieChart();
        PieChart.Data slice1 = new PieChart.Data("Agriculture", pieChartPanel.agriculture);
        PieChart.Data slice2 = new PieChart.Data("Industrie", pieChartPanel.industry);
        PieChart.Data slice3 = new PieChart.Data("Non occupé", 100 - pieChartPanel.agriculture - pieChartPanel.industry);
        
        pieChart.getData().add(slice1);
        pieChart.getData().add(slice2);
        pieChart.getData().add(slice3);

        pieChart.setLegendSide(Side.LEFT);
        pieChartBox.getChildren().add(pieChart);
	}

	private void updateQuestionPanel(Scene scene, QuestionPanel model) {

		// find HBOx in the scene
		VBox questionPanel = (VBox) scene.lookup("#" + Constants.QUESTION_PANEL_ID);

		//remove all previous question
		questionPanel.getChildren().clear();
		
		Text question = new Text(model.question);
		question.setFont(Font.font("Arial", FontWeight.BOLD, 16));
		    
		questionPanel.getChildren().add(question);
		
		// Display question and choice
		Hyperlink options[] = CreateResponseAction( model.responses);
		for (int i=0; i<model.responses.size() ; i++) {
			questionPanel.setMargin(options[i], new Insets(0, 0, 0, 8));
			questionPanel.getChildren().add(options[i]);
	    }
	}
	
	private void updateFactionPanel(Scene scene, FactionPanel model)
	{
		for (int i = 0; i < Constants.FACTIONS_NAMES.length; i++) {
			ProgressBar pb = (ProgressBar) scene.lookup("#" + Constants.FACTIONS_NAMES[i].toString() + Constants.PROGRESSBAR_SUFIX_ID);
			pb.setProgress(model.getFractions().get(Constants.FACTIONS_NAMES[i].toString()).doubleValue() / 100);

			Label label = (Label) scene.lookup("#" + Constants.FACTIONS_NAMES[i].toString() + Constants.PROGRESSBAR_LABEL_SUFIX_ID);
			double pourcent = model.getFractions().get(Constants.FACTIONS_NAMES[i].toString()).doubleValue();
			label.setText(pourcent + "%");
		}
	}
	
	private Hyperlink[] CreateResponseAction(ArrayList<String> responses) {

		Hyperlink options[] = new Hyperlink[responses.size()];
	    
        for (int i=0; i < responses.size(); i++) {
    	    var hl = new Hyperlink(responses.get(i));
    	    hl.setId("response_" + i);
    	    hl.setOnAction(e -> RunUserSelection( (Hyperlink) e.getSource()));
    	    options[i] = hl;
        }

        return options;
	}
	
	private void RunUserSelection(Hyperlink element) 
	{
		// get id of response
		currentResponseIndex = Character.getNumericValue(element.getId().charAt( element.getId().length() -1 )) + 1;
		isNewResponse = true;

		GameManager.getInstance().launchEvents();
		if(GameManager.getInstance().checkIfEndGame()) {
			new EndBox();
		}

	}

	public static int getCurrentResponseIndex() {
		return currentResponseIndex;
	}

	public static void setCurrentResponseIndex(int currentResposneIndex) {
		DatasUpdateManager.currentResponseIndex = currentResposneIndex;
	}

	public static boolean isIsNewResponse() {
		return isNewResponse;
	}

	public static void setIsNewResponse(boolean isNewResponse) {
		DatasUpdateManager.isNewResponse = isNewResponse;
	}
}
