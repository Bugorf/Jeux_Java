package projet.jeux.modele;
import java.awt.Color;
import java.util.Random;

public class ModeleCase {
    private Color couleur;
    private final int coeff;
    private final int estCaseSpe;

    public ModeleCase() {
        coeff = new Random().nextInt(10);
        estCaseSpe = new Random().nextInt(0,2);

    }

    public void setCouleur(Color couleur) {
        this.couleur = couleur;
    }

    public boolean estCaseSpe() {
        return estCaseSpe == 1;
    }

    public int getCoeff() {
        return coeff;
    }
}
