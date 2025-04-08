package projet.jeux.modele;
import java.awt.Color;
import java.util.Random;

public class ModeleCase {
    private Color couleur;
    private final int coeff;
    private final int estCaseSpe;
    private static int nbCaseSpe = 0;
    public ModeleCase() {
        coeff = new Random().nextInt(10);
        estCaseSpe = new Random().nextInt(0,2);
        couleur = estCaseSpe() ? Color.RED : Color.BLACK;

    }

    public Color getCouleur() {
        return couleur;
    }

    public boolean estCaseSpe() {
        // 6 cases spéciale maxmum
        if (estCaseSpe == 1 && nbCaseSpe < 6) {
            nbCaseSpe++;
            return true;
        }
        return false;
        
    }

    public int getCoeff() {
        return coeff;
    }
}
