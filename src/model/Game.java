package model;

/**
 * Auteur : Yattoura
 */

import java.util.ArrayList;
import java.util.List;

public class Game {
    private List<Player> players;
    private Grid grid;
    private int maxTurns;
    private int turnCount;

    public Game(List<Player> players, Grid grid) {
        this.players = players;
        this.grid = grid;
        this.maxTurns = 100; // Limite de tours par défaut
        this.turnCount = 0;
    }

    public Grid getGrid() {
        return grid;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public int getTurnCount() {
        return turnCount;
    }

    public int getMaxTurns() {
        return maxTurns;
    }

    // Vérifie s'il y a un gagnant (joueur restant avec de l'énergie)
    public Player checkWinner() {
        List<Player> activePlayers = new ArrayList<>();

        for (Player player : players) {
            if (player.getEnergy() > 0) {
                activePlayers.add(player);
            }
        }

        if (activePlayers.size() == 1) {
            return activePlayers.get(0);
        }
        return null;
    }

    // Vérifie si le jeu est terminé (un gagnant ou limite de tours atteinte)
    public boolean isGameOver() {
        if (checkWinner() != null) {
            System.out.println("Partie terminée ! Le joueur " + checkWinner().getName() + " a gagné !");
            return true;
        }

        if (turnCount >= maxTurns) {
            System.out.println("Limite de tours atteinte. Match nul !");
            return true;
        }

        return false;
    }

    // Méthode pour incrémenter le compteur de tours (appelée à chaque tour)
    public void nextTurn() {
        turnCount++;
    }
}