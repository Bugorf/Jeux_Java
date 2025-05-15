package projet.jeux.controleur;

import projet.jeux.modele.*;
import projet.jeux.vue.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class ControleurJeu {
    private boolean fini = false;
    private ModeleJoueur joueurActuel;
    private ArrayList<ModeleJoueur> ensembleJoueur;
    private final ModelePlateau plateau;
    private ModeleDe de;
    private VueAccueil vueAccueil;
    private VueJeu vueJeu;
    private VuePlateau vuePlateau;

    private VueJoueur vueJoueur;

    // TODO: Comparer cette méthode avec HashMap
    public ControleurJeu() {
        plateau = new ModelePlateau();
        de = new ModeleDe();
        ensembleJoueur = new ArrayList<>(4);

        ModeleParam modeleParam = new ModeleParam();


        VueMenu vueMenu = new VueMenu();
        VueInterm vueInterm = new VueInterm();
        VueParam vueParam = new VueParam(modeleParam);

        vueAccueil = new VueAccueil(vueInterm,vueParam);


        vueInterm.setAction(() -> {
            ModeleJoueur joueur;
            for (int i = 0; i < 4; i++) {
                switch (i) {
                    case 0:
                        joueur = new ModeleJoueur(vueInterm.getNomJoueur1(), Color.YELLOW);
                        ensembleJoueur.add(joueur);
                        break;
                    case 1:
                        joueur = new ModeleJoueur(vueInterm.getNomJoueur2(), Color.BLUE);
                        ensembleJoueur.add(joueur);
                        break;
                    case 2:
                        joueur = new ModeleJoueur(vueInterm.getNomJoueur3(), Color.ORANGE);
                        ensembleJoueur.add(joueur);
                        break;
                    case 3:
                        joueur = new ModeleJoueur(vueInterm.getNomJoueur4(), Color.GREEN);
                        ensembleJoueur.add(joueur);
                        break;
                }

            }
            joueurActuel = ensembleJoueur.getFirst();

            vuePlateau = new VuePlateau(plateau, ensembleJoueur, joueurActuel);
            vuePlateau.setBtnDe(this::startJeux);
            vueJeu = new VueJeu(vuePlateau, vueMenu);
            vueAccueil.switchView(vueJeu);
        });


    }
    private void startJeux() {
        if (fini) {
            System.out.println("Jeu fini");
            return;
        }

        int chiffreDe = de.lancer();
        int choix = new VueDialogue(vueAccueil.getFrame()).getChoix();

        // Effacer le dernier pion avec le meme couleur
        if (joueurActuel.ensemblePion.get(choix).getPosition() != 0) {
            vuePlateau.getEnsembleCase().get(joueurActuel.ensemblePion.get(choix).getPosition()).clearPionPosition();
        }
        // Maj la position
        joueurActuel.ensemblePion.get(choix).setPosition(chiffreDe + joueurActuel.ensemblePion.get(choix).getPosition());
        //vueJoueur.setPos(choix, joueurActuel.ensemblePion.get(choix).getPosition());

        int posCase = joueurActuel.ensemblePion.get(choix).getPosition();

        while (!(vuePlateau.getEnsembleCase().get(posCase).getInnerColor().equals(Color.WHITE))) {
            posCase++;
        }
        joueurActuel.ensemblePion.get(choix).setPosition(posCase);
        vuePlateau.getEnsembleCase().get(posCase).setPionPosition(joueurActuel.ensemblePion.get(choix).getChiffre(),joueurActuel.getCouleur());

        // Vérifier si le joueur arrive à une case spéciale
        ModeleCase caseActuel = plateau.ensembleCase.get(posCase);
        ModelePion pionActuel = joueurActuel.ensemblePion.get(choix);
        if (caseActuel.getNbCaseChiffre() == 1) {
            // Case spéciale
            String desc = caseActuel.runEvent();
            String nomEvent = caseActuel.getNomEvent();
            String msg = "Dé: " + chiffreDe + "\nne joueur " + joueurActuel.getNom() + " a arrivé à une case spéciale\n Description: " + desc;

            new VueDialogue(vueAccueil.getFrame(),nomEvent,msg);
        } else {
            // Case normale
            String msg = "Dé: " + chiffreDe + "\nLe joueur " + joueurActuel.getNom() + " a arrivé à une case normale";
            new VueDialogue(vueAccueil.getFrame(),"Rien",msg);

            int chiffreCase = caseActuel.getCoeff();
            int coeffPion = pionActuel.getChiffre();
            joueurActuel.setScore(chiffreCase * coeffPion);
        }

        // Condition de victoire
        if (pionActuel.getPosition() >= 30) {
            new VueDialogue(vueAccueil.getFrame(),"Gagné","Jeu terminé");

            // Classer les joueurs en utilisant référance de méthode
            System.out.println("Nom         Score       position");
            ensembleJoueur.sort(Comparator.comparingInt(ModeleJoueur::getScore).reversed());
            ensembleJoueur.forEach(x -> {
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
        vuePlateau.updateJoueurActuel(joueurActuel);
        //updateVue();
    }
/*
    public void updateVue() {
        vueJoueur = new VueJoueur(joueurActuel);
        vueJoueur.setBtnDe(this::startJeux);
        vueJeu.setVueJoueur(vueJoueur);
    }


 */
}
