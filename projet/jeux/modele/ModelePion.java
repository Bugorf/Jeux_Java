package projet.jeux.modele;

import java.awt.Color;

public class ModelePion {
    private int chiffre;
    private int position;
    private Color couleur;

    public ModelePion(int chiffre, Color couleur) {
        this.chiffre = chiffre;
        this.couleur = couleur;
        position = 0;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int p) {
        position = Math.min(p, 30);
    }

    public int getChiffre() {
        return chiffre;
    }

    public Color getCouleur() {
        return couleur;
    }
}
