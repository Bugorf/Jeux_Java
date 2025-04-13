package projet.jeux.vue;

import javax.swing.*;
import java.awt.*;

/**
 *  La classe intermédiaire pour collecter les infos de joueur.
 *
 */
public class VueInterm extends JPanel {

    private final String nomJoueur;
    private JButton btnConfirmer;

    public VueInterm() {
        setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));

        JLabel labelTitre = new JLabel("Veuillez entrer votre nom: ");
        labelTitre.setFont(new Font("SansSerif", Font.BOLD, 24));
        labelTitre.setHorizontalAlignment(SwingConstants.CENTER);

        JTextField input = new JTextField();
        input.setFont(new Font("SansSerif", Font.PLAIN, 16));
        nomJoueur = input.getText();

        btnConfirmer = new JButton("Confirmer");
        btnConfirmer.setFont(new Font("SansSerif", Font.BOLD, 18));
        btnConfirmer.setBackground(new Color(0x4CAF50));
        btnConfirmer.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnConfirmer.setPreferredSize(new Dimension(150, 40));

        add(labelTitre);
        add(input);
        add(btnConfirmer);
    }

    public String getNomJoueur() {
        return nomJoueur;
    }

    public void setAction(Runnable action) {
        btnConfirmer.addActionListener(e -> action.run());
    }
}
