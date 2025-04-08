package projet.jeux.vue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.*;

public class vuePion extends JPanel{
    @Override
    public void paintComponent(Graphics g){
        g.setColor(Color.RED);
        //g.drawOval(100, 100, 30, 30); // (x, y, width, height)
        g.fillOval(100, 100, 30, 30); //interieur plein
        //g.drawRect(100, 130, 30, 50); //contour
        //g.fillRect(100, 128, 30, 50);
}

public static void main(String[] args) {
    JFrame fen = new JFrame();
    fen.setSize(500,500);
    vuePion p = new vuePion();
    fen.add(p);
    fen.setVisible(true);
    
}
    
}
