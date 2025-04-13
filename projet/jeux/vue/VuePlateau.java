package projet.jeux.vue;

import projet.jeux.modele.ModelePlateau;
import javax.swing.*;
import java.awt.*;

/**
 * La classe qui s'occupe de dessiner le plateau
 */
public class VuePlateau extends JPanel{
    public VuePlateau(ModelePlateau modelePlateau) {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        for (int i = 0; i < modelePlateau.ensembleCase.size(); i++) {
            VueCase vueCase = new VueCase(modelePlateau.ensembleCase.get(i));
            int x = modelePlateau.posCase.get(i)[0];
            int y = modelePlateau.posCase.get(i)[1];

            gbc.gridx = x;
            gbc.gridy = y;
            gbc.insets = new Insets(20, 60, 20, 60);
            vueCase.setPreferredSize(new Dimension(80, 80));
            this.add(vueCase, gbc);
        }
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;
        GradientPaint gradient = new GradientPaint(0, 0, new Color(200, 220, 255), getWidth(), getHeight(), Color.WHITE);
        g2d.setPaint(gradient);
        g2d.fillRect(0, 0, getWidth(), getHeight());

        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(10));

        java.util.List<Point> centers = new java.util.ArrayList<>();
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
