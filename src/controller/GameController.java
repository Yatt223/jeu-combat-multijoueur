package controller;

import model.Game;
import model.Player;
import model.Position;
import view.GameFrame;

/**
 * Auteur : Yattoura
 */
public class GameController {
    private Game game;
    private GameFrame gameFrame;
    private Player currentPlayer;

    public GameController(Game game, GameFrame gameFrame) {
        this.game = game;
        this.gameFrame = gameFrame;
        this.currentPlayer = game.getPlayers().get(0); // Commence avec le premier joueur
    }

    public void startGame() {
        log("La partie commence !");
        nextTurn();
    }

    public void nextTurn() {
        if (game.isGameOver()) {
            log("La partie est terminée.");
            return;
        }

        log("C'est au tour de " + currentPlayer.getName() + ".");
        gameFrame.refreshGameDisplay();
    }

    public void handleCellClick(Position position) {
        // Vérifiez si le joueur veut se déplacer ou attaquer
        if (currentPlayer.move(currentPlayer.getPosition().directionTo(position), game.getGrid())) {
            log(currentPlayer.getName() + " se déplace vers " + position + ".");
        } else {
            log("Déplacement impossible vers " + position + ".");
        }
        gameFrame.refreshGameDisplay();
    }

    private void log(String message) {
        gameFrame.updateLog(message);
    }
}