package projet.jeux.vue;

import javax.swing.*;
import java.awt.*;

/**
 * La classe intermédiaire pour collecter les noms des joueurs.
 */
public class VueInterm extends JPanel {

    private JButton btnConfirmer;
    private JTextField inputNom1, inputNom2, inputNom3, inputNom4;

    public VueInterm() {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        // Titre
        JLabel labelTitre = new JLabel("Veuillez entrer les noms des joueurs: ");
        labelTitre.setFont(new Font("SansSerif", Font.BOLD, 24));
        labelTitre.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(labelTitre);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        // Nom Joueur 1
        JPanel nomPanel1 = new JPanel();
        nomPanel1.setOpaque(false);
        nomPanel1.setLayout(new FlowLayout(FlowLayout.CENTER));
        JLabel labelNom1 = new JLabel("Joueur 1: ");
        labelNom1.setFont(new Font("SansSerif", Font.PLAIN, 16));
        inputNom1 = new JTextField();
        inputNom1.setFont(new Font("SansSerif", Font.PLAIN, 16));
        inputNom1.setPreferredSize(new Dimension(250, 40));
        nomPanel1.add(labelNom1);
        nomPanel1.add(inputNom1);
        mainPanel.add(nomPanel1);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        // Nom Joueur 2
        JPanel nomPanel2 = new JPanel();
        nomPanel2.setOpaque(false);
        nomPanel2.setLayout(new FlowLayout(FlowLayout.CENTER));
        JLabel labelNom2 = new JLabel("Joueur 2: ");
        labelNom2.setFont(new Font("SansSerif", Font.PLAIN, 16));
        inputNom2 = new JTextField();
        inputNom2.setFont(new Font("SansSerif", Font.PLAIN, 16));
        inputNom2.setPreferredSize(new Dimension(250, 40));
        nomPanel2.add(labelNom2);
        nomPanel2.add(inputNom2);
        mainPanel.add(nomPanel2);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        // Nom Joueur 3
        JPanel nomPanel3 = new JPanel();
        nomPanel3.setOpaque(false);
        nomPanel3.setLayout(new FlowLayout(FlowLayout.CENTER));
        JLabel labelNom3 = new JLabel("Joueur 3: ");
        labelNom3.setFont(new Font("SansSerif", Font.PLAIN, 16));
        inputNom3 = new JTextField();
        inputNom3.setFont(new Font("SansSerif", Font.PLAIN, 16));
        inputNom3.setPreferredSize(new Dimension(250, 40));
        nomPanel3.add(labelNom3);
        nomPanel3.add(inputNom3);
        mainPanel.add(nomPanel3);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        // Nom Joueur 4
        JPanel nomPanel4 = new JPanel();
        nomPanel4.setOpaque(false);
        nomPanel4.setLayout(new FlowLayout(FlowLayout.CENTER));
        JLabel labelNom4 = new JLabel("Joueur 4: ");
        labelNom4.setFont(new Font("SansSerif", Font.PLAIN, 16));
        inputNom4 = new JTextField();
        inputNom4.setFont(new Font("SansSerif", Font.PLAIN, 16));
        inputNom4.setPreferredSize(new Dimension(250, 40));
        nomPanel4.add(labelNom4);
        nomPanel4.add(inputNom4);
        mainPanel.add(nomPanel4);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        // Bouton confirmation
        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        btnConfirmer = new JButton("Confirmer");
        btnConfirmer.setFont(new Font("SansSerif", Font.BOLD, 18));
        btnConfirmer.setBackground(new Color(0x4CAF50));
        btnConfirmer.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnConfirmer.setPreferredSize(new Dimension(150, 40));
        buttonPanel.add(btnConfirmer);
        mainPanel.add(buttonPanel);

        JLabel jLabel = new JLabel(new ImageIcon("projet/jeux/assets/mer.jpg"));
        jLabel.setLayout(new BorderLayout());
        mainPanel.setOpaque(false);
        setOpaque(false);
        jLabel.add(mainPanel, BorderLayout.CENTER);
        add(jLabel);
    }

    public String getNomJoueur1() {
        return inputNom1.getText();
    }

    public String getNomJoueur2() {
        return inputNom2.getText();
    }

    public String getNomJoueur3() {
        return inputNom3.getText();
    }

    public String getNomJoueur4() {
        return inputNom4.getText();
    }

    public void setAction(Runnable action) {
        btnConfirmer.addActionListener(e -> {
            // vérifier si les champs sont vides
            if (getNomJoueur1().isEmpty() || getNomJoueur2().isEmpty() || getNomJoueur3().isEmpty() || getNomJoueur4().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Tous les noms des joueurs doivent être remplis!", "Erreur", JOptionPane.ERROR_MESSAGE);
            } else {
                action.run();
            }
        });
    }
}
