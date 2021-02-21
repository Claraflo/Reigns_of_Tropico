package fr.esgi.fonctionnel.game;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

public class SaveObjet {

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
}
