package player;

public class Coor {
    public int rowIndex, colIndex;

    public Coor (int rowIndex, int colIndex) {
        this.rowIndex = rowIndex;
        this.colIndex = colIndex;
    }

    public boolean isExplodable ( int sigilCount ) {
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
}
