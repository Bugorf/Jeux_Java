package projet.jeux.partieThiery;

import javax.swing.*;

// ! Utiliser CardLayout !!
public interface VueAccueil {
    // Constructeur
    default JPanel vueAccueil(JPanel vueInterm, JPanel vueParam) {return null;};
    // Changement de vue
    default void setVue(String nomVue) {}
    default JFrame getFrame() {return null;}

}
