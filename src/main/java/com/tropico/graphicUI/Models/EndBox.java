package com.tropico.graphicUI.Models;

import com.tropico.game.GameManager;
import com.tropico.game.Level;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.*;
import javafx.scene.*;

public class EndBox {

    public EndBox() {

        Stage windows = new Stage();

        windows.initModality(Modality.APPLICATION_MODAL);
        windows.setTitle("Fin de partie.");
        windows.setMinWidth(350);

        Label label = new Label("Suite à une trop grande insatisfaction vous etes destitué.");
        Button start = new Button("OK");
        start.setOnAction(e -> {
            windows.close();
            GameManager.getInstance().initNewGame(Level.EASY);
        });
        VBox layout = new VBox(20);
        layout.getChildren().addAll(label,start);
        layout.setAlignment(Pos.TOP_CENTER);
        layout.setPadding(new Insets(30,10,30,10));

        Scene scene = new Scene(layout);
        windows.setScene(scene);
        windows.show();
    }

}
