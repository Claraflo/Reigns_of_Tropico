package com.tropico.graphicUI;

import com.tropico.game.GameManager;
import com.tropico.game.Level;
import javafx.application.Application;
import javafx.stage.Stage;


/**
 * JavaFX App
 */
public class App extends Application {

    @Override
    public void start(Stage primaryStage) {
    	SceneManager builder = SceneManager.getInstance(primaryStage);
    	builder.CreateMainScene();

        GameManager session = GameManager.getInstance();
        if(session.initNewGame(Level.EASY)){
            session.launchEvents();
        }else{
            System.out.println("Erreur d'initialisation de jeu.");
        }
    }

    public static void main(String[] args) {
    	
        launch();
    }

}