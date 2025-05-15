package projet.jeux.vue;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class VueMenu extends JPanel{
    public VueMenu(){
        JPanel fen = new JPanel();
        fen.setLayout(new BorderLayout());
        fen.setSize(500,500);
        JMenuBar barre = new JMenuBar();
        JMenu Re = new JMenu("Retour  ▾");

        // Re.setIcon(new ImageIcon("/home/thierry/Documents/Java/Projet/Jeux_Java/projet/jeux/vue/image_MenuBarre/flecheRouge.png"));
        Re.setHorizontalTextPosition(JLabel.RIGHT);
        barre.add(Re);

        //Re.setIcon("");
        JMenu Para = new JMenu("Paramètre");
        barre.add(Para);
        // Para.setIcon(new ImageIcon("/home/thierry/Documents/Java/Projet/Jeux_Java/projet/jeux/vue/image_MenuBarre/para.png"));
        Para.setHorizontalTextPosition(JLabel.RIGHT);


        JMenu Ai = new JMenu("Aide  ▾");
        barre.add(Ai);
        // Ai.setIcon(new ImageIcon("/home/thierry/Documents/Java/Projet/Jeux_Java/projet/jeux/vue/image_MenuBarre/i.png"));
        Ai.setHorizontalTextPosition(JLabel.RIGHT);
        JMenuItem regles = new JMenuItem("Règles du jeu");
        JMenuItem Contact = new JMenuItem("Nous contacter");
        Ai.add(regles);
        Ai.add(Contact);

        Contact.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Veuillez nous contacter: jeuIncroyable@magie.fr"); //ca crée une fenetre p243 void showMessageDialog(String message, String titre)
            }
        });

        regles.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,
                        "But du jeu:\n Avancer ses pions sur le plateau pour marquer le plus de points.\n\n" + "Déroulement:\nChaque joueur a 6 pions numérotés de 1 à 6.\nIls commencent juste avant la première case du plateau.\nÀ son tour, un joueur lance un dé (valeurs de 1 à 6).\nIl choisit un ou plusieurs de ses pions dont la somme des numéros = le résultat du dé.\nExemple : si le dé donne 4 → il peut jouer le pion 4, ou 1+3, ou 2+2 (s’il en a deux).\nChaque pion sélectionné avance jusqu’à la prochaine case vide, en sautant les pions devant lui.\n\nFin du jeu:\nQuand les cases 20, 25 et 30 sont toutes occupées, la partie se termine.\n\nScore:\nSeules certaines cases ont des points.\nLe score = valeur de la case × numéro du pion.\nLe joueur avec le plus grand total gagne."
                ); //ca crée une fenetre p243 void showMessageDialog(Component, Object)
            }
        });


        JMenuItem ReStart = new JMenuItem("Recommencer jeu");
        Re.add(ReStart);

        ReStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VueInterm i = new VueInterm();
                i.setVisible(true);
            }
        });

        JMenuItem Accueil = new JMenuItem("Accueil");
        Re.add(Accueil);

        /*
        Accueil.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VueAccueil accueil = new VueAccueil();
                accueil.setVisible(true);
            }
        });
        */
        JMenuItem Quit = new JMenuItem("Quitter");

        Quit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Action déclenchée lors du clic
                System.exit(0); // quitte tout le programme
            }
        });

        Re.add(Quit);
        fen.add(barre, BorderLayout.NORTH);
        this.add(fen);

    }

}