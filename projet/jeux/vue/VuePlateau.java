package projet.jeux.vue;


import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import projet.jeux.modele.ModelePlateau;

public class VuePlateau extends JPanel {

    public VuePlateau(ModelePlateau modelePlateau) {
        // Initialisation des conteneurs des cases
        GridBagLayout layoutPanel = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();
        setLayout(layoutPanel);

        for (int i = 0; i < modelePlateau.ensembleCase.size(); i++) {
            JPanel jp = new VueCase(modelePlateau.ensembleCase.get(i));
            int x = modelePlateau.posCase.get(i)[0];
            int y = modelePlateau.posCase.get(i)[1];

            gbc.gridx = x;
            gbc.gridy = y;
            gbc.insets = new Insets(20, 20, 20, 20);

            add(jp, gbc);
        }
    }
    

}
