package projet.jeux;

import java.awt.*;
import java.util.Random;
public enum EnumCouleur {
    GREEN(Color.GREEN),
    PURPLE(Color.PINK),
    BLUE(Color.BLUE);


    // S'il y a pas de paramètre (ex: RED) ---> pas besoin de constructeur
    private final Color color;
    EnumCouleur(Color c) {
        color = c;
    }

    // Non static -> manipulations sur des attributs d'enum
    public Color getColor() {
        return color;
    }

    // Static ---> Cette méthode est liée à la classe, pas les attributs
    public static Color couleurAleatoire() {
        EnumCouleur[] mesCouleurs = EnumCouleur.values();
        int len = mesCouleurs.length;
        return(mesCouleurs[new Random().nextInt(len)].getColor());
    }
}
