package projet.jeux.vue;

import projet.jeux.modele.ModeleJoueur;
import projet.jeux.modele.ModelePlateau;

import javax.swing.*;
import java.awt.*;

public class VueJeu extends JFrame {

    public VueJeu(ModelePlateau modelePlateau, ModeleJoueur modeleJoueur) {
        setLayout(new BorderLayout());

        VuePlateau vuePlateau = new VuePlateau(modelePlateau);
        VueJoueur vueJoueur = new VueJoueur(modeleJoueur);

        getContentPane().add(vuePlateau, BorderLayout.CENTER);
        getContentPane().add(vueJoueur, BorderLayout.EAST);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
