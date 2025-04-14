package projet.jeux.modele;

import java.awt.Color;
import java.util.ArrayList;

public class ModeleJoueur {
    public boolean suspendu = false;
    private String nom;
    private int score;
    private Color couleur;
    public ArrayList<ModelePion> ensemblePion = new ArrayList<ModelePion>(6);

    public ModeleJoueur(String nom, Color couleur) {
        this.couleur= couleur;
        this.nom = nom;
        for (int i = 1; i <= 6; i++) {
            ensemblePion.add(new ModelePion(i, couleur));
        }
    }

    public String getNom() {
        return nom;
    }

    public void setScore(int s) {
        score += s;
    }

    public int getScore() {
        return score;
    }

    public Color getCouleur() {
        return couleur;
    }

}