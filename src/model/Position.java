package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Auteur : Yattoura
 */
public class Position {
    private int x;
    private int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    // Renvoie les positions adjacentes (haut, bas, gauche, droite)
    public List<Position> getAdjacentPositions(int gridSize) {
        List<Position> adjacentPositions = new ArrayList<>();

        // Vérifie les 4 directions principales (haut, bas, gauche, droite)
        if (x > 0)
            adjacentPositions.add(new Position(x - 1, y)); // Gauche
        if (x < gridSize - 1)
            adjacentPositions.add(new Position(x + 1, y)); // Droite
        if (y > 0)
            adjacentPositions.add(new Position(x, y - 1)); // Haut
        if (y < gridSize - 1)
            adjacentPositions.add(new Position(x, y + 1)); // Bas

        return adjacentPositions;
    }

    // Méthode pour calculer la direction vers une position cible
    public Direction directionTo(Position target) {
        if (target.getX() < this.x) {
            return Direction.LEFT; // Cible est à gauche
        } else if (target.getX() > this.x) {
            return Direction.RIGHT; // Cible est à droite
        } else if (target.getY() < this.y) {
            return Direction.UP; // Cible est en haut
        } else if (target.getY() > this.y) {
            return Direction.DOWN; // Cible est en bas
        }
        return null; // Si la position cible est identique à la position actuelle
    }

    // Méthode pour déplacer la position actuelle dans une direction donnée
    public Position move(Direction direction) {
        switch (direction) {
            case UP:
                return new Position(x, y - 1); // Déplacement vers le haut (diminue Y)
            case DOWN:
                return new Position(x, y + 1); // Déplacement vers le bas (augmente Y)
            case LEFT:
                return new Position(x - 1, y); // Déplacement vers la gauche (diminue X)
            case RIGHT:
                return new Position(x + 1, y); // Déplacement vers la droite (augmente X)
            default:
                throw new IllegalArgumentException("Direction inconnue: " + direction);
        }
    }

}