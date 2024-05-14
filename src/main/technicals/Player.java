package main.technicals;

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

    // Operations
    public void addSigil () {
        totalSigils++;
    }
    public void addSigils(int sigils) {
        totalSigils += sigils;
    }
    public void removeSigil () {
        totalSigils--;
    }
    public void removeSigils(int sigils) {
        totalSigils -= sigils;
    }

    // Setters
    public void setId (int id) {
        this.id = id;
    }
    public void setHouse(int house) {
        this.house = house;
    }

}
