package src.vue;

import src.modele.ModeleJoueur;
import src.modele.ModelePlateau;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Path2D;
import java.util.ArrayList;
import java.util.Objects;

/**
 * La classe de vue pour dessiner le plateau
 */
public class VuePlateau extends JPanel {
    private ArrayList<VueCase> ensembleVueCase;
    private JButton jb;
    private BackgroundLabel backgroundLabel;
    private ArrayList<ModeleJoueur> ensembleJoueur;
    private ArrayList<JLabel> ensembleJLabel;
    private ModeleJoueur joueurActuel;

    public VuePlateau(ModelePlateau modelePlateau, ArrayList<ModeleJoueur> ensembleJoueur, ModeleJoueur joueurActuel) {
        setLayout(new BorderLayout());

        this.joueurActuel = joueurActuel;
        this.ensembleJoueur = ensembleJoueur;
        this.ensembleJLabel = new ArrayList<>(4);

        backgroundLabel = new BackgroundLabel(new ImageIcon(Objects.requireNonNull(getClass().getResource("/src/ressources/images/mer.jpg"))));
        ensembleVueCase = new ArrayList<>(modelePlateau.ensembleCase.size());
        backgroundLabel.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();

        // Ajouter VueCase au tableau
        for (int i = 0; i < modelePlateau.ensembleCase.size() - 1; i++) {
            VueCase vueCase = new VueCase(modelePlateau.ensembleCase.get(i));
            ensembleVueCase.add(vueCase);
            int x = modelePlateau.posCase.get(i)[0];
            int y = modelePlateau.posCase.get(i)[1];

            gbc.gridx = x;
            gbc.gridy = y;
            //gbc.insets = new Insets(20, 60, 20, 60);
            //vueCase.setPreferredSize(new Dimension(80, 80));
            gbc.insets = new Insets(10, 30, 10, 30);
            vueCase.setPreferredSize(new Dimension(70, 70));
            backgroundLabel.add(vueCase, gbc);
        }

        // Créer un bouton pour le dé
        jb = new JButton();
        ImageIcon btnIcon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/src/ressources/images/de.png")));
        Image image = btnIcon.getImage();
        Image scaledImage = image.getScaledInstance(85, 85, Image.SCALE_DEFAULT);
        btnIcon = new ImageIcon(scaledImage);
        jb.setIcon(btnIcon);
        gbc.gridx = modelePlateau.posCase.get(modelePlateau.ensembleCase.size() - 1)[0];
        gbc.gridy = modelePlateau.posCase.get(modelePlateau.ensembleCase.size() - 1)[1];
        gbc.insets = new Insets(20, 60, 20, 60);
        jb.setFont(new Font("SansSerif", Font.BOLD, 80));
        jb.setPreferredSize(new Dimension(80, 80));
        jb.setBorder(null);
        jb.setBorderPainted(false);
        jb.setFocusPainted(false);
        jb.setContentAreaFilled(false);
        jb.setOpaque(false);
        backgroundLabel.add(jb, gbc);

        // Créer des panneaux de bulle de discours pour chaque joueur et les ajouter à différentes positions verticales
        for (int i = 0; i < ensembleJoueur.size(); i++) {
            SpeechBubblePanel bubblePanel = new SpeechBubblePanel(ensembleJoueur.get(i).getCouleur());
            bubblePanel.setLayout(new BorderLayout());

            // Créer le JLabel pour le nom du joueur
            JLabel lbl = new JLabel();
            ensembleJLabel.add(lbl);
            lbl.setForeground(Color.BLACK);
            lbl.setFont(new Font("Arial", Font.BOLD, 20));
            lbl.setHorizontalAlignment(SwingConstants.CENTER);

            // Ajuster la largeur en fonction du fait que le joueur est le joueur actuel
            if (ensembleJoueur.get(i).equals(joueurActuel)) {
                lbl.setText(ensembleJoueur.get(i).getNom() + "   " + ensembleJoueur.get(i).getScore());
                lbl.setPreferredSize(new Dimension(100, 40));
            } else {
                lbl.setText("");
                lbl.setPreferredSize(new Dimension(50, 40));
            }

            bubblePanel.add(lbl, BorderLayout.CENTER);

            // Créer un nouveau GridBagConstraints pour chaque bulle et les positionner verticalement
            GridBagConstraints bubbleGbc = new GridBagConstraints();
            bubbleGbc.gridx = 0;  // Fixer gridx pour aligner les bulles dans une seule colonne
            bubbleGbc.gridy = i;  // Varier gridy pour positionner les bulles verticalement
            bubbleGbc.anchor = GridBagConstraints.NORTHWEST;
            bubbleGbc.insets = new Insets(5, 0, 5, 0);
            bubbleGbc.weightx = 1.0;
            bubbleGbc.weighty = 0.0;
            backgroundLabel.add(bubblePanel, bubbleGbc);
        }
        backgroundLabel.setMinimumSize(new Dimension(800, 600)); // Adjust as needed
        this.setMinimumSize(new Dimension(800, 600));

        add(backgroundLabel, BorderLayout.CENTER);
    }

    public ArrayList<VueCase> getEnsembleCase() {
        return ensembleVueCase;
    }

    public void setBtnDe(Runnable action) {
        jb.addActionListener(e -> action.run());
    }

    public void updateJoueurActuel(ModeleJoueur nouveauJoueurActuel) {
        this.joueurActuel = nouveauJoueurActuel;

        for (int i = 0; i < ensembleJoueur.size(); i++) {
            JLabel lbl = ensembleJLabel.get(i);

            if (ensembleJoueur.get(i).equals(joueurActuel)) {
                lbl.setText(joueurActuel.getNom() + "   " + joueurActuel.getScore());
                lbl.setPreferredSize(new Dimension(100, 40));
            } else {
                lbl.setText("");
                lbl.setPreferredSize(new Dimension(70, 40));
            }

            lbl.revalidate();
            lbl.repaint();
        }

        revalidate();
        repaint();
    }

    private class BackgroundLabel extends JLabel {
        public BackgroundLabel(ImageIcon imageIcon) {
            super(imageIcon);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            g2d.setColor(Color.BLACK);
            g2d.setStroke(new BasicStroke(10));

            java.util.List<Point> centers = new ArrayList<>();
            for (Component comp : getComponents()) {
                if (comp instanceof VueCase) {
                    int x = comp.getX() + comp.getWidth() / 2;
                    int y = comp.getY() + comp.getHeight() / 2;
                    centers.add(new Point(x, y));
                }
            }

            for (int i = 0; i < centers.size() - 1; i++) {
                Point p1 = centers.get(i);
                Point p2 = centers.get(i + 1);
                g2d.drawLine(p1.x, p1.y, p2.x, p2.y);
            }
        }
    }

    // Panneau de bulle de discours avec un coin supérieur droit arrondi et un côté gauche plat
    private class SpeechBubblePanel extends JPanel {
        private static final int ARC_SIZE = 15;
        private Color backgroundColor;

        public SpeechBubblePanel(Color backgroundColor) {
            this.backgroundColor = backgroundColor;
            setOpaque(false);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            int width = getWidth();
            int height = getHeight();

            // Créer une forme de bulle personnalisée avec un coin supérieur droit arrondi et un côté gauche plat
            Path2D path = new Path2D.Float();
            path.moveTo(0, 0); // Coin supérieur gauche (coin plat)
            path.lineTo(width - ARC_SIZE, 0); // Bord supérieur droit (commencer la courbe pour le coin droit)
            path.quadTo(width, 0, width, ARC_SIZE); // Coin supérieur droit (coin arrondi)
            path.lineTo(width, height - ARC_SIZE); // Côté droit
            path.quadTo(width, height, width - ARC_SIZE, height); // Coin inférieur droit
            path.lineTo(0, height); // Coin inférieur gauche (coin plat)
            path.closePath();

            // Remplir le fond de la bulle
            g2d.setColor(backgroundColor);
            g2d.fill(path);
        }
    }
}
