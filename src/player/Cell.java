package player;

public class Cell {
    private int rowIndex, colIndex;
    private Player player;
    private int sigilCount;

    Cell (int rowIndex, int colIndex, Player player, int sigilCount) {
        this.rowIndex = rowIndex;
        this.colIndex = colIndex;
        this.player = player;
        this.sigilCount = sigilCount;
    }
    Cell (int rowIndex, int colIndex) {
        this(rowIndex, colIndex, null, 0);
    }

    public boolean isExplodable () {
        // Corners
        if ( (rowIndex == 0 && colIndex == 0) ||
             (rowIndex == 0 && colIndex == 9) ||
             (rowIndex == 9 && colIndex == 0) ||
             (rowIndex == 9 && colIndex == 9) )
            return sigilCount == 1;

        // Edges
        else if (rowIndex == 0 || rowIndex == 9 || colIndex == 0 || colIndex == 9)
            return sigilCount == 2;

        // Center
        else
            return sigilCount == 3;
    }
    public boolean isEmpty () {
        return player == null;
    }
    public boolean isOccupiedBy (Player player) {
        return this.player == player;
    }
    public void addSigil () {
        sigilCount++;
    }
    public void addSigil (Player player) {
        sigilCount++;
        this.player = player;
    }
    public void emptyCell () {
        player = null;
        sigilCount = 0;
    }

    // Getters and Setters
    public int getRowIndex () {
        return rowIndex;
    }
    public int getColIndex () {
        return colIndex;
    }
    public Player getPlayer () {
        return player;
    }
    public void setPlayer (Player player) {
        this.player = player;
    }
    public int getSigilCount () {
        return sigilCount;
    }
    public void setSigilCount (int sigilCount) {
        this.sigilCount = sigilCount;
    }
}
