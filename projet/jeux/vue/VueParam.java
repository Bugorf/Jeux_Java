package projet.jeux.vue;

import projet.jeux.modele.ModeleParam;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class VueParam extends JPanel {

    public VueParam(ModeleParam modeleParam) {
        setLayout(new GridLayout(1, 2, 20, 0));
        setBorder(new EmptyBorder(40, 40, 40, 40));

        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        leftPanel.setBorder(BorderFactory.createTitledBorder("Difficulté"));

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

        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        rightPanel.setBorder(BorderFactory.createTitledBorder("Apparence"));

        JCheckBox nightModeSwitch = new JCheckBox("Activer le mode nuit");
        nightModeSwitch.setFont(new Font("SansSerif", Font.PLAIN, 16));
        nightModeSwitch.setSelected(modeleParam.estModeNuit());
        nightModeSwitch.setAlignmentX(Component.LEFT_ALIGNMENT);

        nightModeSwitch.addActionListener(e -> {
            modeleParam.setModeNuit(nightModeSwitch.isSelected());
        });

        rightPanel.add(Box.createVerticalStrut(10));
        rightPanel.add(nightModeSwitch);

        add(leftPanel);
        add(rightPanel);

    }
}
