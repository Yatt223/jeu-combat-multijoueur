package model.strategy;

import model.*;
import model.weapons.Weapon;

import java.util.List;

/**
 * Auteur : Yattoura
 */

public class AdvancedStrategy implements GameStrategy {

    @Override
    public void playTurn(Player player, Game game) {
        // Si un ennemi est proche, attaquer en priorité
        if (attackNearbyEnemy(player, game)) {
            return;
        }

        // Si l'énergie est faible, chercher une case d'énergie
        if (player.getEnergy() < 50 && moveToEnergy(player, game)) {
            return;
        }

        // Sinon, se déplacer vers une position stratégique
        moveStrategically(player, game);
    }

    // Méthode pour attaquer un ennemi proche
    private boolean attackNearbyEnemy(Player player, Game game) {
        List<Position> nearbyPositions = player.getPosition().getAdjacentPositions(game.getGrid().getSize());
        for (Position pos : nearbyPositions) {
            Player enemy = game.getGrid().getCell(pos).getPlayer();
            if (enemy != null && enemy != player) {
                Weapon weapon = player.getWeapons().get(0); // Utilise la première arme disponible
                weapon.useWeapon(pos, game.getGrid());
                System.out.println(player.getName() + " attaque " + enemy.getName() + " à la position " + pos);
                return true;
            }
        }
        return false;
    }

    // Cherche une case d'énergie proche et se déplace vers elle
    private boolean moveToEnergy(Player player, Game game) {
        List<Position> nearbyPositions = player.getPosition().getAdjacentPositions(game.getGrid().getSize());
        for (Position pos : nearbyPositions) {
            if (game.getGrid().getCell(pos).containsEnergy()) {
                player.move(player.getPosition().directionTo(pos), game.getGrid());
                System.out.println(player.getName() + " se déplace vers une case avec énergie à " + pos);
                return true;
            }
        }
        return false;
    }

    // Se déplace vers une position stratégique, ici le centre de la grille
    private void moveStrategically(Player player, Game game) {
        int gridSize = game.getGrid().getSize();
        Position center = new Position(gridSize / 2, gridSize / 2);
        Direction directionToCenter = player.getPosition().directionTo(center);
        player.move(directionToCenter, game.getGrid());
        System.out.println(player.getName() + " se déplace vers le centre de la grille.");
    }
}