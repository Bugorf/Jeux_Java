package projet.jeux.modele;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;
import java.util.Scanner;

public class ModeleJeu {
    public boolean fini = false;
    public ModeleJoueur joueurActuel;
    public ArrayList<ModeleJoueur> ensembleJoueur;
    public int de;
    public Scanner input = new Scanner(System.in);

    // TODO: Comparer cette méthode avec HashMap
    public ModeleJeu(int nbJoueur) {
        ensembleJoueur = new ArrayList<ModeleJoueur>(nbJoueur);
        for (int i = 0; i < nbJoueur; i++) {
            System.out.println("Veuillez entrer un nom du joueur: ");
            String nom = input.nextLine();
            ensembleJoueur.add(new ModeleJoueur(nom, Color.RED));
        }
        
        joueurActuel = ensembleJoueur.getFirst();
    }

    public void startJeux() {
        System.out.println("Initialisation du plateau terminé !");
        Scanner input = new Scanner(System.in);
        while (!fini) {
            ModelePlateau plateau = new ModelePlateau();
            
            de = de();
            System.out.println("En attendant que le joueur " + joueurActuel.getNom() + " lance les dés (Taper la touche enter pour continuer)");
            
            // TODO: à modifier
            input.nextLine();
            System.out.println("Le joueur " + joueurActuel.getNom() + " a obtenu " + de);
            
            System.out.println("Veuillez choisir un pion pour partir: ");
            System.out.println("Numéro : Position");
            joueurActuel.ensemblePion.forEach(x -> {
                System.out.printf("Pion %d : %d%n", x.getChiffre(), x.getPosition());
            });

            // Maj(mettre à jour) la position
            int choix = input.nextInt() - 1;
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

    public int de() {
        return new Random().nextInt(1,6);
    }

    /* 
    public static void main(String[] args) {

        System.out.println("Veuillez entrer le nombre des joueurs");
        Scanner input = new Scanner(System.in);
        int nbJoueur = input.nextInt();
        
        Jeux jeux = new Jeux(nbJoueur);
        jeux.startJeux();
        
        input.close();


    }
        */
}
