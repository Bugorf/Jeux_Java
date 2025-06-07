package src.modele;

import java.util.Random;

/**
 *  La classe du événement
 *
 */
public class ModeleEvent {
    private String event;

    public ModeleEvent() {
        switch (new Random().nextInt(2)) {
            case 0:
                event = "Prison";
                break;
            case 1:
                event = "Recul";
                break;
        }

    }

    public String getEvent() {
        return event;
    }

}