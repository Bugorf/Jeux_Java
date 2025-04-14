package projet.jeux.vue;

import projet.jeux.modele.ModeleJoueur;
import projet.jeux.modele.ModeleParam;
import projet.jeux.modele.ModelePlateau;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Objects;

public class VueAccueil extends JFrame {
    private JPanel cardPanel;
    private CardLayout cardLayout;
    public VueAccueil(VueInterm vueInterm, VueParam vueParam) {
        setTitle("Accueil");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1500, 900);
        setLocationRelativeTo(null);

        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);
        // === Accueil Panel ===
        JPanel panelAccueil = new JPanel();
        panelAccueil.setOpaque(false);
        panelAccueil.setLayout(new BoxLayout(panelAccueil, BoxLayout.Y_AXIS));
        panelAccueil.setBorder(BorderFactory.createEmptyBorder(50, 200, 50, 200)); // top, left, bottom, right

        JLabel nomJeu = new JLabel("Plato");
        nomJeu.setAlignmentX(Component.CENTER_ALIGNMENT);
        nomJeu.setFont(new Font("SansSerif", Font.BOLD, 70));
        nomJeu.setBorder(BorderFactory.createEmptyBorder(20, 0, 40, 0));

        // Créer des boutons avec style
        JButton btnJouer = createBigButton("Jouer");
        btnJouer.addActionListener(e -> cardLayout.show(cardPanel, "vueInterm"));

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
        cardPanel.add(vueParam, "panelParam");
        cardPanel.add(vueInterm, "vueInterm");

        cardLayout.show(cardPanel, "panelAccueil");

        JLabel jLabel = new JLabel(new ImageIcon("projet/jeux/assets/mer.jpg"));
        jLabel.setLayout(new BorderLayout());
        cardPanel.setOpaque(false);
        jLabel.add(cardPanel, BorderLayout.CENTER);
        setContentPane(jLabel);

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

    public JFrame getFrame() {
        return this;
    }

    public void switchView(VueJeu vueJeu) {
        cardPanel.add(vueJeu, "vueJeu");
        cardLayout.show(cardPanel, "vueJeu");
    }
}


