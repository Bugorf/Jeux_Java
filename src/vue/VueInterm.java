package src.vue;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * La classe intermédiaire pour collecter les noms des joueurs.
 */
public class VueInterm extends JPanel {
    private List<JTextField> inputText = new ArrayList<>();
    private JButton btnConfirmer;
    private List<String> listNom = new ArrayList<>();
    private JPanel inputPanel;
    private JButton btnRetour;

    public VueInterm() {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        JPanel panelRetour = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelRetour.setOpaque(false);
        btnRetour = new JButton("Retour");
        panelRetour.add(btnRetour);
        mainPanel.add(panelRetour);

        // Titre
        JLabel labelTitre = new JLabel("Veuillez choisir le nombre des joueurs: ");
        labelTitre.setFont(new Font("SansSerif", Font.BOLD, 24));
        labelTitre.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(labelTitre);

        JPanel radioPanel = new JPanel();
        List<JRadioButton> jRadioButtons = new ArrayList<>();
        ButtonGroup group = new ButtonGroup();

        for (int i = 2; i <= 4; i++) {
            JRadioButton radioButton = getJRadioButton(i);

            group.add(radioButton);
            jRadioButtons.add(radioButton);
            radioPanel.add(radioButton);
        }

        mainPanel.add(radioPanel);

        inputPanel = new JPanel();
        inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.Y_AXIS));

        // 1 joueur par défaut
        jRadioButtons.getFirst().setSelected(true);
        getJRadioButton(2).doClick();
        mainPanel.add(inputPanel);

        // Bouton confirmation
        JPanel buttonPanel = new JPanel();
        btnConfirmer = new JButton("Confirmer");
        btnConfirmer.setFont(new Font("SansSerif", Font.BOLD, 18));
        btnConfirmer.setBackground(new Color(0x4CAF50));
        btnConfirmer.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnConfirmer.setPreferredSize(new Dimension(150, 40));
        buttonPanel.add(btnConfirmer);
        mainPanel.add(buttonPanel);

        JLabel jLabel = new JLabel(new ImageIcon(Objects.requireNonNull(getClass().getResource("/src/ressources/images/mer.jpg"))));
        jLabel.setLayout(new BorderLayout());
        jLabel.add(mainPanel, BorderLayout.CENTER);

        mainPanel.setOpaque(false);
        inputPanel.setOpaque(false);
        radioPanel.setOpaque(false);
        buttonPanel.setOpaque(false);
        this.setOpaque(false);
        add(jLabel);
    }

    private JRadioButton getJRadioButton(int i) {
        JRadioButton radioButton = new JRadioButton(String.valueOf(i));
        int finalI = i;
        radioButton.addActionListener(e -> {
            inputText.clear();
            inputPanel.removeAll();
            inputPanel.revalidate();
            inputPanel.repaint();

            for (int j = 0; j < finalI; j++) {
                JPanel nomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
                nomPanel.setOpaque(false);
                JLabel labelNom = new JLabel("Joueur " + (j + 1) + ": ");
                JTextField inputNom = new JTextField();
                inputNom.setPreferredSize(new Dimension(250, 40));
                inputText.add(inputNom);
                nomPanel.add(labelNom);
                nomPanel.add(inputNom);
                inputPanel.add(nomPanel);
            }
        });
        return radioButton;
    }

    public String getNom(int numJoueur) {
        return listNom.get(numJoueur);
    }

    public int getNbJoueur() {
        return listNom.size();
    }

    public void setBtnConfimer(Runnable action) {
        btnConfirmer.addActionListener(e -> {
            //listNom.clear(); --> pour éviter la répétition
            listNom.clear();
            for (JTextField jTextField : inputText) {
                if (jTextField.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Tous les noms des joueurs doivent être remplis!", "Erreur", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                listNom.add(jTextField.getText());
            }

            for (int i = 0; i < listNom.size(); i++) {
                for (int j = i + 1; j < listNom.size(); j++) {
                    if (listNom.get(i).equals(listNom.get(j))) {
                        JOptionPane.showMessageDialog(this, "Veuillez ne pas saisir le meme nom", "Erreur", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }
            }
            action.run();
        });
    }

    public void setBtnRetour(Runnable action) {
        btnRetour.addActionListener(e -> {
            action.run();
        });
    }
}
