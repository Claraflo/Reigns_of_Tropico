import fr.esgi.fonctionnel.game.GameManager;
import fr.esgi.fonctionnel.game.Level;



public class Launcher {


    public static void main(String[] args) {
        GameManager session = GameManager.getInstance();
        if(session.initNewGame(Level.EASY)){
            session.launchEvents();
        }else{
            System.out.println("Erreur d'initialisation de jeu.");
        }
    }

}
