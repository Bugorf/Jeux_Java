package src.modele;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * La classe de plateau contenant un ensemble de case et un hashmap de position de case
 *
 */
public class ModelePlateau {

    public ArrayList<ModeleCase> ensembleCase = new ArrayList<>(36);
    // La position de chaque case, on l'utilise pour dessiner la trajectoire des cases.
    public HashMap<Integer, int[]> posCase = new HashMap<>();

    public ModelePlateau() {
        int numeroCase = 0;
        int frontiereGauche = 0;
        int frontiereDroite = 5;
        int frontiereHaute = 0;
        int frontiereBasse = 5;

        while (numeroCase < 36) {
            // Parcours haut
            for (int i = frontiereGauche; i <= frontiereDroite; i++) {
                ajouterCase(numeroCase, numeroCase++, frontiereHaute, i);
            }
            frontiereHaute++;

            // Parcours droite
            for (int i = frontiereHaute; i <= frontiereBasse; i++) {
                ajouterCase(numeroCase, numeroCase++, i, frontiereDroite);
            }
            frontiereDroite--;

            // Parcours bas
            for (int i = frontiereDroite; i >= frontiereGauche; i--) {
                ajouterCase(numeroCase, numeroCase++, frontiereBasse, i);
            }
            frontiereBasse--;

            // Parcours gauche
            for (int i = frontiereBasse; i >= frontiereHaute; i--) {
                ajouterCase(numeroCase, numeroCase++, i, frontiereGauche);
            }
            frontiereGauche++;
        }

        ensembleCase.get(32).setCoeff(20);
        ensembleCase.get(33).setCoeff(25);
        ensembleCase.get(34).setCoeff(30);
        ensembleCase.get(15).setAvecObstacle();
        ensembleCase.get(23).setAvecObstacle();
    }

    private void ajouterCase(int numeroCase, int numCase, int x, int y) {
        ModeleCase modeleCase = new ModeleCase(numCase);
        ensembleCase.add(modeleCase);
        posCase.put(numeroCase, new int[]{x, y});
    }
}
