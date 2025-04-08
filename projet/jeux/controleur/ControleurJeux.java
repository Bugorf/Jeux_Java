package projet.jeux.controleur;
import projet.jeux.modele.*;
import projet.jeux.vue.*;

public class ControleurJeux {

    public ControleurJeux() {
        ModelePlateau plateau = new ModelePlateau();
        new VuePlateau(plateau);

    }

    public static void main(String[] args) {
        new ControleurJeux();
    }

}
