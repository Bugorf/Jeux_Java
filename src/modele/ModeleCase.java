package src.modele;

import java.awt.*;
import java.util.Random;

/**
 * La classe contenant toutes les infos de case.
 */
public class ModeleCase {
    private static int nbCaseSpe = 0;
    private final int numCase;
    private final int estCaseSpe;
    private Color couleur;
    private int coeff;
    private boolean estOccupe = false;
    private int avecChiffre;
    private int nbCaseChiffre = 0;
    private ModeleEvent caseEvent;
    private boolean avecObstacle;

    public ModeleCase(int numCase) {
        this.numCase = numCase;
        if (ModeleParam.getDifficulte() == "Difficile") {
            estCaseSpe = new Random().nextInt(0, 2);
        } else {
            estCaseSpe = new Random().nextInt(0, 4);
        }
        avecChiffre = new Random().nextInt(0, 2);
        coeff = avecChiffre() ? new Random().nextInt(15) : 0;
        couleur = estCaseSpe() ? Color.RED : Color.BLACK;
    }

    public int getNumCase() {
        return numCase;
    }

    public Color getCouleur() {
        return couleur;
    }

    public int getCoeff() {
        return coeff;
    }

    public void setCoeff(int coeff) {
        this.coeff = coeff;
    }

    public void estOccupe(boolean estOccupe) {
        this.estOccupe = estOccupe;
    }

    public boolean estOccupe() {
        return estOccupe;
    }

    public String runEvent(ModeleJoueur modeleJoueur) {
        if (caseEvent.getEvent().equals("Recul")) {
            modeleJoueur.setRecul(true);
        } else {
            modeleJoueur.setSuspendu(true);
        }
        return caseEvent.getEvent();
    }

    public String getNomEvent() {
        return caseEvent.getEvent();
    }

    public boolean estCaseSpe() {
        if (ModeleParam.getDifficulte() == "Difficile") {
            if (estCaseSpe == 1 && nbCaseSpe < 12) {
                nbCaseSpe++;
                caseEvent = new ModeleEvent();
                return true;
            }
        }

        // 6 cases spéciale maxmum
        if (estCaseSpe == 1 && nbCaseSpe < 6) {
            nbCaseSpe++;
            caseEvent = new ModeleEvent();
            return true;
        }
        return false;

    }

    public boolean avecChiffre() {
        // 15 cases avec chiffre maxmum
        if (avecChiffre == 1 && nbCaseChiffre < 15) {
            nbCaseChiffre++;
            return true;
        }
        return false;
    }

    public int getNbCaseChiffre() {
        return estCaseSpe;
    }

    public boolean estAvecObstacle() {
        return avecObstacle;
    }

    public void setAvecObstacle() {
        this.avecObstacle = true;
    }


}
