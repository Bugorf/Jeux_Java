package projet.jeux.partieThiery;

import java.util.ArrayList;

public abstract class ModeleJoueur {
    protected String nom;
    protected int score;
    protected ArrayList<ModelePion> ensemnlePion = new ArrayList<>(6);

    public ArrayList<ModelePion> getEnsemblePion() {
        return ensemnlePion;
    }

    public String getNom() {return nom;}
    public void setScore(int score) {this.score += score;}
    public int getScore() {return score;}
}
