package view;

import model.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Auteur : Yattoura
 */

public class PlayerFrame extends JFrame {
    private Player player;
    private JLabel energyLabel;
    private JTextArea weaponsArea;

    public PlayerFrame(Player player) {
        this.player = player;
        setTitle("Informations sur le joueur - " + player.getName());
        setSize(300, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Panel pour les informations du joueur
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new GridLayout(2, 1));
        energyLabel = new JLabel("Énergie : " + player.getEnergy());
        weaponsArea = new JTextArea("Armes :\n" + getWeaponsText());
        weaponsArea.setEditable(false);
        infoPanel.add(energyLabel);
        infoPanel.add(new JScrollPane(weaponsArea));

        // Panel pour les boutons d'action
        JPanel actionPanel = new JPanel();
        actionPanel.setLayout(new GridLayout(1, 2));
        JButton attackButton = new JButton("Attaquer");
        JButton moveButton = new JButton("Déplacer");
        actionPanel.add(attackButton);
        actionPanel.add(moveButton);

        // Ajouter les panels à la fenêtre
        add(infoPanel, BorderLayout.CENTER);
        add(actionPanel, BorderLayout.SOUTH);

        // Actions des boutons
        attackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                performAttack();
            }
        });

        moveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                performMove();
            }
        });
    }

    // Méthode pour mettre à jour les informations affichées
    public void updateInfo() {
        energyLabel.setText("Énergie : " + player.getEnergy());
        weaponsArea.setText("Armes :\n" + getWeaponsText());
    }

    // Méthode pour générer un texte de la liste des armes du joueur
    private String getWeaponsText() {
        StringBuilder weaponsText = new StringBuilder();
        player.getWeapons().forEach(weapon -> weaponsText.append(weapon.getClass().getSimpleName())
                .append(" (Munitions: ").append(weapon.getAmmo()).append(")\n"));
        return weaponsText.toString();
    }

    // Action pour le bouton "Attaquer"
    private void performAttack() {
        JOptionPane.showMessageDialog(this, player.getName() + " effectue une attaque!");
        // Appeler la logique d'attaque du joueur, mettre à jour l'interface si
        // nécessaire
        updateInfo();
    }

    // Action pour le bouton "Déplacer"
    private void performMove() {
        JOptionPane.showMessageDialog(this, player.getName() + " choisit de se déplacer.");
        // Appeler la logique de déplacement du joueur, mettre à jour l'interface si
        // nécessaire
        updateInfo();
    }
}