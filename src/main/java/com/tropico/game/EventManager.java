package com.tropico.game;

import java.io.*;
import java.util.*;


/**
 * Class EventManager
 * <p>
 * This class init events and it allows to draw an random event storage in events Hashmap.
 **/


public class EventManager {

    public HashMap<Integer, Event> events;
    private LinkedList<Integer> eventsToPlay;
    private LinkedList<Integer> eventsPlayed;
    private Event currentEvent;

    public EventManager() {


        events = new HashMap<>();
        events = deserialise("src/main/java/com/tropico/listeEvent.srlz");
        currentEvent = new Event();

        eventsToPlay = new LinkedList<>();
        for (Map.Entry<Integer, Event> entry : events.entrySet()) {
            if (entry.getValue().getPrimaryEvent() == 1) {
                eventsToPlay.add(entry.getKey());
            }
        }


        eventsPlayed = new LinkedList<>();
    }


    public static <T> void serialize(T monObjet) {
        FileOutputStream file;
        ObjectOutputStream oos = null;
        try {
            file = new FileOutputStream("src/main/java/com/tropico/listeEvent.srlz");
            oos = new ObjectOutputStream(file);
            oos.writeObject(monObjet);
            oos.flush();
            oos.close();
            file.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static <T> T deserialise(String monFichier){
        FileInputStream file;
        ObjectInputStream ois;
        T objet = null;
        try {
            file = new FileInputStream(monFichier);
            ois = new ObjectInputStream(file);
            objet = (T) ois.readObject();
            ois.close();
            file.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return objet;
    }

    public Event getCurrentEvent() {
        return new Event(currentEvent.getSeason(), currentEvent.getPrimaryEvent(), currentEvent.getEvent(), currentEvent.getChoices());
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


    public Event getEventToPlay() {
        int indexEventToPlay = drawRandomEvent();
        eventsPlayed.add(eventsToPlay.get(indexEventToPlay));
        eventsToPlay.remove(indexEventToPlay);

        return events.get(eventsPlayed.get(eventsPlayed.size() - 1));
    }

    public int drawRandomEvent() {

        int indexEventToPlay = 0;
        Random rand = new Random();
        int min = 1;
        int max = eventsToPlay.size();

        if (eventsPlayed.size() == 0) {
            indexEventToPlay = (min + (int) (Math.random() * ((max - min) + 1))) - 1;
            return indexEventToPlay;
        }

        do {
            indexEventToPlay = (min + (int) (Math.random() * ((max - min) + 1))) - 1;
        } while (eventsPlayed.contains(eventsToPlay.get(indexEventToPlay)));

        return indexEventToPlay;
    }

    public boolean CheckLinkedEventToChoice(int indexChoice) {

        if (events.get(eventsPlayed.get(eventsPlayed.size() - 1)).getChoices().containsKey(indexChoice)) {
            return events.get(eventsPlayed.get(eventsPlayed.size() - 1)).getChoices().get(indexChoice).getLikedEventToChoice() != 0;
        } else {
            return false;
        }
    }

    public Event getLinkedEvent(int indexChoice) {
        return events.get(events.get(eventsPlayed.get(eventsPlayed.size() - 1)).getChoices().get(indexChoice).getLikedEventToChoice());
    }

    public void applyActionChoice(Island currentIsland, int indexChoiceOfUser) {
        Action currentAction = currentEvent.getChoices().get(indexChoiceOfUser).getActions();

        for (Map.Entry<Factions, ArrayList<Integer>> entry : currentAction.getActions().entrySet()) {
            if (entry.getKey().equals(Factions.ISLAND)) {
                currentIsland.setFeaturesIsland(entry.getValue());
            } else {
                currentIsland.setFactions(entry.getKey(), entry.getValue());
            }
        }
    }

    public void checkCapacityEventToPlay() {

        if (eventsToPlay.size() == 0) {
            for (Map.Entry<Integer, Event> entry : events.entrySet()) {
                if (entry.getValue().getPrimaryEvent() == 1) {
                    eventsToPlay.add(entry.getKey());
                }
            }
            int tmpIdEvent = eventsPlayed.get(eventsPlayed.size()-1);
            eventsPlayed.clear();
            eventsPlayed.add(tmpIdEvent);
            for(int i=0;i<eventsToPlay.size();i++){
                if(eventsToPlay.get(i)==tmpIdEvent){
                    eventsToPlay.remove(i);
                }
            }
        }

    }
}
