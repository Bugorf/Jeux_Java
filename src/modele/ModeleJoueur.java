package src.modele;

import java.awt.*;
import java.util.ArrayList;

/**
 * La classe qui contient toutes les infos de joueur
 *
 */
public class ModeleJoueur {
    public ArrayList<ModelePion> ensemblePion = new ArrayList<ModelePion>(6);
    private boolean suspendu = false;
    private boolean doubleDe = false;
    private boolean recul = false;
    private String nom;
    private int score;
    private boolean estDejaTroisCaseContinuous = false;
    private Color couleur;

    public ModeleJoueur(String nom, Color couleur) {
        this.couleur = couleur;
        this.nom = nom;
        for (int i = 1; i <= 6; i++) {
            ensemblePion.add(new ModelePion(i, couleur));
        }
    }

    public String getNom() {
        return nom;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int s) {
        score += s;
    }

    public Color getCouleur() {
        return couleur;
    }

    public boolean estSuspendu() {
        return suspendu;
    }

    public void setSuspendu(boolean suspendu) {
        this.suspendu = suspendu;
    }


    public boolean estRecul() {
        return recul;
    }

    public void setRecul(boolean recul) {
        this.recul = recul;
    }

    public boolean estDejaTroisCaseContinuous() {
        return estDejaTroisCaseContinuous;
    }

    public void setEstDejaTroisCaseContinuous(boolean estDejaTroisCaseContinuous) {
        this.estDejaTroisCaseContinuous = estDejaTroisCaseContinuous;
    }
}