package model.weapons;

import model.Position;
import model.Grid;
import model.Player;

/**
 * Auteur : Yattoura
 */

public class HorizontalShot extends Weapon {
    private int range; // Portée maximale du tir

    public HorizontalShot(int ammo, int damage, int range, Player owner) {
        super(ammo, damage, owner);
        this.range = range;
    }

    @Override
    public void useWeapon(Position position, Grid grid) {
        if (!hasAmmo()) {
            System.out.println("Pas de munitions pour " + getOwner().getName());
            return;
        }

        // Tirs vers la gauche
        for (int i = 1; i <= range; i++) {
            Position targetPosition = new Position(position.getX() - i, position.getY());
            if (!grid.isWithinBounds(targetPosition))
                break;

            Player target = grid.getCell(targetPosition).getPlayer();
            if (target != null) {
                target.reduceEnergy(damage);
                System.out.println(getOwner().getName() + " tire vers la gauche et touche " + target.getName()
                        + " infligeant " + damage + " points de dégâts.");
                break; // Arrêter le tir après avoir touché un joueur
            }
        }

        // Tirs vers la droite
        for (int i = 1; i <= range; i++) {
            Position targetPosition = new Position(position.getX() + i, position.getY());
            if (!grid.isWithinBounds(targetPosition))
                break;

            Player target = grid.getCell(targetPosition).getPlayer();
            if (target != null) {
                target.reduceEnergy(damage);
                System.out.println(getOwner().getName() + " tire vers la droite et touche " + target.getName()
                        + " infligeant " + damage + " points de dégâts.");
                break; // Arrêter le tir après avoir touché un joueur
            }
        }

        consumeAmmo();
    }
}