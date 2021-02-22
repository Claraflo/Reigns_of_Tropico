package com.tropico.graphicUI.Models;

import com.tropico.game.GameManager;
import com.tropico.game.Level;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;
import javafx.stage.*;
import javafx.scene.*;

public class LevelBox {

	private Level difficultySelected;
	
    public LevelBox(){

        Stage windows = new Stage();

        windows.initModality(Modality.APPLICATION_MODAL);
        windows.setTitle("Nouvelle partie.");
        windows.setMinWidth(350);


        ToggleGroup levelsChoice = new ToggleGroup();

        RadioButton easy = new RadioButton("Facile   ");
        easy.setOnAction(e -> this.difficultySelected = Level.EASY);
        easy.setToggleGroup(levelsChoice);

        RadioButton medium = new RadioButton("Moyen ");
        medium.setOnAction(e -> this.difficultySelected = Level.MEDIUM);

        medium.setToggleGroup(levelsChoice);

        RadioButton hard = new RadioButton("Difficile");
        hard.setOnAction(e -> this.difficultySelected = Level.HARD);
        hard.setToggleGroup(levelsChoice);

        Button start = new Button("START");
        start.setOnAction(e -> {
            GameManager.getInstance().initNewGame(this.difficultySelected);
            GameManager.getInstance().launchEvents();
        	windows.close();
        	});
        VBox layout = new VBox(20);
        layout.getChildren().addAll(easy,medium,hard,start);
        layout.setAlignment(Pos.TOP_CENTER);
        layout.setPadding(new Insets(10,2,10,2));

        Scene scene = new Scene(layout);
        windows.setScene(scene);
        windows.show();
    }

}
