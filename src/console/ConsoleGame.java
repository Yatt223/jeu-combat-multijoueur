package console;

import model.*;
import model.strategy.*;
import model.weapons.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Auteur : Yattoura
 */

public class ConsoleGame {
    private Game game;
    private Scanner scanner;

    public ConsoleGame() {
        this.scanner = new Scanner(System.in);
        initializeGame();
    }

    // Initialisation du jeu et des joueurs
    private void initializeGame() {
        // Création de la grille de jeu
        Grid grid = new Grid(5); // Grille de taille 5x5
        List<Player> players = new ArrayList<>();

        // Création de deux joueurs (humain et IA)
        Player player1 = new Player("Humain", 100, new Position(0, 0), new AdvancedStrategy());
        Player player2 = new Player("IA", 100, new Position(4, 4), new RandomStrategy());

        // Ajouter des armes aux joueurs
        player1.addWeapon(new HorizontalShot(3, 25, 5, player1));
        player1.addWeapon(new VerticalShot(3, 25, 5, player1));

        player2.addWeapon(new HorizontalShot(2, 20, 4, player2));
        player2.addWeapon(new Bomb(50, 20, player2));
        // Bomb(1, 50, 2, player2)

        // Ajouter les joueurs à la liste
        players.add(player1);
        players.add(player2);

        // Initialiser le jeu avec les joueurs et la grille
        this.game = new Game(players, grid);
    }

    // Boucle principale du jeu
    public void start() {
        System.out.println("Bienvenue dans le jeu de console !");
        while (!game.isGameOver()) {
            for (Player player : game.getPlayers()) {
                if (player.getEnergy() <= 0)
                    continue; // Passer le joueur s'il n'a plus d'énergie
                System.out.println("\nTour de " + player.getName() + " (" + player.getEnergy() + " énergie)");
                displayGrid();
                playerTurn(player);
            }
        }
        System.out.println("La partie est terminée !");
    }

    // Tour du joueur
    private void playerTurn(Player player) {
        System.out.println("Actions possibles :");
        System.out.println("1 - Déplacer");
        System.out.println("2 - Attaquer");
        System.out.print("Choisissez une action : ");

        int choice = scanner.nextInt();
        scanner.nextLine(); // Consomme la nouvelle ligne

        switch (choice) {
            case 1 -> movePlayer(player);
            case 2 -> attackWithPlayer(player);
            default -> System.out.println("Action non valide.");
        }
    }

    // Déplacer le joueur
    private void movePlayer(Player player) {
        System.out.println("Directions : UP, DOWN, LEFT, RIGHT");
        System.out.print("Entrez une direction : ");
        String directionInput = scanner.nextLine().toUpperCase();

        try {
            Direction direction = Direction.valueOf(directionInput);
            boolean success = player.move(direction, game.getGrid());
            if (success) {
                System.out.println(player.getName() + " se déplace vers " + direction);
            } else {
                System.out.println("Déplacement impossible.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Direction invalide.");
        }
    }

    // Attaquer avec le joueur
    private void attackWithPlayer(Player player) {
        System.out.println("Choisissez une arme :");
        List<Weapon> weapons = player.getWeapons();
        for (int i = 0; i < weapons.size(); i++) {
            Weapon weapon = weapons.get(i);
            System.out.println(
                    (i + 1) + " - " + weapon.getClass().getSimpleName() + " (Munitions: " + weapon.getAmmo() + ")");
        }

        int weaponChoice = scanner.nextInt() - 1;
        scanner.nextLine(); // Consomme la nouvelle ligne

        if (weaponChoice >= 0 && weaponChoice < weapons.size()) {
            Weapon weapon = weapons.get(weaponChoice);
            System.out.print("Entrez les coordonnées de la cible (x y) : ");
            int x = scanner.nextInt();
            int y = scanner.nextInt();
            scanner.nextLine(); // Consomme la nouvelle ligne

            Position target = new Position(x, y);
            if (game.getGrid().isWithinBounds(target)) {
                weapon.useWeapon(target, game.getGrid());
                System.out.println(player.getName() + " attaque la position (" + x + ", " + y + ") avec "
                        + weapon.getClass().getSimpleName());
            } else {
                System.out.println("Position cible hors des limites.");
            }
        } else {
            System.out.println("Choix d'arme invalide.");
        }
    }

    // Affiche la grille de jeu avec la position des joueurs
    private void displayGrid() {
        System.out.println("Grille de jeu :");
        for (int y = 0; y < game.getGrid().getSize(); y++) {
            for (int x = 0; x < game.getGrid().getSize(); x++) {
                Player playerAtPos = game.getGrid().getCell(new Position(x, y)).getPlayer();
                if (playerAtPos != null) {
                    System.out.print(playerAtPos.getName().charAt(0) + " ");
                } else {
                    System.out.print(". ");
                }
            }
            System.out.println();
        }
    }
}