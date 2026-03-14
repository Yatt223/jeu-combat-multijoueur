package model.weapons;

import model.Player;
import model.Position;
import model.Grid;

/**
 * Auteur : Yattoura
 */

public class Mine extends Weapon {
    private boolean isVisibleToAll; // Indique si la mine est visible pour tous les joueurs

    public Mine(int damage, Player owner, boolean isVisibleToAll) {
        super(1, damage, owner); // Une mine a généralement une seule utilisation
        this.isVisibleToAll = isVisibleToAll;
    }

    @Override
    public void useWeapon(Position targetPosition, Grid grid) {
        grid.getCell(targetPosition).setMine(this); // Place la mine sur la grille
        ammo--;
    }

    // Vérifie si la mine est visible pour un joueur spécifique
    public boolean isVisibleTo(Player player) {
        return isVisibleToAll || player == getOwner();
    }
}