package model.weapons;

import model.Player;
import model.Position;
import model.Grid;

/**
 * Auteur : Yattoura
 */

public class Bomb extends Weapon {
    private int timer; // Compte à rebours avant l'explosion

    public Bomb(int damage, int timer, Player owner) {
        super(1, damage, owner); // Une bombe a généralement une seule utilisation
        this.timer = timer;
    }

    @Override
    public void useWeapon(Position targetPosition, Grid grid) {
        grid.getCell(targetPosition).setBomb(this); // Place la bombe sur la grille
        ammo--;
    }

    // Décompte le timer et déclenche l'explosion si le temps est écoulé
    public void tick() {
        if (timer > 0) {
            timer--;
        } else {
            explode();
        }
    }

    // Explosion de la bombe
    private void explode() {
        System.out.println("La bombe de " + getOwner().getName() + " explose !");
        // Logique pour infliger des dégâts aux joueurs dans la zone d'explosion
    }
}