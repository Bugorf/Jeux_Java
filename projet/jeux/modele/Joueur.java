package projet.jeux.modele;
import java.awt.*;

public class Joueur{
    private int score;
    private String pseudo;
    private Pion[] pionTab;
    public final Color couleur;
    private Dé dé;



    public Joueur(String ps, Color col){
        this.pseudo = ps;
        this.score = 0;
        this.couleur = col;
        this.dé = new Dé();
        creerPion();
    }

    public boolean verifierPion(Pion pion){
        if (pion.getValeur() == lancerDé()){
            return true;
        }
        return false;
    } //Verifie que int dé = somme(pion)


    public void creerPion(){
        this.pionTab = new Pion[6];
        for(int i = 0; i<pionTab.length; i++){
            Pion pion = new Pion(i+1);
            pionTab[i] = pion;
            pion.couleur = couleur;
        }

    }

    public int lancerDé(){
        return dé.lancer() + dé.lancer();
    }


    public String getPseudo(){
        return pseudo;
    }

    public int getScore(){
        return score;
    }

    /*public Color getCouleur(){ //pas tres utile car couleur est public
        return couleur;
    }*/



}