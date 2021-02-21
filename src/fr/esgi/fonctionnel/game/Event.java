package fr.esgi.fonctionnel.game;

import java.io.Serializable;
import java.util.HashMap;

public class Event implements Serializable  {

    private Season season;
    private String event;
    private int primaryEvent;
    private HashMap<Integer,Choice> choices;

    public Event(Season season,int primaryEvent, String event,HashMap<Integer,Choice> choices){
        this.season=season;
        this.primaryEvent = primaryEvent;
        this.event=event;
        this.choices = new HashMap<>(choices);
    }

    public Event(){}


    public Season getSeason(){
        return season;
    }

    public int getPrimaryEvent(){ return primaryEvent; }

    public String getEvent(){
        return event;
    }

    public HashMap<Integer, Choice> getChoices() {

        return new HashMap<>(choices);
    }
}
