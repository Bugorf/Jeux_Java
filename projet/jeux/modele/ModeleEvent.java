package projet.jeux.modele;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.function.Supplier;

public class ModeleEvent {
    private String nomEvent;
    private Supplier<String> event;
    private final HashMap<String, Supplier<String>> eventList = new HashMap<>();

    public ModeleEvent() {
        eventList.put("Prison", this::eventPrison);
        eventList.put("AjoutPion", this::eventAjoutPion);
        eventList.put("PertePion", this::eventPertePion);
        eventList.put("DeSupp", this::eventDeSupp);
        eventList.put("Recul", this::eventRecul);

        event = randomEvent();
    }

    private String eventPrison() {
        return "Événement Prison";
    }

    private String eventAjoutPion() {
        return "Événement Ajout Pion";
    }

    private String eventPertePion() {
        return "Événement Perte Pion";
    }

    private String eventDeSupp() {
        return "Événement De Suppression";
    }

    private String eventRecul() {
        return "Événement Recul";
    }

    public String getNomEvent() {
        return nomEvent;
    }

    public String getEventMessage() {
        return event.get();
    }

    private Supplier<String> randomEvent() {
        var entryList = new ArrayList<>(eventList.entrySet());

        Random rand = new Random();
        int randomIndex = rand.nextInt(entryList.size());
        Map.Entry<String, Supplier<String>> entry = entryList.get(randomIndex);

        nomEvent = entry.getKey();
        event = entry.getValue();

        return event;
    }

    public String getMsg() {
        randomEvent();
        return event.get();
    }
}