package projet.jeux.modele;

import java.awt.*;
import java.util.Random;

/**
 * La classe contenant toutes les infos de case.
 */
public class ModeleCase {
    private Color couleur;
    private int coeff;
    private final int estCaseSpe;
    private static int nbCaseSpe = 0;
    private int avecChiffre;
    private int nbCaseChiffre = 0;
    private ModeleEvent caseEvent;
    public ModeleCase() {
        estCaseSpe = new Random().nextInt(0,2);
        avecChiffre = new Random().nextInt(0,2);
        coeff = avecChiffre() ? new Random().nextInt(15) : 0;
        couleur = estCaseSpe() ? Color.RED : Color.BLACK;
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
}
