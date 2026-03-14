package controller;

import model.Direction;
import model.Game;
import model.Player;
import model.Position;
import model.weapons.Weapon;

import java.util.List;
import java.util.Scanner;

/**
 * Auteur : Yattoura
 */
public class PlayerController {
    private Player player;
    private Game game;
    private Scanner scanner;

    public PlayerController(Player player, Game game) {
        this.player = player;
        this.game = game;
        this.scanner = new Scanner(System.in);
    }

    // Méthode pour que le joueur choisisse une action
    public void takeAction() {
        System.out.println("Actions disponibles pour " + player.getName() + ":");
        System.out.println("1 - Déplacer");
        System.out.println("2 - Attaquer");
        System.out.print("Choisissez une action : ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consomme la ligne

        switch (choice) {
            case 1 -> movePlayer();
            case 2 -> attackWithPlayer();
            default -> System.out.println("Action non valide.");
        }
    }

    // Déplace le joueur dans une direction choisie
    private void movePlayer() {
        System.out.println("Directions : UP, DOWN, LEFT, RIGHT");
        System.out.print("Entrez une direction : ");
        String directionInput = scanner.nextLine().toUpperCase();

        try {
            Direction direction = Direction.valueOf(directionInput);
            boolean success = player.move(direction, game.getGrid());
            if (success) {
                System.out.println(player.getName() + " s'est déplacé vers " + direction);
            } else {
                System.out.println("Déplacement impossible.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Direction invalide.");
        }
    }

    // Permet au joueur de choisir une arme pour attaquer
    private void attackWithPlayer() {
        List<Weapon> weapons = player.getWeapons();
        System.out.println("Choisissez une arme :");
        for (int i = 0; i < weapons.size(); i++) {
            Weapon weapon = weapons.get(i);
            System.out.println((i + 1) + " - " + weapon.getClass().getSimpleName() + " (Munitions: " + weapon.getAmmo() + ")");
        }

        int weaponChoice = scanner.nextInt() - 1;
        scanner.nextLine(); // Consomme la ligne

        if (weaponChoice >= 0 && weaponChoice < weapons.size()) {
            Weapon weapon = weapons.get(weaponChoice);
            System.out.print("Entrez les coordonnées de la cible (x y) : ");
            int x = scanner.nextInt();
            int y = scanner.nextInt();
            scanner.nextLine(); // Consomme la ligne

            Position target = new Position(x, y);
            if (game.getGrid().isWithinBounds(target)) {
                weapon.useWeapon(target, game.getGrid());
                System.out.println(player.getName() + " attaque la position (" + x + ", " + y + ") avec " + weapon.getClass().getSimpleName());
            } else {
                System.out.println("Position cible hors des limites.");
            }
        } else {
            System.out.println("Choix d'arme invalide.");
        }
    }
}