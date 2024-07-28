package player;

import static utilz.Constants.GameConstants.*;
import static utilz.Constants.UI.GameBoard.*;

import java.awt.event.MouseEvent;

import main.Game;

public class Grid {
    protected Game game;
    protected Cell[][] grid = new Cell[10][10];

    protected int animTick = 0;
    protected boolean animExplode=false;

    public Grid (Game game) {
        this.game = game;

        for (int i = 0; i < 10; i++)
            for (int j = 0; j < 10; j++)
                grid[i][j] = new Cell(i,j);

        GameData.winner = -1;
        GameData.rotation = 0;
        GameData.countExplodables = 0;
    }

    protected boolean addSigil (Cell cell, Player player) {
        // Non-empty cell occupied by other player
        if ( !cell.isEmpty() && !cell.isOccupiedBy(player) )
            return false;

        // Empty or occupied by current player
        player.addSigil();
        cell.addSigil(player);

        game.getGamePanel().repaint();

        return true;
    }

    protected boolean explode (Cell cell, Player player) {
        GameData.countExplodables--;
        cell.emptyCell();
        int rowIndex=cell.getRowIndex(), colIndex=cell.getColIndex();

        GameData.explodable = new Explodables(player.getID(), cell.getRowIndex(), cell.getColIndex());
        animExplode = true;
        animTick = 0;

        game.getGamePanel().repaint();

        boolean flag = false;
        if ( rowIndex > 0 )
            addExplodingSigil(grid[rowIndex - 1][colIndex], player);
        if ( rowIndex < 9 )
            addExplodingSigil(grid[rowIndex + 1][colIndex], player);
        if ( colIndex > 0 )
            addExplodingSigil(grid[rowIndex][colIndex - 1], player);
        if ( colIndex < 9 )
            addExplodingSigil(grid[rowIndex][colIndex + 1], player);

        return flag;
    }

    private void addExplodingSigil (Cell cell, Player player) { // Exploding
        Player prev_player = cell.getPlayer();

        // Empty or occupied by current player
        if (prev_player == null || prev_player == player) {
            cell.addSigil(player);
            return;
        }

        // Occupied by other player
        prev_player.removeSigil( cell.getSigilCount() );
        player.addSigil( cell.getSigilCount() );
        cell.setPlayer(player);

        if ( cell.isExplosive() )
            explode(cell, player);
        else
            cell.addSigil();
    }

    protected void checkAllExplosives () {
        for (int i=0 ; i<10 ; i++)
            for (int j=0 ; j<10 ; j++)
                if ( grid[i][j].isExplosive() )
                    explode(grid[i][j], grid[i][j].getPlayer());
    }

    // Getters
    protected Cell getCell (int rowIndex, int colIndex) {
        return grid[rowIndex][colIndex];
    }
    protected Cell getCell (MouseEvent e) {
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
