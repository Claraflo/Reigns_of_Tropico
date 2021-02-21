package fr.esgi.fonctionnel.game;

import java.io.Serializable;
import java.util.HashMap;

public class Choice implements Serializable  {

    private String choice;
    private int likedEventToChoice;
    private Action actions;

    public Choice(String choice, int likedEventToChoice,Action actions){
        this.choice = choice;
        this.likedEventToChoice = likedEventToChoice;
        this.actions = new Action(actions.getActions());
    }

    public String getChoice() {
        return choice;
    }

    public int getLikedEventToChoice() {
        return likedEventToChoice;
    }

    public Action getActions() {
        return new Action(actions.getActions());
    }
}
