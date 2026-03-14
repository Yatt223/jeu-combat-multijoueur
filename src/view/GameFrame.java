package view;

import controller.GameController;
import model.Game;

import javax.swing.*;
import java.awt.*;

/**
 * Auteur : Yattoura
 */

public class GameFrame extends JFrame {
    private Game game;
    private GridPanel gridPanel;
    private JTextArea logArea;
    private GameController controller;

    public GameFrame(Game game) {
        this.game = game;

        setTitle("Jeu de Grille");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Initialiser la grille
        gridPanel = new GridPanel(game.getGrid());
        add(gridPanel, BorderLayout.CENTER);

        // Zone de log
        logArea = new JTextArea(10, 30);
        logArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(logArea);
        add(scrollPane, BorderLayout.EAST);

        // Boutons
        JPanel controlPanel = new JPanel(new FlowLayout());
        JButton endTurnButton = new JButton("Fin de Tour");
        controlPanel.add(endTurnButton);
        add(controlPanel, BorderLayout.SOUTH);

        // Action pour passer au joueur suivant
        endTurnButton.addActionListener(e -> controller.nextTurn());

        // Listener pour les clics sur la grille
        gridPanel.setCellClickListener(position -> controller.handleCellClick(position));
    }

    public void setController(GameController controller) {
        this.controller = controller;
    }

    public void updateLog(String message) {
        logArea.append(message + "\n");
    }

    public void refreshGameDisplay() {
        gridPanel.updateGrid(game.getGrid());
    }
}