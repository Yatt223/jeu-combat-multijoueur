package main;

import model.*;
import model.strategy.AdvancedStrategy;
import model.strategy.RandomStrategy;
import model.weapons.HorizontalShot;
import model.weapons.VerticalShot;
import view.GameFrame;

import javax.swing.*;
import java.util.Arrays;

/**
 * Auteur : Yattoura
 */
public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // Initialiser les joueurs
            Player player1 = new Player("Alice", 100, new Position(0, 0), new AdvancedStrategy());
            Player player2 = new Player("Bot", 100, new Position(4, 4), new RandomStrategy());

            // Ajouter des armes aux joueurs
            player1.addWeapon(new HorizontalShot(3, 25, 5, player1));
            player1.addWeapon(new VerticalShot(3, 25, 5, player1));

            player2.addWeapon(new HorizontalShot(2, 20, 4, player2));
            player2.addWeapon(new VerticalShot(2, 20, 4, player2));

            // Initialiser la grille et le modèle de jeu
            Grid grid = new Grid(5); // Grille 5x5
            Game game = new Game(Arrays.asList(player1, player2), grid);

            // Créer et afficher l'interface graphique
            GameFrame gameFrame = new GameFrame(game);
            gameFrame.setVisible(true);
        });
    }
}