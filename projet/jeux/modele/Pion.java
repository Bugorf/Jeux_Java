package projet.jeux.modele;
import java.awt.*;
public class Pion{
    private final int valeur;
    private int position;
    Color couleur;

    public Pion(int val){
        this.position = 0;
        this.valeur = val;
    }

    public int getPosition(){
        return position;
    }

    public int getValeur(){
        return valeur;
    }


} 