package fr.esgi.fonctionnel.game;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.*;

public class EventManager {

    public HashMap<Integer,Event> events;
    private LinkedList<Integer> eventsToPlay;
    private LinkedList<Integer> eventsPlayed;
    private Event currentEvent;

    public EventManager(){

        //TODO : fonctions pour serialiser.
        events= new HashMap<>();
        currentEvent = new Event();

        HashMap<Factions,ArrayList<Integer>> actions = new HashMap<>();
        actions.put(Factions.CAPITALISTES, new ArrayList<Integer>(Arrays.asList(10,0)));
        actions.put(Factions.RELIGIEUX,new ArrayList<Integer>(Arrays.asList(-20,-5)));
        actions.put(Factions.ISLAND,new ArrayList<Integer>(Arrays.asList(10,-5,20)));

        HashMap<Integer,Choice> choices = new HashMap<>();
        choices.put(1,new Choice("Rep 1",0,new Action(actions)));
        choices.put(2,new Choice("Rep 2",0,new Action(actions)));
        choices.put(3,new Choice("Rep 3",3,new Action(actions)));
        choices.put(4,new Choice("Rep 4",0,new Action(actions)));

        events.put(1,new Event(Season.NONE,1,"event1",choices));
        events.put(2,new Event(Season.NONE,1,"event2",choices));
        events.put(3,new Event(Season.NONE,0,"event3",choices));
        events.put(4,new Event(Season.NONE,1,"event4",choices));

        eventsToPlay=new   LinkedList<>();
        for (Map.Entry<Integer,Event> entry : events.entrySet()) {
            if(entry.getValue().getPrimaryEvent()==1){
                eventsToPlay.add(entry.getKey());
            }
        }

        serialize(events);
        eventsPlayed=new LinkedList<>();
    }

    // TODO: Move this:
    public static <T> void serialize(T monObjet){
        FileOutputStream file;
        ObjectOutputStream oos = null;
        try {
            file = new FileOutputStream("src/test.json");
            oos = new ObjectOutputStream(file);
            oos.writeObject(monObjet);
            oos.flush();
            oos.close();
            file.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("je suis un ficher cacher");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("je suis toujour ouvert");
        }

    }

    public Event getCurrentEvent() {
        return new Event(currentEvent.getSeason(),currentEvent.getPrimaryEvent(),currentEvent.getEvent(), currentEvent.getChoices());
    }

    public void setCurrentEvent(Event linkedEvent) {
        this.currentEvent = linkedEvent;
    }

    public LinkedList<Integer> getEventsPlayed() {
        return eventsPlayed;
    }

    public LinkedList<Integer> getEventsToPlay() {
        return eventsToPlay;
    }


    public Event getEventToPlay(){
        int indexEventToPlay = drawRandomEvent();
        eventsPlayed.add(eventsToPlay.get(indexEventToPlay));
        eventsToPlay.remove(indexEventToPlay);

        return events.get(eventsPlayed.get(eventsPlayed.size()-1));
    }

    public int drawRandomEvent(){

        int indexEventToPlay=0;
        Random rand = new Random();
        int min=1;
        int max = eventsToPlay.size();

        if(eventsPlayed.size()==0){
            indexEventToPlay = ( min + (int)(Math.random() * ((max - min) + 1)))-1;
            return indexEventToPlay;
        }

        do{
            indexEventToPlay = (min + (int)(Math.random() * ((max - min) + 1)))-1;
        }while(eventsPlayed.contains(eventsToPlay.get(indexEventToPlay)));

        return indexEventToPlay;
    }

    public boolean CheckLinkedEventToChoice(int indexChoice) {

        if(events.get(eventsPlayed.get(eventsPlayed.size()-1)).getChoices().containsKey(indexChoice)) {
            return events.get(eventsPlayed.get(eventsPlayed.size() - 1)).getChoices().get(indexChoice).getLikedEventToChoice() != 0;
        }else{
            return false;
        }
    }

    public Event getLinkedEvent(int indexChoice) {

        return events.get(events.get(eventsPlayed.get(eventsPlayed.size()-1)).getChoices().get(indexChoice).getLikedEventToChoice());
    }

    public void applyActionChoice(Island currentIsland,int indexChoiceOfUser) {
        Action currentAction = currentEvent.getChoices().get(indexChoiceOfUser).getActions();

        for (Map.Entry<Factions,ArrayList<Integer>> entry : currentAction.getActions().entrySet()) {
            if(entry.getKey().equals(Factions.ISLAND)){
                currentIsland.setFeaturesIsland(entry.getValue());
            }else{
                currentIsland.setFactions(entry.getKey(),entry.getValue());
            }

        }
    }

}
