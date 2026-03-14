package model.strategy;

import model.*;
import model.weapons.Weapon;

import java.util.List;
import java.util.Random;

/**
 * Auteur : Yattoura
 */

public class RandomStrategy implements GameStrategy {
    private Random random = new Random();

    @Override
    public void playTurn(Player player, Game game) {
        int action = random.nextInt(4); // Choisit une action aléatoire parmi 4 options

        switch (action) {
            case 0 -> moveRandomly(player, game);
            case 1 -> attackRandomly(player, game);
            case 2 -> player.deployShield();
            case 3 -> System.out.println(player.getName() + " choisit de ne rien faire pour économiser de l'énergie.");
        }
    }

    // Méthode pour déplacer le joueur dans une direction aléatoire
    private void moveRandomly(Player player, Game game) {
        Direction[] directions = Direction.values();
        Direction randomDirection = directions[random.nextInt(directions.length)];
        player.move(randomDirection, game.getGrid());
        System.out.println(player.getName() + " se déplace vers " + randomDirection);
    }

    // Méthode pour attaquer une position aléatoire
    private void attackRandomly(Player player, Game game) {
        List<Weapon> weapons = player.getWeapons();
        if (!weapons.isEmpty()) {
            Weapon weapon = weapons.get(random.nextInt(weapons.size()));
            Position targetPosition = getRandomPosition(game.getGrid().getSize());
            weapon.useWeapon(targetPosition, game.getGrid());
            System.out.println(player.getName() + " attaque la position " + targetPosition + " avec "
                    + weapon.getClass().getSimpleName());
        }
    }

    // Génère une position aléatoire dans la grille
    private Position getRandomPosition(int gridSize) {
        int x = random.nextInt(gridSize);
        int y = random.nextInt(gridSize);
        return new Position(x, y);
    }
}