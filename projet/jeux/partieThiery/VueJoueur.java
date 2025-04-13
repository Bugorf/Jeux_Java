package projet.jeux.partieThiery;

import javax.swing.*;
import java.awt.event.ActionListener;

public interface VueJoueur {
    // Constructeur
    default JPanel vueJoueur() {return null;};
    // Listener de bouton de dé
    default void setDeListener(Runnable listener) {};
}
