package player;

import java.awt.Color;

import utilz.Constants;

public class Player {
    private int id;
    private int house;
    private int totalSigils;

    // Constructor
    public Player(int id, int house) {
        this.id = id;
        this.house = house;
        totalSigils = 0;
    }
    public Player(int id) {
        this.id = id;
        house = -1;
        totalSigils = 0;
    }

    // Getters
    public int getId () {
        return id;
    }
    public int getHouse () {
        return house;
    }
    public int getTotalSigils () {
        return totalSigils;
    }

    public String toString () {
        return "Player " + id + " is from House " + house + " and has " + totalSigils + " sigils.";
    }

    public Color getColor () {
        return Constants.Colors.getColor(this);
    }

    // Operations
    public void addSigil () {
        totalSigils++;
    }
    public void addSigil (int sigils) {
        totalSigils += sigils;
    }
    public void removeSigil () {
        totalSigils--;
    }
    public void removeSigil (int count) {
        totalSigils -= count;
    }

    // Setters
    public void setId (int id) {
        this.id = id;
    }
    public void setHouse(int house) {
        this.house = house;
    }

}
