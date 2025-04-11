package projet.jeux.controleur;
import projet.jeux.modele.*;
import projet.jeux.vue.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;
import java.util.Scanner;

public class ControleurJeu {
    public boolean fini = false;
    public ModeleJoueur joueurActuel;
    public ArrayList<ModeleJoueur> ensembleJoueur;
    public int de;


    // TODO: Comparer cette méthode avec HashMap
    public ControleurJeu(ArrayList<ModeleJoueur> ensembleJoueur) {
        this.ensembleJoueur = ensembleJoueur;
        joueurActuel = ensembleJoueur.getFirst();

        ModelePlateau modelePlateau = new ModelePlateau();
        ModeleJoueur modeleJoueur = joueurActuel;
        ModeleParam modeleParam = new ModeleParam();

        new VueAccueil(modelePlateau, modeleJoueur,modeleParam, ensembleJoueur);
    }

    public void startJeux() {
        System.out.println("Initialisation du plateau terminé !");
        Scanner input = new Scanner(System.in);
        while (!fini) {
            ModelePlateau plateau = new ModelePlateau();

            de = ModeleDe.getValueDe();
            // System.out.println("En attendant que le joueur " + joueurActuel.getNom() + " lance les dés (Taper la touche enter pour continuer)");

            // TODO: à modifier
            // input.nextLine();
            // System.out.println("Le joueur " + joueurActuel.getNom() + " a obtenu " + de);

            // System.out.println("Veuillez choisir un pion pour partir: ");
            // System.out.println("Numéro : Position");
//            joueurActuel.ensemblePion.forEach(x -> {
//                System.out.printf("Pion %d : %d%n", x.getChiffre(), x.getPosition());
//            });

            // Maj(mettre à jour) la position
            int choix = 1;
            joueurActuel.ensemblePion.get(choix).setPosition(de);

            // Vérifier si le joueur arrive à une case spéciale
            int position = joueurActuel.ensemblePion.get(choix).getPosition();
            ModeleCase caseActuel = plateau.ensembleCase.get(position);
            ModelePion pionActuel = joueurActuel.ensemblePion.get(choix);
            if (caseActuel.estCaseSpe()) {
                // Case spéciale
                System.out.println("Le joueur " + joueurActuel.getNom() + " a arrivé à une case spéciale");
                new ModeleEvent(joueurActuel);
            } else {
                // Case normale
                System.out.println("Le joueur " + joueurActuel.getNom() + " a arrivé à une case normale");
                int chiffreCase = caseActuel.getCoeff();
                int coeffPion = pionActuel.getChiffre();
                joueurActuel.setScore(chiffreCase * coeffPion);
            }

            // Condition de victoire
            if (pionActuel.getPosition() >= 30) {
                System.out.println("Jeux terminé, Voici le classement des joueur: ");

                // Classer les joueurs en utilisant référance de méthode
                System.out.println("Nom         Score       position");
                ensembleJoueur.sort(Comparator.comparingInt(ModeleJoueur::getScore).reversed());
                ensembleJoueur.forEach(x-> {
                    x.ensemblePion.sort(Comparator.comparingInt(ModelePion::getPosition).reversed());
                });
                ensembleJoueur.forEach(x -> System.out.printf("%d. %s        %d      pion%d -> %d%n", ensembleJoueur.indexOf(x) + 1, x.getNom(), x.getScore(), x.ensemblePion.getFirst().getChiffre(), x.ensemblePion.getFirst().getPosition()));
                fini = true;
            }

            // Changer le joueur
            int numeroJoueur = ensembleJoueur.indexOf(joueurActuel);
            if (numeroJoueur == ensembleJoueur.size() - 1) {
                joueurActuel = ensembleJoueur.getFirst();
            } else {
                joueurActuel = ensembleJoueur.get(numeroJoueur + 1);
            }

        }
    }

    public void MajPlateau() {

    }
}
