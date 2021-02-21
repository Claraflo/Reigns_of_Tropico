package fr.esgi.fonctionnel.game;

import java.io.*;
import java.util.*;

public class EventManager {

    public HashMap<Integer, Event> events;
    private LinkedList<Integer> eventsToPlay;
    private LinkedList<Integer> eventsPlayed;
    private Event currentEvent;

    public EventManager() {

        //TODO : fonctions pour serialiser.
        events = new HashMap<>();
        events = deserialise("src/listeEvent.srlz");
        currentEvent = new Event();

        HashMap<Factions, ArrayList<Integer>> actions;
        HashMap<Integer, Choice> choices;

/*
        choices = new HashMap<>();

        actions = new HashMap<>();
        actions.put(Factions.COMUNISTES, new ArrayList<Integer>(Arrays.asList(10, 2)));
        actions.put(Factions.LIBERAUX, new ArrayList<Integer>(Arrays.asList(-10, 0)));
        actions.put(Factions.RELIGIEUX, new ArrayList<Integer>(Arrays.asList(5, 1)));
        choices.put(1, new Choice("Requilibrer les biens pour que chacun puisse manger.", 0, new Action(actions)));

        actions = new HashMap<>();
        actions.put(Factions.COMUNISTES, new ArrayList<Integer>(Arrays.asList(-15, -3)));
        actions.put(Factions.LIBERAUX, new ArrayList<Integer>(Arrays.asList(-20, -2)));
        actions.put(Factions.MILITAIRES, new ArrayList<Integer>(Arrays.asList(10, 0)));
        choices.put(2, new Choice("Intervention de l'armée pour éviter les emeutes", 0, new Action(actions)));

        actions = new HashMap<>();
        actions.put(Factions.CAPITALISTES, new ArrayList<Integer>(Arrays.asList(10, 0)));
        actions.put(Factions.NATIONNALISTES, new ArrayList<Integer>(Arrays.asList(-5, 0)));
        actions.put(Factions.ISLAND, new ArrayList<Integer>(Arrays.asList(-15, 0, 0)));
        choices.put(3, new Choice("Acheter en import de la nouriturre pour palier au manque", 0, new Action(actions)));

        events.put(1, new Event(Season.AUTOMN, 1, "Pandemie animal", choices));

        choices = new HashMap<>();
        actions = new HashMap<>();
        actions.put(Factions.LIBERAUX, new ArrayList<Integer>(Arrays.asList(10, 0)));
        actions.put(Factions.RELIGIEUX, new ArrayList<Integer>(Arrays.asList(-10, 0)));
        choices.put(1, new Choice("Autoriser le mariage pour tous", 0, new Action(actions)));

        actions = new HashMap<>();
        actions.put(Factions.LIBERAUX, new ArrayList<Integer>(Arrays.asList(-10, 0)));
        actions.put(Factions.RELIGIEUX, new ArrayList<Integer>(Arrays.asList(10, 0)));
        choices.put(2, new Choice("Interdire le mariage pour tous", 0, new Action(actions)));

        events.put(2, new Event(Season.NONE, 1, "Mariage homosexuel", choices));

        choices = new HashMap<>();

        actions = new HashMap<>();
        actions.put(Factions.CAPITALISTES, new ArrayList<Integer>(Arrays.asList(-20, 0)));
        actions.put(Factions.NATIONNALISTES, new ArrayList<Integer>(Arrays.asList(10, 0)));
        actions.put(Factions.ECOLOGISTES, new ArrayList<Integer>(Arrays.asList(-10, 0)));
        choices.put(1, new Choice("organiser une chasse et donner à manger à la population", 0, new Action(actions)));

        actions = new HashMap<>();
        actions.put(Factions.ECOLOGISTES, new ArrayList<Integer>(Arrays.asList(10, 0)));
        actions.put(Factions.ISLAND, new ArrayList<Integer>(Arrays.asList(5, 0, 5)));
        choices.put(2, new Choice("Introduire de nouveaux prédateurs pour réguler la population", 5, new Action(actions)));

        events.put(3, new Event(Season.NONE, 1, "Quantité animaux trop importante", choices));

        choices = new HashMap<>();

        actions = new HashMap<>();
        actions.put(Factions.CAPITALISTES, new ArrayList<Integer>(Arrays.asList(10, 0)));
        actions.put(Factions.ECOLOGISTES, new ArrayList<Integer>(Arrays.asList(-15, 0)));
        actions.put(Factions.ISLAND, new ArrayList<Integer>(Arrays.asList(30, 5, 0)));
        choices.put(1, new Choice("Constuire un champ d'extraction.\n", 0, new Action(actions)));

        actions = new HashMap<>();
        actions.put(Factions.CAPITALISTES, new ArrayList<Integer>(Arrays.asList(-10, 0)));
        actions.put(Factions.ECOLOGISTES, new ArrayList<Integer>(Arrays.asList(5, 0)));
        choices.put(2, new Choice("Ne pas exploiter\n", 0, new Action(actions)));

        events.put(4, new Event(Season.NONE, 1, "Terres rares", choices));

        choices = new HashMap<>();

        actions = new HashMap<>();
        actions.put(Factions.ECOLOGISTES, new ArrayList<Integer>(Arrays.asList(-10, 0)));
        actions.put(Factions.ISLAND, new ArrayList<Integer>(Arrays.asList(-20, 0, 0)));
        choices.put(1, new Choice("Lancer une campagne d'extermination \n", 0, new Action(actions)));

        events.put(5, new Event(Season.NONE, 0, "Prolifération araignée", choices));

        choices = new HashMap<>();

        actions = new HashMap<>();
        actions.put(Factions.CAPITALISTES, new ArrayList<Integer>(Arrays.asList(-60, -5)));
        actions.put(Factions.COMUNISTES, new ArrayList<Integer>(Arrays.asList(-60, -5)));
        actions.put(Factions.LIBERAUX, new ArrayList<Integer>(Arrays.asList(-60, -5)));
        actions.put(Factions.RELIGIEUX, new ArrayList<Integer>(Arrays.asList(-60, -5)));
        actions.put(Factions.ECOLOGISTES, new ArrayList<Integer>(Arrays.asList(-60, -5)));
        actions.put(Factions.NATIONNALISTES, new ArrayList<Integer>(Arrays.asList(-60, -5)));
        actions.put(Factions.LOYALISTES, new ArrayList<Integer>(Arrays.asList(-60, -5)));
        choices.put(1, new Choice("les géologues aurais pus le prevoir", 0, new Action(actions)));

        events.put(6, new Event(Season.NONE, 0, "Volcan qui se reveille", choices));

        choices = new HashMap<>();

        actions = new HashMap<>();
        actions.put(Factions.MILITAIRES, new ArrayList<Integer>(Arrays.asList(-5, 0)));
        actions.put(Factions.NATIONNALISTES, new ArrayList<Integer>(Arrays.asList(-10, 0)));
        choices.put(1, new Choice("Présente ses excuses en pleurant", 0, new Action(actions)));

        actions = new HashMap<>();
        actions.put(Factions.COMUNISTES, new ArrayList<Integer>(Arrays.asList(10, 0)));
        actions.put(Factions.LIBERAUX, new ArrayList<Integer>(Arrays.asList(5, 0)));
        actions.put(Factions.NATIONNALISTES, new ArrayList<Integer>(Arrays.asList(5, 0)));
        choices.put(2, new Choice("Démisionner", 0, new Action(actions)));

        actions = new HashMap<>();
        actions.put(Factions.MILITAIRES, new ArrayList<Integer>(Arrays.asList(10, 0)));
        actions.put(Factions.NATIONNALISTES, new ArrayList<Integer>(Arrays.asList(-10, 1)));
        choices.put(3, new Choice("L'executer", 0, new Action(actions)));

        events.put(7, new Event(Season.NONE, 1, "Scandale : Premier ministre surpris avec des prostitués", choices));

        choices = new HashMap<>();

        actions = new HashMap<>();
        actions.put(Factions.MILITAIRES, new ArrayList<Integer>(Arrays.asList(-10, 0)));
        actions.put(Factions.LIBERAUX, new ArrayList<Integer>(Arrays.asList(20, 0)));
        actions.put(Factions.NATIONNALISTES, new ArrayList<Integer>(Arrays.asList(-10, 0)));
        actions.put(Factions.LOYALISTES, new ArrayList<Integer>(Arrays.asList(-5, 0)));
        choices.put(1, new Choice("Gracier", 0, new Action(actions)));

        actions = new HashMap<>();
        actions.put(Factions.MILITAIRES, new ArrayList<Integer>(Arrays.asList(5, 0)));
        actions.put(Factions.NATIONNALISTES, new ArrayList<Integer>(Arrays.asList(10, 0)));
        actions.put(Factions.LOYALISTES, new ArrayList<Integer>(Arrays.asList(10, 0)));
        choices.put(2, new Choice("Enprisonner pour de longues années", 0, new Action(actions)));

        actions = new HashMap<>();
        actions.put(Factions.RELIGIEUX, new ArrayList<Integer>(Arrays.asList(-10, 0)));
        actions.put(Factions.MILITAIRES, new ArrayList<Integer>(Arrays.asList(10, 0)));
        actions.put(Factions.NATIONNALISTES, new ArrayList<Integer>(Arrays.asList(10, 0)));
        actions.put(Factions.LOYALISTES, new ArrayList<Integer>(Arrays.asList(5, 0)));
        choices.put(3, new Choice("Executer", 0, new Action(actions)));

        events.put(8, new Event(Season.NONE, 1, "Opposants politiques", choices));

        choices = new HashMap<>();

        actions = new HashMap<>();
        actions.put(Factions.CAPITALISTES, new ArrayList<Integer>(Arrays.asList(-15, 0)));
        actions.put(Factions.ECOLOGISTES, new ArrayList<Integer>(Arrays.asList(10, 0)));
        actions.put(Factions.ISLAND, new ArrayList<Integer>(Arrays.asList(-15, 0, 15)));

        choices.put(1, new Choice("Amenager une reserve naturelle", 0, new Action(actions)));

        actions = new HashMap<>();
        actions.put(Factions.ECOLOGISTES, new ArrayList<Integer>(Arrays.asList(-10, 0)));
        actions.put(Factions.ISLAND, new ArrayList<Integer>(Arrays.asList(25, 0, 0)));
        choices.put(2, new Choice("Les vendre à un cirque", 0, new Action(actions)));

        actions = new HashMap<>();
        actions.put(Factions.ECOLOGISTES, new ArrayList<Integer>(Arrays.asList(20, 0)));
        actions.put(Factions.CAPITALISTES, new ArrayList<Integer>(Arrays.asList(5, 0)));
        actions.put(Factions.ISLAND, new ArrayList<Integer>(Arrays.asList(10, 0, 5)));
        choices.put(3, new Choice("Organiser un tourisme autour de ca.", 0, new Action(actions)));

        actions = new HashMap<>();
        actions.put(Factions.ECOLOGISTES, new ArrayList<Integer>(Arrays.asList(-30, 0)));
        actions.put(Factions.CAPITALISTES, new ArrayList<Integer>(Arrays.asList(15, 0)));
        actions.put(Factions.ISLAND, new ArrayList<Integer>(Arrays.asList(50, 10, -10)));
        choices.put(4, new Choice("Faire commerce de leurs fourrures et dents", 0, new Action(actions)));

        events.put(9, new Event(Season.NONE, 1, "Animaux rares", choices));

        choices = new HashMap<>();

        actions = new HashMap<>();
        actions.put(Factions.COMUNISTES, new ArrayList<Integer>(Arrays.asList(5, 0)));
        actions.put(Factions.RELIGIEUX, new ArrayList<Integer>(Arrays.asList(15, 0)));
        actions.put(Factions.ISLAND, new ArrayList<Integer>(Arrays.asList(-5, 0, 0)));
        choices.put(1, new Choice("Construire un orphelinat", 17, new Action(actions)));

        actions = new HashMap<>();
        actions.put(Factions.CAPITALISTES, new ArrayList<Integer>(Arrays.asList(10, 0)));
        actions.put(Factions.ISLAND, new ArrayList<Integer>(Arrays.asList(15, 5, 0)));
        choices.put(2, new Choice("Construire une maison close", 14, new Action(actions)));

        events.put(10, new Event(Season.NONE, 1, "Avenir ou Plaisir", choices));

        choices = new HashMap<>();

        actions = new HashMap<>();
        actions.put(Factions.ECOLOGISTES, new ArrayList<Integer>(Arrays.asList(5, 0)));
        actions.put(Factions.LIBERAUX, new ArrayList<Integer>(Arrays.asList(5, 0)));
        actions.put(Factions.ISLAND, new ArrayList<Integer>(Arrays.asList(-20, 0, 0)));
        choices.put(1, new Choice("Construire des immeubles écologique", 0, new Action(actions)));

        actions = new HashMap<>();
        actions.put(Factions.LIBERAUX, new ArrayList<Integer>(Arrays.asList(5, 0)));
        actions.put(Factions.ISLAND, new ArrayList<Integer>(Arrays.asList(-10, 0, 0)));
        choices.put(2, new Choice("Constuire des immeubles standards", 0, new Action(actions)));

        events.put(11, new Event(Season.NONE, 1, "Modernisation de l'ile (construction d'immeuble)", choices));

        choices = new HashMap<>();

        actions = new HashMap<>();
        actions.put(Factions.COMUNISTES, new ArrayList<Integer>(Arrays.asList(20, 0)));
        actions.put(Factions.LIBERAUX, new ArrayList<Integer>(Arrays.asList(10, 0)));
        actions.put(Factions.NATIONNALISTES, new ArrayList<Integer>(Arrays.asList(10, 0)));
        actions.put(Factions.ISLAND, new ArrayList<Integer>(Arrays.asList(-5, 0, 0)));
        choices.put(1, new Choice("Renover les écoles", 0, new Action(actions)));

        actions = new HashMap<>();
        actions.put(Factions.COMUNISTES, new ArrayList<Integer>(Arrays.asList(-10, 0)));
        actions.put(Factions.NATIONNALISTES, new ArrayList<Integer>(Arrays.asList(-10, 0)));
        choices.put(2, new Choice("Ne pas renover les écoles", 0, new Action(actions)));

        events.put(12, new Event(Season.NONE, 0, "Renover les écoles.", choices));

        choices = new HashMap<>();

        actions = new HashMap<>();
        actions.put(Factions.CAPITALISTES, new ArrayList<Integer>(Arrays.asList(15, 0)));
        actions.put(Factions.MILITAIRES, new ArrayList<Integer>(Arrays.asList(10, 0)));
        actions.put(Factions.ECOLOGISTES, new ArrayList<Integer>(Arrays.asList(-10, 0)));
        actions.put(Factions.ISLAND, new ArrayList<Integer>(Arrays.asList(15, 0, 0)));
        choices.put(1, new Choice("Miner le lac", 0, new Action(actions)));

        actions = new HashMap<>();
        actions.put(Factions.ECOLOGISTES, new ArrayList<Integer>(Arrays.asList(15, 0)));
        actions.put(Factions.CAPITALISTES, new ArrayList<Integer>(Arrays.asList(-10, 0)));
        choices.put(2, new Choice("Ne pas le miner", 0, new Action(actions)));

        events.put(13, new Event(Season.NONE, 0, "Mine un lac pour extraire les richesses?", choices));

        choices = new HashMap<>();

        actions = new HashMap<>();
        actions.put(Factions.COMUNISTES, new ArrayList<Integer>(Arrays.asList(-5, 0)));
        actions.put(Factions.LIBERAUX, new ArrayList<Integer>(Arrays.asList(-5, 0)));
        actions.put(Factions.RELIGIEUX, new ArrayList<Integer>(Arrays.asList(10, 0)));
        choices.put(1, new Choice("limite", 0, new Action(actions)));

        actions = new HashMap<>();
        actions.put(Factions.COMUNISTES, new ArrayList<Integer>(Arrays.asList(5, 10)));
        actions.put(Factions.CAPITALISTES, new ArrayList<Integer>(Arrays.asList(5, 0)));
        actions.put(Factions.RELIGIEUX, new ArrayList<Integer>(Arrays.asList(-5, 0)));
        actions.put(Factions.ISLAND, new ArrayList<Integer>(Arrays.asList(5, 0, 0)));
        choices.put(2, new Choice("régule", 0, new Action(actions)));

        actions = new HashMap<>();
        actions.put(Factions.COMUNISTES, new ArrayList<Integer>(Arrays.asList(-15, 10)));
        actions.put(Factions.CAPITALISTES, new ArrayList<Integer>(Arrays.asList(-15, 0)));
        actions.put(Factions.LIBERAUX, new ArrayList<Integer>(Arrays.asList(-20, 0)));
        actions.put(Factions.RELIGIEUX, new ArrayList<Integer>(Arrays.asList(20, 0)));
        choices.put(3, new Choice("prohébition", 0, new Action(actions)));

        events.put(14, new Event(Season.NONE, 0, "Gestion de l'alcool", choices));

        choices = new HashMap<>();

        actions = new HashMap<>();
        actions.put(Factions.COMUNISTES, new ArrayList<Integer>(Arrays.asList(10, 0)));
        actions.put(Factions.LIBERAUX, new ArrayList<Integer>(Arrays.asList(-20, 0)));
        actions.put(Factions.RELIGIEUX, new ArrayList<Integer>(Arrays.asList(5, 5)));
        choices.put(1, new Choice("Créer des allocations familliales", 0, new Action(actions)));

        actions = new HashMap<>();
        actions.put(Factions.COMUNISTES, new ArrayList<Integer>(Arrays.asList(-10, 0)));
        actions.put(Factions.LIBERAUX, new ArrayList<Integer>(Arrays.asList(-5, 0)));
        choices.put(2, new Choice("Supprimer les allocs", 0, new Action(actions)));

        actions = new HashMap<>();
        actions.put(Factions.CAPITALISTES, new ArrayList<Integer>(Arrays.asList(5, 0)));
        actions.put(Factions.COMUNISTES, new ArrayList<Integer>(Arrays.asList(-5, 0)));
        actions.put(Factions.LIBERAUX, new ArrayList<Integer>(Arrays.asList(-5, 0)));
        actions.put(Factions.ISLAND, new ArrayList<Integer>(Arrays.asList(5, 0 , 0)));
        choices.put(3, new Choice("Mettre une taxe anti-enfants", 0, new Action(actions)));

        events.put(15, new Event(Season.NONE, 1, "Allocation familiale", choices));

        choices = new HashMap<>();

        actions = new HashMap<>();
        actions.put(Factions.COMUNISTES, new ArrayList<Integer>(Arrays.asList(-10, 0)));
        actions.put(Factions.NATIONNALISTES, new ArrayList<Integer>(Arrays.asList(-10, 0)));
        choices.put(1, new Choice("Dans les bas quartiers", 0, new Action(actions)));

        actions = new HashMap<>();
        actions.put(Factions.COMUNISTES, new ArrayList<Integer>(Arrays.asList(5, 0)));
        actions.put(Factions.NATIONNALISTES, new ArrayList<Integer>(Arrays.asList(5, 0)));
        actions.put(Factions.ECOLOGISTES, new ArrayList<Integer>(Arrays.asList(5, 0)));
        actions.put(Factions.ISLAND, new ArrayList<Integer>(Arrays.asList(-5, -5, 0)));
        choices.put(2, new Choice("Stations d'épuration", 0, new Action(actions)));

        actions = new HashMap<>();
        actions.put(Factions.ECOLOGISTES, new ArrayList<Integer>(Arrays.asList(-20, 0)));
        choices.put(3, new Choice("Dans la nature", 0, new Action(actions)));

        events.put(16, new Event(Season.NONE, 1, "Eaux usées", choices));

        choices = new HashMap<>();

        actions = new HashMap<>();
        actions.put(Factions.LIBERAUX, new ArrayList<Integer>(Arrays.asList(-10, 0)));
        actions.put(Factions.CAPITALISTES, new ArrayList<Integer>(Arrays.asList(15, 0)));
        choices.put(1, new Choice("Faire travailler les enfants", 0, new Action(actions)));

        actions = new HashMap<>();
        actions.put(Factions.COMUNISTES, new ArrayList<Integer>(Arrays.asList(5, 0)));
        actions.put(Factions.CAPITALISTES, new ArrayList<Integer>(Arrays.asList(-5, 0)));
        actions.put(Factions.LIBERAUX, new ArrayList<Integer>(Arrays.asList(5, 0)));
        actions.put(Factions.NATIONNALISTES, new ArrayList<Integer>(Arrays.asList(15, 0)));
        choices.put(2, new Choice("Laisser le choix aux familles", 0, new Action(actions)));

        actions = new HashMap<>();
        actions.put(Factions.COMUNISTES, new ArrayList<Integer>(Arrays.asList(10, 0)));
        actions.put(Factions.CAPITALISTES, new ArrayList<Integer>(Arrays.asList(-5, 0)));
        actions.put(Factions.LIBERAUX, new ArrayList<Integer>(Arrays.asList(10, 0)));
        actions.put(Factions.NATIONNALISTES, new ArrayList<Integer>(Arrays.asList(5, 0)));
        choices.put(3, new Choice("Interdire le travail des enfants", 0, new Action(actions)));

        actions = new HashMap<>();
        actions.put(Factions.CAPITALISTES, new ArrayList<Integer>(Arrays.asList(-10, 0)));
        actions.put(Factions.LIBERAUX, new ArrayList<Integer>(Arrays.asList(-5, 0)));
        choices.put(4, new Choice("Rendre l'école obligatoire", 0, new Action(actions)));

        events.put(17, new Event(Season.NONE, 0, "Travail des enfants", choices));

        choices = new HashMap<>();

        actions = new HashMap<>();
        actions.put(Factions.ISLAND, new ArrayList<Integer>(Arrays.asList(-20, 0, 0)));
        choices.put(1, new Choice("Engager de couteux geologues pour examiner la situation", 0, new Action(actions)));

        actions = new HashMap<>();
        choices.put(2, new Choice("na rien faire c'est naturelle", 6, new Action(actions)));

        events.put(18, new Event(Season.NONE, 0, "Des tremblement de terre inopiner", choices));

        choices = new HashMap<>();

        actions = new HashMap<>();
        actions.put(Factions.CAPITALISTES, new ArrayList<Integer>(Arrays.asList(20, 0)));
        actions.put(Factions.COMUNISTES, new ArrayList<Integer>(Arrays.asList(-20, 0)));
        actions.put(Factions.RELIGIEUX, new ArrayList<Integer>(Arrays.asList(-10, 0)));
        actions.put(Factions.NATIONNALISTES, new ArrayList<Integer>(Arrays.asList(-5, 0)));
        actions.put(Factions.LIBERAUX, new ArrayList<Integer>(Arrays.asList(-5, 0)));
        choices.put(1, new Choice("Rasé les bidonviles pour constuire des beaux quartiers dessus", 0, new Action(actions)));

        actions = new HashMap<>();
        actions.put(Factions.COMUNISTES, new ArrayList<Integer>(Arrays.asList(10, 0)));
        actions.put(Factions.CAPITALISTES, new ArrayList<Integer>(Arrays.asList(-5, 0)));
        actions.put(Factions.NATIONNALISTES, new ArrayList<Integer>(Arrays.asList(5, 0)));
        choices.put(2, new Choice("Augmenter la securité en ajoutant des gardes", 0, new Action(actions)));

        events.put(19, new Event(Season.NONE, 1, "Bidonville", choices));

        choices = new HashMap<>();

        actions = new HashMap<>();
        actions.put(Factions.MILITAIRES, new ArrayList<Integer>(Arrays.asList(20, 0)));
        actions.put(Factions.LIBERAUX, new ArrayList<Integer>(Arrays.asList(-10, 0)));
        actions.put(Factions.COMUNISTES, new ArrayList<Integer>(Arrays.asList(- 10, 0)));
        choices.put(1, new Choice("mettre un service militaire obligatoire", 0, new Action(actions)));

        actions = new HashMap<>();
        actions.put(Factions.COMUNISTES, new ArrayList<Integer>(Arrays.asList(5, 0)));
        actions.put(Factions.MILITAIRES, new ArrayList<Integer>(Arrays.asList(-15, 0)));
        choices.put(2, new Choice("laisser comme ca", 0, new Action(actions)));

        events.put(20, new Event(Season.NONE, 1, "Service obligatoire", choices));

        choices = new HashMap<>();

        actions = new HashMap<>();
        actions.put(Factions.ISLAND, new ArrayList<Integer>(Arrays.asList(-20, 0, 0)));
        choices.put(1, new Choice("Finnancé la couteuse etude", 0, new Action(actions)));

        actions = new HashMap<>();
        choices.put(2, new Choice("Refuser leur etude", 0, new Action(actions)));

        events.put(21, new Event(Season.NONE, 1, "Des géologes cherche un financement", choices));
*/
        eventsToPlay = new LinkedList<>();
        for (Map.Entry<Integer, Event> entry : events.entrySet()) {
            if (entry.getValue().getPrimaryEvent() == 1) {
                eventsToPlay.add(entry.getKey());
            }
        }

        //serialize(events);


        eventsPlayed = new LinkedList<>();




    }


    // TODO: Move this:
    public static <T> void serialize(T monObjet) {
        FileOutputStream file;
        ObjectOutputStream oos = null;
        try {
            file = new FileOutputStream("src/listeEvent.srlz");
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
