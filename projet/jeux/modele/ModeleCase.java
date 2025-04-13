package projet.jeux.modele;

import java.awt.*;
import java.util.Random;

/**
 * La classe contenant toutes les infos de case.
 */
public class ModeleCase {
    private Color couleur;
    private final int coeff;
    private final int estCaseSpe;
    private static int nbCaseSpe = 0;
    private ModeleEvent caseEvent;
    public ModeleCase() {
        coeff = new Random().nextInt(10);
        estCaseSpe = new Random().nextInt(0,2);
        couleur = estCaseSpe() ? Color.RED : Color.BLACK;
    }

    public Color getCouleur() {
        return couleur;
    }

    public int getCoeff() {
        return coeff;
    }

    public String runEvent() {
        return caseEvent.getMsg();
    }

    public String getNomEvent() {
        return caseEvent.getNomEvent();
    }
    public boolean estCaseSpe() {
        // 6 cases spéciale maxmum
        if (estCaseSpe == 1 && nbCaseSpe < 6) {
            nbCaseSpe++;
            caseEvent = new ModeleEvent();
            return true;
        }
        return false;
        
    }
}
