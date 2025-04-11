package projet.jeux;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;
import java.util.Scanner;

import projet.jeux.controleur.ControleurJeu;
import projet.jeux.controleur.ControleurJoueur;
import projet.jeux.modele.ModeleJoueur;
import projet.jeux.modele.ModelePion;
import projet.jeux.modele.ModelePlateau;
import projet.jeux.modele.ModeleCase;
import projet.jeux.modele.ModeleEvent;

public class Jeux {

    public static void main(String[] args) {
        ControleurJoueur initJoueur = new ControleurJoueur(3);
        new ControleurJeu(initJoueur.ensembleJoueur);
    }

}
