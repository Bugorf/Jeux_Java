package projet.jeux.vue;


import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import projet.jeux.modele.ModelePlateau;

public class VuePlateau {

    public VuePlateau(ModelePlateau modelePlateau) {
        // Initialisation des conteneurs des cases
        JFrame framePlateau = new JFrame();
        JPanel panelPlateau = new JPanel();

        BorderLayout layoutPanel = new BorderLayout();
        GridBagLayout layoutPlateau = new GridBagLayout(); 
        GridBagConstraints gbc = new GridBagConstraints();
        
        panelPlateau.setLayout(layoutPanel);
        framePlateau.setLayout(layoutPlateau);

        // Les cases
        for (int i = 0; i < modelePlateau.ensembleCase.size(); i++) {
            panelPlateau = new VueCase(modelePlateau.ensembleCase.get(i));
            int x = modelePlateau.posCase.get(i)[0];
            int y = modelePlateau.posCase.get(i)[1];

            gbc.gridx = x;
            gbc.gridy = y;
            gbc.insets = new Insets(10, 10, 10, 10);

            framePlateau.add(panelPlateau, gbc);
        }


        framePlateau.setSize(900,500);
        framePlateau.pack();
        framePlateau.setVisible(true);
    }
    

}
