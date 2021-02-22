package com.tropico.game;

import com.tropico.game.Action;

import java.io.Serializable;

/**
 * Class Choice
 * <p>
 * constaint a choice linked to an event.
 **/

public class Choice implements Serializable {

    private String choice;
    private int likedEventToChoice;
    private Action actions;

    public Choice(String choice, int likedEventToChoice, Action actions) {
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
