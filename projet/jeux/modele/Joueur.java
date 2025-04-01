package projet.jeux.modele;
import java.awt.*;

public class Joueur{
    int score;
    String pseudo;
    Pion[] pionTab;
    Color couleur;



    public Joueur(String ps, Color col){
        this.pseudo = ps;
        this.score = 0;
        this.couleur = col;
    }

    public void verifierPion(Pion pion){} //Verifie que int dé = somme(pion)

    /*public void choisirPion(Pion pion ){
        if(plateau[pion.position+1] == 0){ //TODO
            pion.position++;
        }

    } //permet au joueur de choisir le pion,

    public int score(){

        return score;
    } //donne le score litteralement*/

    public void creerPion(){
        this.pionTab = new Pion[6];
        for(int i = 0; i<pionTab.length; i++){
            Pion pion = new Pion(i);
            pionTab[i] = pion;
            pion.couleur = couleur;
        }

    }

}