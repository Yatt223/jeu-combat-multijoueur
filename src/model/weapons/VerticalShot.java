package model.weapons;

import model.Position;
import model.Grid;
import model.Player;

/**
 * Auteur : Yattoura
 */

public class VerticalShot extends Weapon {
    private int range; // Portée maximale du tir

    public VerticalShot(int ammo, int damage, int range, Player owner) {
        super(ammo, damage, owner);
        this.range = range;
    }

    @Override
    public void useWeapon(Position position, Grid grid) {
        if (!hasAmmo()) {
            System.out.println("Pas de munitions pour " + getOwner().getName());
            return;
        }

        // Tirs vers le haut
        for (int i = 1; i <= range; i++) {
            Position targetPosition = new Position(position.getX(), position.getY() - i);
            if (!grid.isWithinBounds(targetPosition))
                break;

            Player target = grid.getCell(targetPosition).getPlayer();
            if (target != null) {
                target.reduceEnergy(damage);
                System.out.println(getOwner().getName() + " tire vers le haut et touche " + target.getName()
                        + " infligeant " + damage + " points de dégâts.");
                break; // Arrêter le tir après avoir touché un joueur
            }
        }

        // Tirs vers le bas
        for (int i = 1; i <= range; i++) {
            Position targetPosition = new Position(position.getX(), position.getY() + i);
            if (!grid.isWithinBounds(targetPosition))
                break;

            Player target = grid.getCell(targetPosition).getPlayer();
            if (target != null) {
                target.reduceEnergy(damage);
                System.out.println(getOwner().getName() + " tire vers le bas et touche " + target.getName()
                        + " infligeant " + damage + " points de dégâts.");
                break; // Arrêter le tir après avoir touché un joueur
            }
        }

        consumeAmmo();
    }
}