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

    // Checker methods
    public boolean isExplodable () {
        // System.out.println("Here! row: " + rowIndex + " | col: " + colIndex);
        // Corners
        if ( (rowIndex == 0 && colIndex == 0) ||
             (rowIndex == 0 && colIndex == 9) ||
             (rowIndex == 9 && colIndex == 0) ||
             (rowIndex == 9 && colIndex == 9) ) {
            if (sigilCount == 1) {
                GameData.explodables.add(new Explodables(player.getID(), rowIndex, colIndex));
                System.out.println("Explodable at row=" + rowIndex + ", col=" + colIndex);
            }
            return sigilCount == 1;
        }


        // Edges
        else if (rowIndex == 0 || rowIndex == 9 || colIndex == 0 || colIndex == 9) {
            if (sigilCount == 2) {
                GameData.explodables.add(new Explodables(player.getID(), rowIndex, colIndex));
                System.out.println("Explodable at row=" + rowIndex + ", col=" + colIndex);
            }
            return sigilCount == 2;
        }

        // Center
        else {
            if (sigilCount == 3) {
                GameData.explodables.add(new Explodables(player.getID(), rowIndex, colIndex));
                System.out.println("Explodable at row=" + rowIndex + ", col=" + colIndex);
            }
            return sigilCount == 3;
        }
    }
    public boolean isEmpty () {
        return player == null;
    }
    public boolean isOccupiedBy (Player player) {
        return this.player == player;
    }

    // Operational methods
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

    // Getters
    public int getRowIndex () {
        return rowIndex;
    }
    public int getColIndex () {
        return colIndex;
    }
    public Player getPlayer () {
        return player;
    }
    public int getSigilCount () {
        return sigilCount;
    }

    // Setters
    public void setPlayer (Player player) {
        this.player = player;
    }
    public void setSigilCount (int sigilCount) {
        this.sigilCount = sigilCount;
    }
}
