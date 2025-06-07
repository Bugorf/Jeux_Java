package src.modele;


/**
 * Classe de la page de paramètre
 */
public class ModeleParam {

    private static String difficulte = "Facile";
    private static boolean estModeNuit = false; // Pour permettre d'avoir un meme etat pour toutes les fenetes

    public static String getDifficulte() {
        return difficulte;
    }

    public static void setDifficulte(String Difficulte) {
        Difficulte = difficulte;
    }


}
