package projet.jeux.vue;

import projet.jeux.modele.ModeleCase;

import javax.swing.*;
import java.awt.*;

/**
 * La classe correspondante à la case
 */
public class VueCase extends JPanel {

    private Color innerColor = Color.WHITE;
    private Color outerColor;
    private int innerMargin;
    private int outerMargin;
    private String text;

    public VueCase(ModeleCase modeleCase) {
        this.outerColor = modeleCase.getCouleur();
        if (modeleCase.getCoeff() == 0) {
            this.text = "";
        } else{
            this.text = String.valueOf(modeleCase.getCoeff());
        }

        setOpaque(false);
    }

    public void setPionPosition(int numPion,Color couleur) {
        this.innerColor = couleur;
        text = String.valueOf(numPion);
        repaint();
    }

    public void clearPionPosition() {
        this.innerColor = Color.YELLOW;
        text = "";
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {

        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int size = Math.min(getWidth(), getHeight());

        // Cercle extérieur
        outerMargin = 0;
        g2.setColor(outerColor);
        g2.fillOval(outerMargin, outerMargin, size - outerMargin * 2, size - outerMargin * 2);

        // Cercle intérieur
        innerMargin = 6;
        g2.setColor(innerColor);
        g2.fillOval(innerMargin, innerMargin, size - innerMargin * 2, size - innerMargin * 2);

        // Texte au centre
        g2.setColor(Color.BLACK);
        g2.setFont(new Font("SansSerif", Font.BOLD, 30));
        FontMetrics fm = g2.getFontMetrics();
        int textWidth = fm.stringWidth(text);
        int textHeight = fm.getAscent();
        int x = (getWidth() - textWidth) / 2;
        int y = (getHeight() + textHeight) / 2 - 4;
        g2.drawString(text, x, y);


    }

    public Color getInnerColor() {
        return innerColor;
    }
}
