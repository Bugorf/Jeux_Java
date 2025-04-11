package projet.jeux.vue;

import projet.jeux.modele.ModeleJoueur;
import projet.jeux.modele.ModeleParam;
import projet.jeux.modele.ModelePlateau;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class VueAccueil extends JFrame {
    public VueAccueil(ModelePlateau modelePlateau, ModeleJoueur modeleJoueur, ModeleParam modeleParam, ArrayList<ModeleJoueur> ensembleJoueur) {
        setTitle("Accueil");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1500, 900);
        setLocationRelativeTo(null);

        CardLayout cardLayout = new CardLayout();
        JPanel cardPanel = new JPanel(cardLayout);

        VueJeu vueJeu = new VueJeu(modelePlateau, modeleJoueur, ensembleJoueur);
        JPanel panelParam = new JPanel();
        panelParam.add(new VueParam(modeleParam));

        // === Accueil Panel ===
        JPanel panelAccueil = new JPanel();
        panelAccueil.setLayout(new BoxLayout(panelAccueil, BoxLayout.Y_AXIS));
        panelAccueil.setBorder(BorderFactory.createEmptyBorder(50, 200, 50, 200)); // top, left, bottom, right

        JLabel nomJeu = new JLabel("Nom du Jeu");
        nomJeu.setAlignmentX(Component.CENTER_ALIGNMENT);
        nomJeu.setFont(new Font("SansSerif", Font.BOLD, 32));
        nomJeu.setBorder(BorderFactory.createEmptyBorder(20, 0, 40, 0));

        // Créer des boutons avec style
        JButton btnJouer = createBigButton("Jouer");
        btnJouer.addActionListener(e -> cardLayout.show(cardPanel, "vueJeu"));

        JButton btnParam = createBigButton("Paramètres");
        btnParam.addActionListener(e -> cardLayout.show(cardPanel, "panelParam"));

        JButton btnQuitter = createBigButton("Quitter");
        btnQuitter.addActionListener(e -> System.exit(0));

        // Ajouter composants avec glue pour centrer verticalement
        panelAccueil.add(Box.createVerticalGlue());
        panelAccueil.add(nomJeu);
        panelAccueil.add(btnJouer);
        panelAccueil.add(Box.createVerticalStrut(20));
        panelAccueil.add(btnParam);
        panelAccueil.add(Box.createVerticalStrut(20));
        panelAccueil.add(btnQuitter);
        panelAccueil.add(Box.createVerticalGlue());

        // Ajout aux cartes
        cardPanel.add(panelAccueil, "panelAccueil");
        cardPanel.add(panelParam, "panelParam");
        cardPanel.add(vueJeu, "vueJeu");

        setContentPane(cardPanel);
        cardLayout.show(cardPanel, "panelAccueil");
        setVisible(true);
    }

    // Méthode utilitaire pour créer un gros bouton rempli
    private JButton createBigButton(String text) {
        JButton button = new JButton(text);
        button.setMaximumSize(new Dimension(300, 60)); // largeur, hauteur
        button.setPreferredSize(new Dimension(300, 60));
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setFont(new Font("SansSerif", Font.PLAIN, 20));
        return button;
    }
}


