package projet.jeux.vue;

import projet.jeux.modele.ModeleDe;
import projet.jeux.modele.ModeleJoueur;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class VueJoueur extends JPanel {

    public VueJoueur(ModeleJoueur modeleJoueur) {
        setLayout(new GridBagLayout());

        JPanel panelTotal = new JPanel();
        panelTotal.setLayout(new BoxLayout(panelTotal, BoxLayout.Y_AXIS));
        JLabel label = new JLabel(modeleJoueur.getNom());
        JPanel panelPion = new JPanel(new GridLayout(2, 3,10,10));

        modeleJoueur.ensemblePion.forEach(e -> {
            JPanel panel = new JPanel();
            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
            panel.add(Box.createVerticalStrut(10));
            panel.add(new JLabel("Pion" + e.getChiffre()));
            panel.add(new JLabel(String.valueOf(e.getPosition())));
            panelPion.add(panel);
        });

        label.setFont(new Font("SansSerif", Font.BOLD, 30));
        label.setForeground(Color.RED);
        JButton de = new JButton("Dé");
        de.addActionListener(e -> {
            ModeleDe.lanceDe();
        });
        panelTotal.add(label);
        panelTotal.add(Box.createVerticalStrut(10));
        panelTotal.add(panelPion);
        panelTotal.add(de);

        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        add(panelTotal);



    }
}
