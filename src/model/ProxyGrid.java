package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Auteur : Yattoura
 */

public class ProxyGrid {
    private Grid realGrid; // La grille réelle du jeu
    private Player player; // Le joueur pour lequel cette ProxyGrid est créée

    // Constructeur
    public ProxyGrid(Grid realGrid, Player player) {
        this.realGrid = realGrid;
        this.player = player;
    }

    // Renvoie une liste des cellules visibles pour le joueur
    public List<Cell> getVisibleCells() {
        List<Cell> visibleCells = new ArrayList<>();

        for (int x = 0; x < realGrid.getSize(); x++) {
            for (int y = 0; y < realGrid.getSize(); y++) {
                Cell cell = realGrid.getCell(new Position(x, y));

                if (isCellVisible(cell)) {
                    visibleCells.add(cell);
                } else {
                    visibleCells.add(createHiddenCell(cell.getPosition()));
                }
            }
        }
        return visibleCells;
    }

    // Vérifie si une cellule est visible pour le joueur
    private boolean isCellVisible(Cell cell) {
        // La cellule est visible si elle contient le joueur, un joueur allié, ou un
        // objet visible par ce joueur.
        if (cell.getPlayer() == player) {
            return true;
        }

        // Les bombes ou mines sont visibles uniquement si elles sont placées par ce
        // joueur
        if (cell.getMine() != null && cell.getMine().getOwner() == player) {
            return true;
        }

        if (cell.getBomb() != null && cell.getBomb().getOwner() == player) {
            return true;
        }

        // Autres cas d'accessibilité ou de visibilité spécifique peuvent être ajoutés
        // ici
        return false;
    }

    // Crée une cellule cachée pour simuler une cellule non visible
    private Cell createHiddenCell(Position position) {
        Cell hiddenCell = new Cell(position);
        hiddenCell.setWall(true); // Par défaut, on peut représenter les cellules invisibles comme des murs ou
                                  // vides
        return hiddenCell;
    }
}