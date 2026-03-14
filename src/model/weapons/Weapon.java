package model.weapons;

import model.Player;
import model.Position;
import model.Grid;

/**
 * Auteur : Yattoura
 */

public abstract class Weapon {
    protected int ammo;        // Nombre de munitions restantes
    protected int damage;      // Dégâts infligés par l'arme
    protected Player owner;    // Propriétaire de l'arme

    public Weapon(int ammo, int damage, Player owner) {
        this.ammo = ammo;
        this.damage = damage;
        this.owner = owner;
    }

    // Getter pour le nombre de munitions restantes
    public int getAmmo() {
        return ammo;
    }

    // Vérifie si l'arme a encore des munitions
    public boolean hasAmmo() {
        return ammo > 0;
    }

    // Consomme une munition après chaque utilisation de l'arme
    protected void consumeAmmo() {
        if (ammo > 0) {
            ammo--;
            System.out.println(owner.getName() + " utilise une munition. Munitions restantes: " + ammo);
        } else {
            System.out.println("Pas de munitions restantes pour " + owner.getName());
        }
    }

    // Méthode abstraite à implémenter par les sous-classes pour définir l'utilisation de l'arme
    public abstract void useWeapon(Position targetPosition, Grid grid);

    // Retourne le propriétaire de l'arme
    public Player getOwner() {
        return owner;
    }
}