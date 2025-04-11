package projet.jeux.modele;

import java.util.Random;

public class ModeleDe {

    private static int de;
    public static int lanceDe() {
        de = new Random().nextInt(1,6);
        return de;
    }

    public static int getValueDe() {
        return de;
    }

}
