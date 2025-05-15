package projet.jeux.modele;

import java.util.Random;

public class ModeleDe{
    private int veleurDé;
    public int lancer(){
        veleurDé = new Random().nextInt(1, 7); //obtenir un chiffre entre 1 et 6
        //ou this.veleurDé += rand.nextInt(12); mais j'aime bien l'idée qu'il y ait 2 dés
        return veleurDé;
    }


}