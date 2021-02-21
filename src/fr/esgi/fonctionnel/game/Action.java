package fr.esgi.fonctionnel.game;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class Action implements Serializable {

    private HashMap<Factions, ArrayList<Integer>> actions;

    public Action(HashMap<Factions, ArrayList<Integer>> actions) {
        this.actions = new HashMap<Factions, ArrayList<Integer>>(actions);
    }

    public HashMap<Factions, ArrayList<Integer>> getActions() {
        return new HashMap<Factions,ArrayList<Integer>>(actions);
    }
}
