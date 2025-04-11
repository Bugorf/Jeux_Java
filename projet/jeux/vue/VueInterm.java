package projet.jeux.vue;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class VueInterm extends JPanel {

    private final ArrayList<JTextField> champsJoueurs = new ArrayList<>();
    private final JButton boutonConfirmer;

    public VueInterm() {
        setLayout(new BorderLayout(20, 20));
        setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));

        // ===== 顶部 Label =====
        JLabel labelTitre = new JLabel("Nombre de joueurs : 3");
        labelTitre.setFont(new Font("SansSerif", Font.BOLD, 24));
        labelTitre.setHorizontalAlignment(SwingConstants.CENTER);
        add(labelTitre, BorderLayout.NORTH);

        // ===== 中间 输入框区域 =====
        JPanel panelCentre = new JPanel();
        panelCentre.setLayout(new GridLayout(3, 2, 10, 15));

        for (int i = 1; i <= 3; i++) {
            JLabel label = new JLabel("Joueur " + i + " :");
            label.setFont(new Font("SansSerif", Font.PLAIN, 16));
            JTextField champ = new JTextField();
            champ.setFont(new Font("SansSerif", Font.PLAIN, 16));

            champsJoueurs.add(champ);

            panelCentre.add(label);
            panelCentre.add(champ);
        }

        add(panelCentre, BorderLayout.CENTER);

        // ===== 底部按钮 =====
        boutonConfirmer = new JButton("Confirmer");
        boutonConfirmer.setFont(new Font("SansSerif", Font.BOLD, 18));
        boutonConfirmer.setBackground(new Color(0x4CAF50));
        boutonConfirmer.setForeground(Color.WHITE);
        boutonConfirmer.setFocusPainted(false);
        boutonConfirmer.setCursor(new Cursor(Cursor.HAND_CURSOR));
        boutonConfirmer.setPreferredSize(new Dimension(150, 40));

        JPanel panelSud = new JPanel();
        panelSud.add(boutonConfirmer);
        add(panelSud, BorderLayout.SOUTH);
    }

    // === 对外暴露方法：返回输入的玩家名字 ===
    public ArrayList<String> getNomsJoueurs() {
        ArrayList<String> noms = new ArrayList<>();
        for (JTextField champ : champsJoueurs) {
            noms.add(champ.getText().trim());
        }
        return noms;
    }

    // === 对外暴露方法：让控制器设置按钮行为 ===
    public void setOnConfirmer(Runnable action) {
        boutonConfirmer.addActionListener(e -> action.run());
    }
}
