package projet.jeux.controleur;

import projet.jeux.modele.ModeleJoueur;

import java.awt.*;
import java.util.ArrayList;
import java.util.Scanner;

public class ControleurJoueur {
    public Scanner input = new Scanner(System.in);
    public ArrayList<ModeleJoueur> ensembleJoueur;
    public ControleurJoueur(int nbJoueur) {
        ensembleJoueur = new ArrayList<ModeleJoueur>(nbJoueur);
        for (int i = 0; i < nbJoueur; i++) {
            System.out.println("Veuillez entrer un nom du joueur: ");
            String nom = input.nextLine();
            ensembleJoueur.add(new ModeleJoueur(nom, Color.RED));
        }
    }
}
