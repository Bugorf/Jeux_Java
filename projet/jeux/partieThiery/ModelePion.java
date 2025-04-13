package projet.jeux.partieThiery;

public interface ModelePion {
    default int getPosition() {return 0;}
    default int getChiffre() {return 0;}
    default void setPosition(int i) {}
}
