package main.technicals;


import static utilz.Constants.House;
import static utilz.Constants.Colors;

public class Grid {
    private Cell[][] grid;
    private int playerCount;
    private int gridSize;

    // Constructor
    public Grid (int gridSize, int playerCount) {
        this.gridSize = gridSize;
        this.playerCount = playerCount;

        grid = new Cell[gridSize][gridSize];

        for (int i = 0; i < gridSize; i++)
            for (int j = 0; j < gridSize; j++)
                grid[i][j] = new Cell(null, 0);

    }

    // Getters


    // Setters


    

}
