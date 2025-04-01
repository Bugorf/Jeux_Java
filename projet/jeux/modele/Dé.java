package projet.jeux.modele;

import java.util.Random;

public class Dé{
    private int veleurDé = 0;
    Random rand = new Random();


    public int lancerDé(){
        this.veleurDé += rand.nextInt(6); //obtenir un chiffre entre 1 et 6
        this.veleurDé += rand.nextInt(6);
        //ou this.veleurDé += rand.nextInt(12); mais j'aime bien l'idée qu'il y ait 2 dés
        return veleurDé;
    }


}