package view;

import model.Game;
import model.Player;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * Auteur : Yattoura
 */

public class PlayerInfoPanel extends JPanel {
    private Game game;
    private JTextArea infoArea;

    public PlayerInfoPanel(Game game) {
        this.game = game;
        setLayout(new BorderLayout());

        infoArea = new JTextArea(10, 20);
        infoArea.setEditable(false);

        add(new JScrollPane(infoArea), BorderLayout.CENTER);
        updatePlayerInfo();
    }

    // Méthode pour mettre à jour les informations des joueurs
    public void updatePlayerInfo() {
        List<Player> players = game.getPlayers();
        StringBuilder info = new StringBuilder();

        for (Player player : players) {
            info.append("Nom: ").append(player.getName()).append("\n");
            info.append("Energie: ").append(player.getEnergy()).append("\n");
            info.append("Armes:\n");

            player.getWeapons().forEach(weapon -> info.append(" - ").append(weapon.getClass().getSimpleName())
                    .append(" (munitions: ").append(weapon.getAmmo()).append(")\n"));

            info.append("\n");
        }
        infoArea.setText(info.toString());
    }
}