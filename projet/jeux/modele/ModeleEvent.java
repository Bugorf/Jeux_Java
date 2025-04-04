package projet.jeux.modele;

import java.util.Random;

public class ModeleEvent {
    private String nomEvent;

    public ModeleEvent(ModeleJoueur joueur) {
        switch (new Random().nextInt(4)) {
            case 0:
                nomEvent = "Prison";
                eventPrison(joueur);
                break;
            case 1:
                nomEvent = "AjoutPion";
                eventAjoutPion();
                break;
            case 2:
                nomEvent = "PertePion";
                eventPertePion();
                break;
            case 3:
                nomEvent = "Desupp";
                eventDeSupp();
                break;
            case 4:
                nomEvent = "Recul";
                eventRecul();
                break;
        }
    }

    //TODO: A compléter
    void eventPrison(ModeleJoueur joueur) {
        joueur.suspendu = true;
        System.out.println("Joueur " + joueur.getNom() + " a été suspendu");
    }

    void eventAjoutPion() {
        System.out.println("eventAjoutPion");
    }

    void eventPertePion() {
        System.out.println("eventPertePion");
    }

    void eventDeSupp() {
        System.out.println("eventPertePion");
    }

    void eventRecul() {
        System.out.println("eventPertePion");
    }

    String getNomEvent() {
        return nomEvent;
    }
}