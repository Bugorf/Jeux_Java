package projet.jeux.controleur;
import projet.jeux.modele.*;
import projet.jeux.vue.*;

import java.awt.*;

public class ControleurJeu {

    public ControleurJeu() {
        ModelePlateau plateau = new ModelePlateau();
        ModeleJoueur modeleJoueur = new ModeleJoueur("Jake", Color.RED);
        new VueJeu(modeleJoueur, plateau);

    }
    public static void main(String[] args) {
        new ControleurJeu();
    }

}
