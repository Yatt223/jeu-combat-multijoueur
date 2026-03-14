package model;

/**
 * Auteur : Yattoura
 */

public enum Direction {
    UP, DOWN, LEFT, RIGHT;

    // Retourne la position modifiée en fonction de la direction
    public Position move(Position position) {
        switch (this) {
            case UP:
                return new Position(position.getX(), position.getY() - 1);
            case DOWN:
                return new Position(position.getX(), position.getY() + 1);
            case LEFT:
                return new Position(position.getX() - 1, position.getY());
            case RIGHT:
                return new Position(position.getX() + 1, position.getY());
            default:
                throw new IllegalArgumentException("Direction inconnue: " + this);
        }
    }

}