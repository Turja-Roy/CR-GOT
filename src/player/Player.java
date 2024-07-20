package player;

import java.awt.Color;

import utilz.Constants;

public class Player {
    private int id;
    private int house;
    private int totalSigils;

    public Player(int id) {
        this.id = id;
        house = -1;
        totalSigils = 0;
    }

    // Operational methods
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

    // Getters
    public int getHouse () {
        return house;
    }
    public int getTotalSigils () {
        return totalSigils;
    }
    public Color getColor () {
        return Constants.Colors.getColor(this);
    }

    // Setters
    public void setHouse(int house) {
        this.house = house;
    }

    // Checker method
    public String toString () {
        return "Player " + id + " is from House " + house + " and has " + totalSigils + " sigils.";
    }
}
