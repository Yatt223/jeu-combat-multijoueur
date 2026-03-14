package model;

/**
 * Auteur : Yattoura
 */

import model.strategy.GameStrategy;
import model.weapons.Weapon;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private String name; // Nom du joueur
    private int energy; // Niveau d'énergie du joueur
    private Position position; // Position actuelle du joueur sur la grille
    private List<Weapon> weapons; // Liste des armes possédées par le joueur
    private GameStrategy strategy; // Stratégie utilisée par le joueur

    public Player(String name, int energy, Position startPosition, GameStrategy strategy) {
        this.name = name;
        this.energy = energy;
        this.position = startPosition;
        this.strategy = strategy;
        this.weapons = new ArrayList<>(); // Initialise la liste d'armes vide
    }

    // Getter pour le nom
    public String getName() {
        return name;
    }

    // Getter pour l'énergie
    public int getEnergy() {
        return energy;
    }

    // Réduit l'énergie du joueur
    public void reduceEnergy(int amount) {
        energy -= amount;
        if (energy < 0)
            energy = 0;
        System.out.println(name + " perd " + amount + " points d'énergie. Énergie restante: " + energy);
    }

    // Getter pour la position
    public Position getPosition() {
        return position;
    }

    // Définit une nouvelle position pour le joueur
    public void setPosition(Position newPosition) {
        this.position = newPosition;
    }

    // Getter pour la stratégie
    public GameStrategy getStrategy() {
        return strategy;
    }

    // Définit une nouvelle stratégie pour le joueur
    public void setStrategy(GameStrategy newStrategy) {
        this.strategy = newStrategy;
    }

    // Déplace le joueur dans une direction donnée
    public boolean move(Direction direction, Grid grid) {
        Position newPosition = position.move(direction);

        // Vérifie que la nouvelle position est dans les limites et libre
        if (grid.isWithinBounds(newPosition) && grid.getCell(newPosition).getPlayer() == null) {
            grid.getCell(position).setPlayer(null); // Libère l'ancienne position
            position = newPosition;
            grid.getCell(newPosition).setPlayer(this); // Place le joueur dans la nouvelle position
            System.out.println(name + " se déplace vers " + direction);
            return true;
        } else {
            System.out.println("Déplacement impossible pour " + name + " dans la direction " + direction);
            return false;
        }
    }

    // Utilise une arme sur une position cible
    public boolean useWeapon(Weapon weapon, Position targetPosition, Grid grid) {
        if (weapons.contains(weapon) && weapon.hasAmmo()) {
            if (grid.isWithinBounds(targetPosition)) {
                weapon.useWeapon(targetPosition, grid);
                System.out.println(
                        name + " utilise " + weapon.getClass().getSimpleName() + " sur la position " + targetPosition);
                return true;
            } else {
                System.out.println("Cible hors des limites pour " + name);
                return false;
            }
        } else {
            System.out.println(name + " n'a pas suffisamment de munitions ou ne possède pas cette arme.");
            return false;
        }
    }

    // Ajoute une arme au joueur
    public void addWeapon(Weapon weapon) {
        weapons.add(weapon);
    }

    // Getter pour les armes du joueur
    public List<Weapon> getWeapons() {
        return weapons;
    }

    // Active le bouclier pour protéger le joueur
    public void deployShield() {
        System.out.println(name + " déploie un bouclier pour se protéger au prochain tour.");
    }

    // Exécute le tour du joueur en utilisant sa stratégie
    public void playTurn(Game game) {
        strategy.playTurn(this, game);
    }
}