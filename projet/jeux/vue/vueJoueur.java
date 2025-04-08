package projet.jeux.vue;

import javax.swing.*;

import projet.jeux.Jeux;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;




public class vueJoueur extends JFrame{
    public vueJoueur(){
        JPanel fen = new JPanel();
        fen.setLayout(new BorderLayout());
        fen.setSize(500,500);
        JMenuBar barre = new JMenuBar();
        JMenu Re = new JMenu("Retour  ▾");



        Re.setIcon(new ImageIcon("/home/thierry/Documents/Java/Projet/Jeux_Java/projet/jeux/vue/image_MenuBarre/flecheRouge.png"));
        Re.setHorizontalTextPosition(JLabel.RIGHT);
        barre.add(Re);

        //Re.setIcon("");
        JMenu Para = new JMenu("Paramètre  ▾");
        barre.add(Para);
        Para.setIcon(new ImageIcon("/home/thierry/Documents/Java/Projet/Jeux_Java/projet/jeux/vue/image_MenuBarre/para.png"));
        Para.setHorizontalTextPosition(JLabel.RIGHT);


        JMenu Ai = new JMenu("Aide");
        barre.add(Ai);
        Ai.setIcon(new ImageIcon("/home/thierry/Documents/Java/Projet/Jeux_Java/projet/jeux/vue/image_MenuBarre/i.png"));
        Ai.setHorizontalTextPosition(JLabel.RIGHT);


        JMenuItem ReStart = new JMenuItem("Recommencer jeu");
        Re.add(ReStart);

        ReStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Action déclenchée lors du clic
                Jeux j = new Jeux(2); // relance ub jeu
                j.startJeux();

            }
        });

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
        this.pack(); 
    }

    public static void main(String[] args) {
        vueJoueur j = new vueJoueur();
        j.setLocation(350, 200);
        j.setVisible(true);
        
    }
}
