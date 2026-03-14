package model;

import model.weapons.Bomb;
import model.weapons.Mine;

public class Cell {
    private Position position;
    private boolean isWall;
    private boolean containsEnergy;
    private Mine mine;
    private Bomb bomb;
    private Player player;

    /**
     * Auteur : Yattoura
     */

    public Cell(Position position) {
        this.position = position;
        this.isWall = false;
        this.containsEnergy = false;
        this.mine = null;
        this.bomb = null;
        this.player = null;
    }

    public void setWall(boolean isWall) {
        this.isWall = isWall;
    }

    public void setEnergy(boolean containsEnergy) {
        this.containsEnergy = containsEnergy;
    }

    public void setMine(Mine mine) {
        this.mine = mine;
    }

    public void setBomb(Bomb bomb) {
        this.bomb = bomb;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }

    public boolean isWall() {
        return isWall;
    }

    public boolean containsEnergy() {
        return containsEnergy;
    }

    public Mine getMine() {
        return mine;
    }

    public Bomb getBomb() {
        return bomb;
    }

    public Position getPosition() {
        return position;
    }

    public boolean isContainsEnergy() {
        return containsEnergy;
    }

}