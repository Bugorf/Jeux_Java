package projet.jeux.modele;

public class ModeleParam {

    private String difficulte = "Facile";
    private boolean estModeNuit = false;

    public String getDifficulte() {
        return difficulte;
    }

    public void setDifficulte(String difficulte) {
        this.difficulte = difficulte;
    }

    public boolean estModeNuit() {
        return estModeNuit;
    }
    public void setModeNuit(boolean ModeNuit) {
        estModeNuit = ModeNuit;
    }


}
