package model;

/**
 * Auteur : Yattoura
 */
public class Grid {
    private Cell[][] cells;
    private int size;

    public Grid(int size) {
        this.size = size;
        cells = new Cell[size][size];
        initializeGrid();
    }

    // Initialise la grille avec des cellules vides
    public void initializeGrid() {
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                cells[x][y] = new Cell(new Position(x, y));
            }
        }
    }

    // Vérifie si une position donnée est dans les limites de la grille
    public boolean isWithinBounds(Position position) {
        int x = position.getX();
        int y = position.getY();
        return x >= 0 && x < size && y >= 0 && y < size;
    }

    // Retourne une cellule à une position donnée
    public Cell getCell(Position position) {
        if (isWithinBounds(position)) {
            return cells[position.getX()][position.getY()];
        } else {
            throw new IndexOutOfBoundsException("Position en dehors des limites de la grille");
        }
    }

    // Retourne la taille de la grille
    public int getSize() {
        return size;
    }
}