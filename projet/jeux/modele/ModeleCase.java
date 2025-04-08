package projet.jeux.modele;
import java.awt.Color;
import java.util.Random;

public class ModeleCase {
    private Color couleur;
    private final int coeff;        // TODO
    private final int estCaseSpe; // modifier caseSpe avec un random sur le nb de case
                                    // CaseSpé avec position non fixée

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
