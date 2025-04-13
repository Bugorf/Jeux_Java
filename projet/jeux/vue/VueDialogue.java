package projet.jeux.vue;

import projet.jeux.partieThiery.ModelePion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class VueDialogue extends JDialog {
    private JRadioButton[] radioButtons = new JRadioButton[6];
    private ButtonGroup buttonGroup = new ButtonGroup();
    private int choix;

    public VueDialogue(Frame parent) {
        super(parent, true);
        initUI();
        pack();
        setLocationRelativeTo(parent);
    }

    public VueDialogue(Frame parent, String title, String message) {
        JOptionPane.showMessageDialog(parent, message, title, JOptionPane.INFORMATION_MESSAGE);
    }

    private void initUI() {
        setLayout(new BorderLayout(10, 10));

        JLabel titre = new JLabel("Choix", SwingConstants.CENTER);
        titre.setFont(new Font("SansSerif", Font.BOLD, 16));
        add(titre, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(2, 3, 5, 5));

        for (int i = 0; i < 6; i++) {
            JPanel choixPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            JLabel label = new JLabel("Pion " + (i + 1));
            radioButtons[i] = new JRadioButton();
            buttonGroup.add(radioButtons[i]);
            choixPanel.add(label);
            choixPanel.add(radioButtons[i]);
            centerPanel.add(choixPanel);
        }

        add(centerPanel, BorderLayout.CENTER);

        JButton btnConfirmation = new JButton("OK");
        btnConfirmation.addActionListener(x -> {
            for (int i = 0; i < radioButtons.length; i++) {
                if (radioButtons[i].isSelected()) {
                    choix = i + 1;
                    break;
                }
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(btnConfirmation);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    public int getChoix() {
        return choix;
    }

}
