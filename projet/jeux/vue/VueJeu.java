package projet.jeux.vue;

import projet.jeux.modele.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;

public class VueJeu extends JPanel {
    public boolean fini = false;
    public ArrayList<ModeleJoueur> ensembleJoueur;
    public int de;
    public VueJeu(ModelePlateau modelePlateau, ModeleJoueur modeleJoueur,ArrayList<ModeleJoueur> ensembleJoueur) {
        setLayout(new BorderLayout());

        this.ensembleJoueur = ensembleJoueur;
        VuePlateau vuePlateau = new VuePlateau(modelePlateau);
        VueJoueur vueJoueur = new VueJoueur(modeleJoueur);
        VueMenu vueMenu = new VueMenu();

        add(vueMenu, BorderLayout.NORTH);
        add(vuePlateau, BorderLayout.CENTER);
        add(vueJoueur, BorderLayout.EAST);

        vueJoueur.setOnConfirmer(() -> {
            startJeux(modelePlateau, modeleJoueur,vueJoueur);
        });

    }

    public void startJeux(ModelePlateau plateau, ModeleJoueur joueurActuel, VueJoueur vueJoueur) {

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
            vueJoueur.setPos(choix,de);

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
