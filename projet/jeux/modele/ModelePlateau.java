package projet.jeux.modele;
import java.awt.Color;
import java.util.ArrayList;

public class ModelePlateau {

    public ArrayList<ModeleCase> ensembleCase = new ArrayList<ModeleCase>(30);

    public ModelePlateau () {
        for (int i = 0; i < 30; i++) {
            ModeleCase modeleCase = new ModeleCase();
            Color c = modeleCase.estCaseSpe() ? Color.RED : Color.YELLOW;
            modeleCase.setCouleur(c);
            ensembleCase.add(modeleCase);
        }

    }

}