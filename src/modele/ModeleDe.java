package src.modele;

import java.util.Random;

/**
 * Cette classe est utilisé pour générer la chiffre de dé.
 */

public class ModeleDe {
    private int veleurDé;

    public int lancer() {
        if (ModeleParam.getDifficulte() == "Difficile") {
            veleurDé = new Random().nextInt(3);
        } else {
            veleurDé = new Random().nextInt(7);
        }
        return veleurDé;
    }


}