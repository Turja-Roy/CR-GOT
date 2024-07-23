package player;

import static utilz.Constants.GameConstants.*;
import static utilz.Constants.UI.GameBoard.*;

import java.awt.Graphics;
import java.awt.event.MouseEvent;

import main.Game;
import ui.Sigils;

public class Grid {
    private Cell[][] grid = new Cell[10][10];
    private Game game;

    public Grid (Game game) {
        this.game = game;
        for (int i = 0; i < 10; i++)
            for (int j = 0; j < 10; j++)
                grid[i][j] = new Cell(i,j);
    }

    public boolean addSigil (Cell cell, Player player) { // Not exploding
        // Non-empty cell occupied by other player
        if ( !cell.isEmpty() && !cell.isOccupiedBy(player) )
            return false;

        // Empty or occupied by current player
        player.addSigil();
        if ( cell.isExplodable() )
            explode(cell, player);
        else
            cell.addSigil(player);

        for (Player p : GameData.players) // For checking
            System.out.println(p);

        return true;
    }

    private void addSigil (Cell cell, Player player, boolean isExploding) { // Exploding
        Player prev_player = cell.getPlayer();

        // Empty or occupied by current player
        if (prev_player == null || prev_player == player) {
            if ( cell.isExplodable() )
                explode(cell, player);
            else
                cell.addSigil(player);
            return;
        }

        // Occupied by other player
        prev_player.removeSigil( cell.getSigilCount() );
        player.addSigil( cell.getSigilCount() );
        cell.setPlayer(player);

        if ( cell.isExplodable() )
            explode(cell, player);
        else
            cell.setSigilCount( cell.getSigilCount()+1 );
    }

    private void explode (Cell cell, Player player) {
        cell.emptyCell();
        int rowIndex=cell.getRowIndex(), colIndex=cell.getColIndex();

        if ( rowIndex > 0 )
            addSigil(grid[rowIndex - 1][colIndex], player, true);
        if ( rowIndex < 9 )
            addSigil(grid[rowIndex + 1][colIndex], player, true);
        if ( colIndex > 0 )
            addSigil(grid[rowIndex][colIndex - 1], player, true);
        if ( colIndex < 9 )
            addSigil(grid[rowIndex][colIndex + 1], player, true);
    }

    // Getters
    public Cell getCell (int rowIndex, int colIndex) {
        return grid[rowIndex][colIndex];
    }
    public Cell getCell (MouseEvent e) {
        int x = e.getX();
        int y = e.getY();

        if (x < 0 || x > GB_WIDTH ||
            y < 0 || y > GB_HEIGHT )
            return null;

        int colIndex = x / CELL_SIZE;
        int rowIndex = y / CELL_SIZE;

        return grid[rowIndex][colIndex];
    }
}
