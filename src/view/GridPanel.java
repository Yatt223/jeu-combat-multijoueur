package view;

import model.Grid;
import model.Position;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Auteur : Yattoura
 */

public class GridPanel extends JPanel {
    private Grid grid;
    private int cellSize = 40; // Taille des cellules en pixels
    private Position selectedPosition; // Dernière cellule cliquée

    public interface CellClickListener {
        void onCellClick(Position position);
    }

    private CellClickListener clickListener;

    public GridPanel(Grid grid) {
        this.grid = grid;
        setPreferredSize(new Dimension(grid.getSize() * cellSize, grid.getSize() * cellSize));

        // Gestion des clics
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int col = e.getX() / cellSize;
                int row = e.getY() / cellSize;
                selectedPosition = new Position(col, row);
                if (clickListener != null) {
                    clickListener.onCellClick(selectedPosition);
                }
                repaint();
            }
        });
    }

    public void setCellClickListener(CellClickListener listener) {
        this.clickListener = listener;
    }

    public void updateGrid(Grid grid) {
        this.grid = grid;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (int row = 0; row < grid.getSize(); row++) {
            for (int col = 0; col < grid.getSize(); col++) {
                int x = col * cellSize;
                int y = row * cellSize;

                g.setColor(Color.GRAY);
                g.drawRect(x, y, cellSize, cellSize);

                if (grid.getCell(new Position(col, row)).getPlayer() != null) {
                    g.setColor(Color.BLUE); // Joueur présent
                    g.fillRect(x + 1, y + 1, cellSize - 2, cellSize - 2);
                } else if (selectedPosition != null && selectedPosition.equals(new Position(col, row))) {
                    g.setColor(Color.YELLOW); // Cellule sélectionnée
                    g.fillRect(x + 1, y + 1, cellSize - 2, cellSize - 2);
                }
            }
        }
    }
}