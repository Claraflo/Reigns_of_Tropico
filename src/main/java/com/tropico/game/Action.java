package com.tropico.game;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Class Action
 * <p>
 * Constaint a Hashmap with the list of concequencies of a choice.
 **/

public class Action implements Serializable {

    private HashMap<Factions, ArrayList<Integer>> actions;

    public Action(HashMap<Factions, ArrayList<Integer>> actions) {
        this.actions = new HashMap<Factions, ArrayList<Integer>>(actions);
    }

    public HashMap<Factions, ArrayList<Integer>> getActions() {
        return new HashMap<Factions, ArrayList<Integer>>(actions);
    }
}
