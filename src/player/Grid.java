package player;

import static utilz.Constants.GameConstants.*;
import static utilz.Constants.UI.GameBoard.*;

import java.awt.Rectangle;
import java.awt.event.MouseEvent;

public class Grid {
    private Cell[][] grid = new Cell[10][10];

    public final Rectangle gridBounds = new Rectangle(GB_CENTER_X - GB_WIDTH / 2, GB_CENTER_Y - GB_HEIGHT / 2, GB_WIDTH, GB_HEIGHT);

    public Grid () {
        for (int i = 0; i < 10; i++)
            for (int j = 0; j < 10; j++)
                grid[i][j] = new Cell(new Coor(i, j));
    }

    public boolean addSigil (Cell cell, Player player) { // Not exploding
        if ( !cell.isEmpty() && !cell.isOccupiedBy(player) )
            return false;

        player.addSigil();
        if ( !cell.isExplodable() ) {
            if ( cell.isEmpty() )
                cell.addSigil(player);
            else
                cell.addSigil();
        }
        else {
            explode(cell, player);
        }

        return true;
    }

    public void addSigil (Cell cell, Player player, boolean isExploding) { // Exploding
        Player prev_player = cell.getPlayer();

        if ( prev_player != null ) {
            prev_player.removeSigil( cell.getSigilCount() );
            player.addSigil( cell.getSigilCount() );
        }

        cell.setPlayer(player);
        
        if ( !cell.isExplodable() )
            addSigil(cell, player, isExploding);
        else
            explode(cell, player);
    }

    public void explode (Cell cell, Player player) {
        cell.emptyCell();
        Coor coor = cell.getCoor();

        if ( coor.rowIndex > 0 )
            addSigil(grid[coor.rowIndex - 1][coor.colIndex], player, true);
        if ( coor.rowIndex < 9 )
            addSigil(grid[coor.rowIndex + 1][coor.colIndex], player, true);
        if ( coor.colIndex > 0 )
            addSigil(grid[coor.rowIndex][coor.colIndex - 1], player, true);
        if ( coor.colIndex < 9 )
            addSigil(grid[coor.rowIndex][coor.colIndex + 1], player, true);
    }

    // Getters
    public Cell getCell (Coor coor) {
        return grid[coor.rowIndex][coor.colIndex];
    }
    public Cell getCell (int rowIndex, int colIndex) {
        return grid[rowIndex][colIndex];
    }
    public Cell getCell (MouseEvent e) {
        Coor coor = getCellCoor(e);
        return grid[coor.rowIndex][coor.colIndex];
    }

    public Coor getCellCoor (MouseEvent e) {
        int x = e.getX();
        int y = e.getY();

        if (x < GB_CENTER_X - GB_WIDTH / 2 || x > GB_CENTER_X + GB_WIDTH / 2 ||
            y < GB_CENTER_Y - GB_HEIGHT / 2 || y > GB_CENTER_Y + GB_HEIGHT / 2)
            return null;

        int rowIndex = (y - GB_CENTER_Y + GB_HEIGHT / 2) / CELL_SIZE;
        int colIndex = (x - GB_CENTER_X + GB_WIDTH / 2) / CELL_SIZE;

        return new Coor(rowIndex, colIndex);
    }
}
