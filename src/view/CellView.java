package view;

import model.Cell;

import javax.swing.*;
import java.awt.*;

/**
 * Auteur : Yattoura
 */

public class CellView extends JPanel {
    private Cell cell;

    public CellView(Cell cell) {
        this.cell = cell;
        setPreferredSize(new Dimension(50, 50)); // Taille de chaque cellule
        updateCell();
    }

    // Méthode pour mettre à jour l'affichage d'une cellule en fonction de son
    // contenu
    public void updateCell() {
        removeAll();

        if (cell.isWall()) {
            setBackground(Color.GRAY); // Mur
        } else if (cell.containsEnergy()) {
            setBackground(Color.YELLOW); // Energie
        } else if (cell.getPlayer() != null) {
            setBackground(Color.BLUE); // Joueur présent
        } else if (cell.getMine() != null) {
            setBackground(Color.RED); // Mine
        } else if (cell.getBomb() != null) {
            setBackground(Color.ORANGE); // Bombe
        } else {
            setBackground(Color.WHITE); // Cellule vide
        }

        revalidate();
        repaint();
    }
}