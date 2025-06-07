package src.vue;

import src.modele.ModeleJoueur;
import src.modele.ModelePion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

/**
 * Cette classe de vue est utilisé pour générer les fenêtres de dialogue personnalisées à travers des constructeurs
 */
public class VueDialogue extends JDialog {
    private List<Integer> choix = new ArrayList<>();
    private int chiffreDe;
    private int[] choixPionEtJoueur = new int[2];

    private ArrayList<ModeleJoueur> ensembleJoueur = new ArrayList<>();

    public VueDialogue(Frame parent, int chiffreDe) {
        super(parent, true);
        this.chiffreDe = chiffreDe;
        setUndecorated(true);

        initChoixPionUI();
        pack();
        setShape(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 30, 30));
        setLocationRelativeTo(parent);
        getContentPane().setBackground(new Color(251, 251, 251));
        setVisible(true);
    }

    public VueDialogue(Frame parent, String title, String msg) {
        JOptionPane.showMessageDialog(parent, msg, title, JOptionPane.INFORMATION_MESSAGE);
    }

    public VueDialogue(Frame parent, ArrayList<ModeleJoueur> ensembleJoueur, ModeleJoueur joueurActuel, boolean classement) {
        super(parent, true);
        setUndecorated(true);
        this.ensembleJoueur = ensembleJoueur;

        if (classement) {
            initClassement();
        } else {
            initDeZero(joueurActuel);
        }

        pack();
        setShape(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 30, 30));
        setLocationRelativeTo(parent);
        getContentPane().setBackground(new Color(251, 251, 251));
        setVisible(true);
    }

    private void initClassement() {
        setLayout(new BorderLayout(10, 10));

        JLabel titre = new JLabel("Jeu terminé", SwingConstants.CENTER);
        titre.setFont(new Font("SansSerif", Font.BOLD, 16));
        add(titre, BorderLayout.NORTH);

        // Classement des joueurs par l'ordre décroissant
        ensembleJoueur.sort(Comparator.comparingInt(ModeleJoueur::getScore).reversed());

        JPanel rankingPanel = new JPanel();
        rankingPanel.setLayout(new BoxLayout(rankingPanel, BoxLayout.Y_AXIS));
        rankingPanel.setBackground(new Color(251, 251, 251));

        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new GridLayout(1, 5));
        headerPanel.setBackground(new Color(251, 251, 251));

        headerPanel.add(new JLabel("Classement"));
        headerPanel.add(new JLabel("Nom"));
        headerPanel.add(new JLabel("Score"));
        headerPanel.add(new JLabel("Pion"));
        headerPanel.add(new JLabel("Position"));

        rankingPanel.add(headerPanel);

        for (int i = 0; i < ensembleJoueur.size(); i++) {
            ModeleJoueur joueur = ensembleJoueur.get(i);
            int score = joueur.getScore();
            ModelePion pion = joueur.ensemblePion.getFirst();
            int pionPosition = pion.getPosition();
            int pionChiffre = pion.getChiffre();

            JPanel playerPanel = new JPanel();
            playerPanel.setLayout(new GridLayout(1, 5));
            playerPanel.setBackground(new Color(251, 251, 251));

            playerPanel.add(new JLabel(String.valueOf(i + 1)));
            playerPanel.add(new JLabel(joueur.getNom()));
            playerPanel.add(new JLabel(String.valueOf(score)));
            playerPanel.add(new JLabel("Pion " + pionChiffre));
            playerPanel.add(new JLabel(String.valueOf(pionPosition)));

            rankingPanel.add(playerPanel);
        }

        JScrollPane scrollPane = new JScrollPane(rankingPanel);
        add(scrollPane, BorderLayout.CENTER);

        JButton closeButton = new JButton("OK");
        closeButton.addActionListener(e -> dispose());
        add(closeButton, BorderLayout.SOUTH);
    }

    public void initDeZero(ModeleJoueur joueurAcutuel) {
        JRadioButton[] radioButtonsPion = new JRadioButton[6];
        JRadioButton[] radioButtonsJoueur = new JRadioButton[4];

        setLayout(new BorderLayout(10, 10));
        setUndecorated(true);


        JLabel titre = new JLabel("Veuillez sélectionner un joueur et un pion", SwingConstants.CENTER);
        titre.setFont(new Font("SansSerif", Font.BOLD, 16));
        add(titre, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel();
        centerPanel.setBackground(new Color(251, 251, 251));
        centerPanel.setLayout(new GridLayout(2, 3, 5, 5));

        JPanel panelJoueur = new JPanel();
        panelJoueur.setBackground(new Color(251, 251, 251));
        panelJoueur.setLayout(new BoxLayout(panelJoueur, BoxLayout.X_AXIS));
        ButtonGroup group = new ButtonGroup();
        ButtonGroup group2 = new ButtonGroup();

        for (int i = 0; i < 6; i++) {
            JPanel choixPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            choixPanel.setBackground(new Color(251, 251, 251));
            JLabel label = new JLabel("Pion " + (i + 1));
            radioButtonsPion[i] = new JRadioButton();
            group.add(radioButtonsPion[i]);
            choixPanel.add(label);
            choixPanel.add(radioButtonsPion[i]);
            centerPanel.add(choixPanel);
        }

        for (int i = 0; i < ensembleJoueur.size(); i++) {
            if (!ensembleJoueur.get(i).getNom().equals(joueurAcutuel.getNom())) {
                JRadioButton radio = new JRadioButton(ensembleJoueur.get(i).getNom());
                group2.add(radio);
                radioButtonsJoueur[i] = radio;
                panelJoueur.add(radio);
            }
        }

        JButton btnConfirmation = new JButton("OK");
        btnConfirmation.addActionListener(x -> {
            for (int i = 0; i < radioButtonsPion.length; i++) {
                if (radioButtonsPion[i].isSelected()) {
                    choixPionEtJoueur[0] = i;
                    break;
                }
            }

            for (int i = 0; i < radioButtonsJoueur.length; i++) {
                if (radioButtonsJoueur[i] != null && radioButtonsJoueur[i].isSelected()) {
                    choixPionEtJoueur[1] = i;
                    break;
                }
            }

            dispose();
        });

        JPanel buttonPanel = new JPanel(new BorderLayout());
        buttonPanel.setBackground(new Color(251, 251, 251));
        buttonPanel.add(panelJoueur, BorderLayout.CENTER);
        buttonPanel.add(btnConfirmation, BorderLayout.SOUTH);

        add(centerPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

    }

    public void initChoixPionUI() {
        JToggleButton[] toggleButtons = new JToggleButton[6];
        setLayout(new BorderLayout(10, 10));
        setUndecorated(true);


        JLabel titre = new JLabel("Choix de pion", SwingConstants.CENTER);
        titre.setFont(new Font("SansSerif", Font.BOLD, 16));
        add(titre, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(2, 3, 5, 5));
        centerPanel.setBackground(new Color(251, 251, 251));

        for (int i = 1; i <= 6; i++) {
            JToggleButton btn = new JToggleButton("Pion " + i);
            btn.setFont(new Font("SansSerif", Font.BOLD, 20));
            btn.setContentAreaFilled(true);
            btn.setOpaque(true);
            btn.setFocusPainted(false);
            btn.addChangeListener(e -> {
                if (btn.isSelected()) {
                    btn.setForeground(Color.GREEN);
                } else {
                    btn.setForeground(Color.BLACK);
                }
                btn.repaint();
            });
            toggleButtons[i - 1] = btn;
            centerPanel.add(btn);
        }

        JButton btnConfirmation = new JButton("OK");
        btnConfirmation.setMargin(new Insets(10, 20, 10, 20));
        btnConfirmation.setFont(new Font("SansSerif", Font.BOLD, 16));
        btnConfirmation.addActionListener(x -> {
            choix.clear();
            int sum = 0;
            for (int i = 0; i < toggleButtons.length; i++) {
                if (toggleButtons[i].isSelected()) {
                    choix.add(i);
                    sum = sum + i + 1;
                }
            }
            System.out.println(sum);
            if (sum != chiffreDe) {
                JOptionPane.showMessageDialog(this, "Veuillez choisir la bonne combinaison des pions", "Erreur combinaison de pion", JOptionPane.INFORMATION_MESSAGE);
            } else {
                dispose();
            }
        });
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(251, 251, 251));
        buttonPanel.add(btnConfirmation);

        ImageIcon deIcon = switch (chiffreDe) {
            case 1 -> new ImageIcon(Objects.requireNonNull(getClass().getResource("/src/ressources/animations/1.gif")));
            case 2 -> new ImageIcon(Objects.requireNonNull(getClass().getResource("/src/ressources/animations/2.gif")));
            case 3 -> new ImageIcon(Objects.requireNonNull(getClass().getResource("/src/ressources/animations/3.gif")));
            case 4 -> new ImageIcon(Objects.requireNonNull(getClass().getResource("/src/ressources/animations/4.gif")));
            case 5 -> new ImageIcon(Objects.requireNonNull(getClass().getResource("/src/ressources/animations/5.gif")));
            case 6 -> new ImageIcon(Objects.requireNonNull(getClass().getResource("/src/ressources/animations/6.gif")));
            default -> null;
        };

        JLabel deLabel = new JLabel(deIcon);

        Timer timer = new Timer(1500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deLabel.setIcon(switch (chiffreDe) {
                    case 1 -> new ImageIcon(Objects.requireNonNull(getClass().getResource("/src/ressources/images/1.jpg")));
                    case 2 -> new ImageIcon(Objects.requireNonNull(getClass().getResource("/src/ressources/images/2.jpg")));
                    case 3 -> new ImageIcon(Objects.requireNonNull(getClass().getResource("/src/ressources/images/3.jpg")));
                    case 4 -> new ImageIcon(Objects.requireNonNull(getClass().getResource("/src/ressources/images/4.jpg")));
                    case 5 -> new ImageIcon(Objects.requireNonNull(getClass().getResource("/src/ressources/images/5.jpg")));
                    case 6 -> new ImageIcon(Objects.requireNonNull(getClass().getResource("/src/ressources/images/6.jpg")));
                    default -> null;
                });
            }
        });
        timer.setRepeats(false);
        timer.start();

        add(deLabel, BorderLayout.WEST);
        add(centerPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    public List<Integer> getChoix() {
        return choix;
    }

    public int[] getChoixJoueur() {
        return choixPionEtJoueur;
    }

}
