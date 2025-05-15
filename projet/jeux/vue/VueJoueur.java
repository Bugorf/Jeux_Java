package projet.jeux.vue;

import projet.jeux.modele.ModeleDe;
import projet.jeux.modele.ModeleJoueur;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;

public class VueJoueur extends JPanel {
    private JLabel[] labelPos = new JLabel[6];
    public JButton de;
    public VueJoueur(ModeleJoueur modeleJoueur) {
        setLayout(new GridBagLayout());

        JPanel panelTotal = new JPanel();
        panelTotal.setLayout(new GridLayout(3,1, 10,10));
        JLabel label = new JLabel(modeleJoueur.getNom());
        JPanel panelPion = new JPanel(new GridLayout(2, 3,10,10));

        for (int i = 0; i < labelPos.length; i++) {
            labelPos[i] = new JLabel(String.valueOf(modeleJoueur.ensemblePion.get(i).getPosition()));
        }

        for (int i = 0; i < modeleJoueur.ensemblePion.size(); i++) {
            JPanel panel = new JPanel();
            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
            panel.add(Box.createVerticalStrut(10));

            JLabel labelPion = new JLabel("Pion" + (i+1));
            labelPion.setAlignmentX(Component.CENTER_ALIGNMENT);
            panel.add(labelPion);


            panel.add(labelPos[i]);
            panel.setBorder(BorderFactory.createCompoundBorder(
                    new LineBorder(Color.BLACK, 1, true),
                    new EmptyBorder(5, 10, 5, 10)
            ));
            panelPion.add(panel);
        }


        label.setFont(new Font("SansSerif", Font.BOLD, 30));
        label.setForeground(modeleJoueur.getCouleur());
        label.setHorizontalAlignment(SwingConstants.CENTER);

        de = new JButton("🎲");
        de.setFont(new Font("SansSerif", Font.BOLD, 70));

        panelTotal.add(label);
        panelTotal.add(panelPion);
        panelTotal.add(de);

        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        add(panelTotal);

    }

    public void setPos(int pos, int valeur) {
        labelPos[pos].setText(String.valueOf(valeur));
    }

    public void setBtnDe(Runnable action) {
        de.addActionListener(e -> action.run());
    }
}
