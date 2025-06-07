package src.vue;

import src.modele.ModeleParam;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.Objects;

/**
 * Vue de param
 */
public class VueParam extends JPanel {

    private JButton btnRetour;

    public VueParam(ModeleParam modeleParam) {

        setLayout(new BorderLayout());


        JPanel panelRetour = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelRetour.setOpaque(false);
        btnRetour = new JButton("Retour");
        panelRetour.add(btnRetour);

        JPanel mainPanel = new JPanel(new GridLayout(1, 2, 20, 0));
        mainPanel.setOpaque(false);
        mainPanel.setBorder(new EmptyBorder(40, 40, 40, 40));

        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        leftPanel.setBorder(BorderFactory.createTitledBorder("Difficulté"));
        leftPanel.setOpaque(false);

        JLabel labelDifficulte = new JLabel("Choisir la difficulté :");
        labelDifficulte.setFont(new Font("SansSerif", Font.PLAIN, 16));
        labelDifficulte.setAlignmentX(Component.LEFT_ALIGNMENT);

        String[] niveaux = {"Facile", "Moyen", "Difficile"};
        JComboBox<String> comboDifficulte = new JComboBox<>(niveaux);
        comboDifficulte.setMaximumSize(new Dimension(200, 30));
        comboDifficulte.setAlignmentX(Component.LEFT_ALIGNMENT);
        comboDifficulte.setSelectedItem(modeleParam.getDifficulte());

        comboDifficulte.addActionListener(e -> {
            modeleParam.setDifficulte((String) comboDifficulte.getSelectedItem());
        });

        leftPanel.add(labelDifficulte);
        leftPanel.add(Box.createVerticalStrut(10));
        leftPanel.add(comboDifficulte);

        mainPanel.add(leftPanel);

        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setOpaque(false);
        contentPanel.add(panelRetour, BorderLayout.NORTH);
        contentPanel.add(mainPanel, BorderLayout.CENTER);

        JLabel backgroundLabel = new JLabel(new ImageIcon(Objects.requireNonNull(getClass().getResource("/src/ressources/images/mer.jpg"))));
        backgroundLabel.setLayout(new BorderLayout());

        backgroundLabel.add(contentPanel, BorderLayout.CENTER);

        add(backgroundLabel, BorderLayout.CENTER);

    }


    public void setAction(Runnable action) {
        btnRetour.addActionListener(e -> {
            action.run();
        });
    }
}
