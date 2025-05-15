package projet.jeux.vue;

import javax.swing.*;
import java.awt.*;

/**
 *  La classe principale d'interface du jeu, y compris le plateau, le menu
 *  et une fenêtre des infos de joueurs.
 */
public class VueJeu extends JPanel {
    private VueJoueur vueJoueur;
    public VueJeu(VuePlateau vuePlateau, VueMenu vueMenu) {
        setLayout(new BorderLayout());
        //this.vueJoueur = vueJoueur;
        JPanel menuContainer = new JPanel(new FlowLayout(FlowLayout.LEFT));
        menuContainer.setOpaque(false);
        menuContainer.add(vueMenu);

        add(menuContainer, BorderLayout.NORTH);
        add(vuePlateau, BorderLayout.CENTER);
        //add(vueJoueur, BorderLayout.EAST);

    }
/*
    public void setVueJoueur(VueJoueur nouveau) {
        remove(this.vueJoueur);
        this.vueJoueur = nouveau;
        add(this.vueJoueur, BorderLayout.EAST);
        revalidate();
        repaint();
    }


 */

}

