package com.tropico.game;

import com.tropico.game.Choice;
import com.tropico.game.Season;

import java.io.Serializable;
import java.util.HashMap;

/**
 * Class Event
 * <p>
 * Class containt an event
 * Choices are linked thanks to the HashMap choices.
 * Season is specified thanks to the private attribut season
 * Primary or secondary status is specified thanks to the private attribut primaryEvent
 **/

public class Event implements Serializable {

    private Season season;
    private String event;
    private int primaryEvent;
    private HashMap<Integer, Choice> choices;

    public Event(Season season, int primaryEvent, String event, HashMap<Integer, Choice> choices) {
        this.season = season;
        this.primaryEvent = primaryEvent;
        this.event = event;
        this.choices = new HashMap<>(choices);
    }

    public Event() {
    }


    public Season getSeason() {
        return season;
    }

    public int getPrimaryEvent() {
        return primaryEvent;
    }

    public String getEvent() {
        return event;
    }

    public HashMap<Integer, Choice> getChoices() {

        return new HashMap<>(choices);
    }
}
