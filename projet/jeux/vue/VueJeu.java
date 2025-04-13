package projet.jeux.vue;

import javax.swing.*;
import java.awt.*;

/**
 *  La classe principale d'interface du jeu, y compris le plateau, le menu
 *  et une fenêtre des infos de joueurs.
 */
public class VueJeu extends JPanel {
    public VueJeu(JPanel vuePlateau, JPanel vueJoueur, JPanel vueMenu) {
        setLayout(new BorderLayout());

        add(vueMenu, BorderLayout.NORTH);
        add(vuePlateau, BorderLayout.CENTER);
        add(vueJoueur, BorderLayout.EAST);

    }

}
