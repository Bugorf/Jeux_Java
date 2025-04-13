package projet.jeux.controleur;

import projet.jeux.modele.ModeleCase;
import projet.jeux.modele.ModeleEvent;
import projet.jeux.modele.ModelePlateau;
import projet.jeux.partieThiery.*;
import projet.jeux.vue.VueDialogue;
import projet.jeux.vue.VueInterm;
import projet.jeux.vue.VueJeu;
import projet.jeux.vue.VuePlateau;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class ControleurJeu extends ModeleJoueur implements VueAccueil, VueJoueur, VueMenu, VueParam, ModeleDe {
    private boolean fini = false;
    private ModeleJoueur joueurActuel;
    private ArrayList<ModeleJoueur> ensembleJoueur;
    private final ModelePlateau plateau;
    private int de;

    // TODO: Comparer cette méthode avec HashMap
    public ControleurJeu() {
        plateau = new ModelePlateau();

        VueInterm vueInterm = new VueInterm();
        VuePlateau vuePlateau = new VuePlateau(plateau);

        // TODO👇: Modifier et remplacer cette partie
        JPanel vueJoueur = vueJoueur();
        JPanel vueMenu = vueMenu();
        JPanel vueParam = vueParam();
        vueAccueil(vueInterm, vueParam);

        VueJeu vueJeu = new VueJeu(vuePlateau, vueJoueur, vueMenu);

        // Événement de clic de bouton "confirmer" de vue intermédiaire
        vueInterm.setAction(() -> {
            VueAccueil.super.setVue("vueJeu");
            nom = vueInterm.getNomJoueur();
            if (fini) {
                System.out.println("Jeu fini");
            } else {
                startJeux();
            }
        });

        VueJoueur.super.setDeListener(this::startJeux);


        ensembleJoueur.add(this);
        joueurActuel = ensembleJoueur.getFirst();
        // TODO👆: Modifier et remplacer cette partie

    }

    public void startJeux() {
        de = ModeleDe.super.getChiffre();
        int choix = new VueDialogue(getFrame()).getChoix();

        // Maj la position
        joueurActuel.getEnsemblePion().get(choix).setPosition(de);

        // Vérifier si le joueur arrive à une case spéciale
        int position = joueurActuel.getEnsemblePion().get(choix).getPosition();
        ModeleCase caseActuel = plateau.ensembleCase.get(position);
        ModelePion pionActuel = joueurActuel.getEnsemblePion().get(choix);
        if (caseActuel.estCaseSpe()) {
            // Case spéciale
            String desc = caseActuel.runEvent();
            String nomEvent = caseActuel.getNomEvent();
            String msg = "Le joueur " + joueurActuel.getNom() + " a arrivé à une case spéciale\n Description: " + desc;

            new VueDialogue(getFrame(),nomEvent,msg);
        } else {
            // Case normale
            String msg = "Le joueur " + joueurActuel.getNom() + " a arrivé à une case normale";
            new VueDialogue(getFrame(),"Rien",msg);

            int chiffreCase = caseActuel.getCoeff();
            int coeffPion = pionActuel.getChiffre();
            joueurActuel.setScore(chiffreCase * coeffPion);
        }

        // Condition de victoire
        if (pionActuel.getPosition() >= 30) {
            new VueDialogue(getFrame(),"Gagné","Jeu terminé");

            // Classer les joueurs en utilisant référance de méthode
            System.out.println("Nom         Score       position");
            ensembleJoueur.sort(Comparator.comparingInt(ModeleJoueur::getScore).reversed());
            ensembleJoueur.forEach(x -> {
                x.getEnsemblePion().sort(Comparator.comparingInt(ModelePion::getPosition).reversed());
            });
            ensembleJoueur.forEach(x -> System.out.printf("%d. %s        %d      pion%d -> %d%n", ensembleJoueur.indexOf(x) + 1, x.getNom(), x.getScore(), x.getEnsemblePion().getFirst().getChiffre(), x.getEnsemblePion().getFirst().getPosition()));
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
