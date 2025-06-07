
package src.controleur;

import src.modele.*;
import src.vue.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 *  La classe principale du jeu qui controle la logique du jeu ainsi que
 *  l'affichage et l'interaction avec utilisateur
 *
 */

public class ControleurJeu {
    private final ModelePlateau plateau;
    private ModeleJoueur joueurActuel;
    private ArrayList<ModeleJoueur> ensembleJoueur;
    private ModeleDe de;
    private VueAccueil vueAccueil;
    private VueJeu vueJeu;
    private VuePlateau vuePlateau;
    private int caseZeroPion;
    private boolean troisCasesContinues = false;

    // TODO: Comparer cette méthode avec HashMap
    public ControleurJeu() {
        plateau = new ModelePlateau();
        de = new ModeleDe();
        ensembleJoueur = new ArrayList<>(4);

        ModeleParam modeleParam = new ModeleParam();


        VueMenu vueMenu = new VueMenu();
        VueInterm vueInterm = new VueInterm();
        VueParam vueParam = new VueParam(modeleParam);

        vueAccueil = new VueAccueil(vueInterm, vueParam);

        vueInterm.setBtnConfimer(() -> {
            ModeleJoueur joueur;

            for (int i = 0; i < vueInterm.getNbJoueur(); i++) {
                switch (i) {
                    case 0:
                        joueur = new ModeleJoueur(vueInterm.getNom(0), Color.YELLOW);
                        ensembleJoueur.add(joueur);
                        break;
                    case 1:
                        joueur = new ModeleJoueur(vueInterm.getNom(1), Color.BLUE);
                        ensembleJoueur.add(joueur);
                        break;
                    case 2:
                        joueur = new ModeleJoueur(vueInterm.getNom(2), Color.ORANGE);
                        ensembleJoueur.add(joueur);
                        break;
                    case 3:
                        joueur = new ModeleJoueur(vueInterm.getNom(3), Color.GREEN);
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

        vueParam.setAction(() -> vueAccueil.switchView(vueAccueil));
        vueInterm.setBtnRetour(() -> vueAccueil.switchView(vueAccueil));
        vueMenu.setAction(
                () -> vueAccueil.switchView(vueInterm),
                () -> vueAccueil.switchView(vueAccueil),
                () -> vueAccueil.switchView(vueParam)
        );

    }

    private void startJeux() {
        int chiffreDe = de.lancer();

        if (joueurActuel.estSuspendu()) {
            joueurActuel.setSuspendu(false);
            JOptionPane.showMessageDialog(null, "Vous être suspendu !");
            changerJoueurActuel();
            return;
        }

        if (chiffreDe == 0) {
            deZero();
        } else {
            deNormal(chiffreDe);
        }

        if (troisCasesContinues && !joueurActuel.estDejaTroisCaseContinuous()) {
            JOptionPane.showMessageDialog(null, "Vous avez gagné un dé supplémentaire");
            joueurActuel.setEstDejaTroisCaseContinuous(true);
        } else {

            joueurActuel.setEstDejaTroisCaseContinuous(false);
            changerJoueurActuel();
            troisCasesContinues = false;
        }
    }

    public void changerJoueurActuel() {
        int numeroJoueur = ensembleJoueur.indexOf(joueurActuel);
        if (numeroJoueur == ensembleJoueur.size() - 1) {
            joueurActuel = ensembleJoueur.getFirst();
        } else {
            joueurActuel = ensembleJoueur.get(numeroJoueur + 1);
        }
        vuePlateau.updateJoueurActuel(joueurActuel);
    }

    public void deZero() {
        JOptionPane.showMessageDialog(null, "Vous avez obtenu un dé 0 !");
        int[] choixPionEtJoueur = new VueDialogue(vueAccueil.getFrame(), ensembleJoueur, joueurActuel, false).getChoixJoueur();
        int choixPion = choixPionEtJoueur[0];
        int choixJoueur = choixPionEtJoueur[1];

        recul(choixJoueur, choixPion);
    }

    public void deNormal(int chiffreDe) {
        List<Integer> choix = new VueDialogue(vueAccueil.getFrame(), chiffreDe).getChoix();

        for (Integer c : choix) {

            ModelePion pionActuel = joueurActuel.ensemblePion.get(c);
            int posPionDuJoueurActuel = pionActuel.getPosition();

            int posCaseLibre = plateau.ensembleCase.stream()
                    .filter(ModeleCase -> !ModeleCase.estOccupe() && ModeleCase.getNumCase() >= posPionDuJoueurActuel)
                    .toList().getFirst().getNumCase();

            // Maj la position

            pionActuel.setPosition(posCaseLibre);
            vuePlateau.getEnsembleCase().get(posCaseLibre).setPionPosition(pionActuel.getChiffre(), joueurActuel.getCouleur());

            // Maj le statu de case
            plateau.ensembleCase.get(posCaseLibre).estOccupe(true);

            // Effacer le dernier pion avec le meme couleur
            if (posCaseLibre != 0) {
                // vérifier si la couleur de première case est identique à celle de joueurActuel
                if (vuePlateau.getEnsembleCase().getFirst().getInnerColor().equals(joueurActuel.getCouleur()) && caseZeroPion == pionActuel.getChiffre()) {
                    vuePlateau.getEnsembleCase().getFirst().clearPionPosition();
                }

                if (posPionDuJoueurActuel != 0) {
                    vuePlateau.getEnsembleCase().get(posPionDuJoueurActuel).clearPionPosition();
                    plateau.ensembleCase.get(posPionDuJoueurActuel).estOccupe(false);
                }
            } else {
                caseZeroPion = pionActuel.getChiffre();
            }

            // Ajout de score
            int numeroPion = pionActuel.getChiffre();
            int coeffCase = plateau.ensembleCase.get(posCaseLibre).getCoeff();
            joueurActuel.setScore(numeroPion * coeffCase);

            troisCasesContinues = caseContinue(posCaseLibre);

            // Vérifier si le joueur arrive à une case spéciale
            ModeleCase caseActuel = plateau.ensembleCase.get(posCaseLibre);
            if (caseActuel.getNbCaseChiffre() == 1) {
                // Case spéciale
                String desc = caseActuel.runEvent(joueurActuel);
                String nomEvent = caseActuel.getNomEvent();
                String msg = "Le joueur " + joueurActuel.getNom() + " a arrivé à une case spéciale\nDescription: " + desc;

                new VueDialogue(vueAccueil.getFrame(), nomEvent, msg);
            } else {
                // Case normale
                String msg = "Le joueur " + joueurActuel.getNom() + " a arrivé à une case normale";
                new VueDialogue(vueAccueil.getFrame(), "Rien", msg);

            }

            // vérifier le statu d'attribut recul
            if (joueurActuel.estRecul()) {
                deZero();
            }
            estFini();

        }
    }

    public void recul(int choixJoueur, int choixPion) {
        ModelePion pionCible = ensembleJoueur.get(choixJoueur).ensemblePion.get(choixPion);
        ModeleJoueur joueurCible = ensembleJoueur.get(choixJoueur);

        int posPionCible = pionCible.getPosition();


        List<ModeleCase> newPosCase = plateau.ensembleCase.stream()
                .filter(ModeleCase -> !ModeleCase.estOccupe() && ModeleCase.getNumCase() < posPionCible)
                .toList();

        if (newPosCase.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Pas de Case disponible !");
        } else {
            int newPosCaseLibre = newPosCase.getLast().getNumCase();

            // Dessiner la nouvelle case d'abord
            pionCible.setPosition(newPosCaseLibre);
            vuePlateau.getEnsembleCase().get(newPosCaseLibre).setPionPosition(pionCible.getChiffre(), joueurCible.getCouleur());

            // Effacer l'ancienne
            vuePlateau.getEnsembleCase().get(posPionCible).clearPionPosition();
            plateau.ensembleCase.get(posPionCible).estOccupe(false);
        }

        joueurActuel.setRecul(false);
    }
    public boolean caseContinue(int posCase) {
        if (posCase == 0 || posCase == 1 || posCase == 2 || posCase == 33 || posCase == 34 || posCase == 35) {
            return false;
        }

        // 3 cas à traiter
        Color couleurCaseActuel = vuePlateau.getEnsembleCase().get(posCase).getInnerColor();
        Color couleurCasePrecedente1 = vuePlateau.getEnsembleCase().get(posCase - 1).getInnerColor();
        Color couleurCaseProchaine1 = vuePlateau.getEnsembleCase().get(posCase + 1).getInnerColor();

        Color couleurCaseProchaine2 = vuePlateau.getEnsembleCase().get(posCase + 2).getInnerColor();
        Color couleurCasePrecedente2 = vuePlateau.getEnsembleCase().get(posCase - 2).getInnerColor();


        return (couleurCaseActuel == couleurCasePrecedente1 && couleurCasePrecedente1 == couleurCaseProchaine1)
                || (couleurCaseActuel == couleurCasePrecedente1 && couleurCasePrecedente1 == couleurCasePrecedente2)
                || (couleurCaseActuel == couleurCaseProchaine1 && couleurCaseProchaine1 == couleurCaseProchaine2);
    }

    public void estFini() {
        if (plateau.ensembleCase.get(32).estOccupe() && plateau.ensembleCase.get(33).estOccupe() && plateau.ensembleCase.get(34).estOccupe()) {
            new VueDialogue(vueAccueil.getFrame(), ensembleJoueur, joueurActuel, true);
        }

    }

}
