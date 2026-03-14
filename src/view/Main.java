package view;

import controller.GameController;
import model.*;
import model.strategy.AdvancedStrategy;
import model.strategy.RandomStrategy;

import javax.swing.*;
import java.util.Arrays;

/**
 * Auteur : Yattoura
 */

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // Initialisation du modèle
            Player player1 = new Player("Alice", 100, new Position(0, 0), new AdvancedStrategy());
            Player player2 = new Player("Bot", 100, new Position(4, 4), new RandomStrategy());

            Grid grid = new Grid(5); // Grille 5x5
            Game game = new Game(Arrays.asList(player1, player2), grid);

            // Initialisation de la vue et du contrôleur
            GameFrame gameFrame = new GameFrame(game);
            GameController gameController = new GameController(game, gameFrame);
            gameFrame.setController(gameController);

            // Afficher l'interface
            gameFrame.setVisible(true);
        });
    }
}