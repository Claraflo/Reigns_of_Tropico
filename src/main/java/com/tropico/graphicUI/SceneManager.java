package com.tropico.graphicUI;

import com.tropico.graphicUI.Models.LevelBox;
import com.tropico.graphicUI.Utils.Constants;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.paint.Color;

/// Singleton. Create all elements in view and display them
public final class SceneManager {
	
	private static SceneManager _instance;
	private Scene _scene;
	private Stage _primaryStage;
	
	private final double SCENE_WIDTH = 840;
	private final double SCENE_HEIGH = 480;
	
	private SceneManager(Stage primaryStage) {
		_primaryStage = primaryStage;
	}
	
    public static SceneManager getInstance(Stage primaryStage) {
        if(_instance == null) {
        	_instance = new SceneManager(primaryStage);
        }
        
        return _instance;
    }
	
	public void CreateMainScene() {
    	BorderPane border = new BorderPane();

    	HBox hbox = CreateTopMenu();
		border.setTop(hbox);
    	
		VBox leftSideOfScene = CreateLeftSideOfScene();
		border.setLeft(leftSideOfScene);
		
		VBox rightSideOfScene = CreateRightSideOfScene();
		border.setRight(rightSideOfScene);

        _scene = new Scene(border, SCENE_WIDTH, SCENE_HEIGH);
        _primaryStage.setResizable(true);

        _primaryStage.setScene(_scene);
        _primaryStage.show();
	}
	
	
	private HBox CreateTopMenu() {
	    HBox hbox = new HBox();
	    hbox.setPadding(new Insets(15, 12, 15, 12));
	    hbox.setSpacing(10);
	    hbox.setStyle("-fx-background-color: #336699;");

	    Label gameTitle = new Label("Tropico");
	    gameTitle.setFont(Font.font("Arial", FontWeight.BOLD, 18));
	    gameTitle.setTextFill(Color.web("#33E5FF"));
	    
	    Button buttonNewGame = new Button("Start new game");
	    buttonNewGame.setOnAction(e -> new LevelBox());
	    
	    buttonNewGame.setPrefSize(100, 20);
	    
	    Button buttonProjected = new Button("Save game");
	    buttonProjected.setPrefSize(100, 20);
	    hbox.getChildren().addAll(gameTitle, buttonNewGame, buttonProjected);

	    return hbox;
	}
	
	private void test() {
		Text tb = (Text) _scene.lookup("#titleLabel");
		tb.setText("textChange");

	}
	
	private VBox CreateLeftSideOfScene() {
		// general box attribute
	    VBox leftBox = new VBox();
	    leftBox.setPrefWidth(SCENE_WIDTH/2);
	    leftBox.setPadding(new Insets(10));
	    leftBox.setStyle("-fx-background-color: DAE6F3;");
	    leftBox.setSpacing(5);

	    // Display year and season
	    Text title = new Text("Tours: 1  Saison: 1/4");
	    title.setId(Constants.INFO_LABEL_ID);
	    title.setFont(Font.font("Arial", FontWeight.BOLD, 14));
	    leftBox.getChildren().add(title);

	   
	    VBox questionPanel = new VBox();
		questionPanel.setId(Constants.QUESTION_PANEL_ID);

	    leftBox.getChildren().add(questionPanel);
	    return leftBox;
	}
	 
	private VBox CreateRightSideOfScene() {
	    VBox rightBox = new VBox();
	    rightBox.setPrefWidth(SCENE_WIDTH/2);
	    rightBox.setPadding(new Insets(10));
	    rightBox.setAlignment(Pos.TOP_CENTER);
	    rightBox.setSpacing(5);
	    rightBox.setStyle("-fx-background-color: E8E8E8;");
	    
        for (int i=0; i <  Constants.FACTIONS_NAMES.length ; i++) {
        	HBox line = new HBox();
        	Label factionName = new Label(Constants.FACTIONS_NAMES[i].toString().toLowerCase());
        	factionName.setFont(Font.font("Arial", FontWeight.BOLD, 12));

    	    ProgressBar pb = new ProgressBar(0.5);
    	    pb.setId(Constants.FACTIONS_NAMES[i] + Constants.PROGRESSBAR_SUFIX_ID);
    	    //pb.setProgress(0.5);
    	    pb.setPrefWidth(150);
    	    Label pourcentage = new Label("50%");
    	    pourcentage.setId(Constants.FACTIONS_NAMES[i] + Constants.PROGRESSBAR_LABEL_SUFIX_ID);

    	    line.getChildren().addAll(factionName, pb, pourcentage);
    	    rightBox.getChildren().add(line);
	    } 
	            
        // PieChart section 
        HBox pieChartPanel = new HBox();
        pieChartPanel.setId(Constants.PIECHART_PANEL_ID);
	    rightBox.getChildren().add(pieChartPanel);
	    
	    // stats line
	    HBox infosPanel = new HBox();
	    Label moneyLabel = new Label("trésors: ");
	    Text moneyValue = new Text("0" + "$ ");
	    moneyValue.setId(Constants.MONEY_VALUE_ID);
	    		
	    Label satisLabel = new Label("satisfaction: ");
	    Text satisValue = new Text("0" + "% ");
	    satisValue.setId(Constants.SATISFACTION_VALUE_ID);
	    
	    Label populationLabel = new Label("population: ");
	    Text populationValue = new Text("0");
	    populationValue.setId(Constants.POPULATION_VALUE_ID);
	    infosPanel.getChildren().addAll(moneyLabel, moneyValue, satisLabel, satisValue, populationLabel, populationValue);
	    
	    rightBox.getChildren().add(infosPanel);
	    return rightBox;
	}
	
	public Scene getScene() {
		return _scene;
	}
}
