package player;

public class Cell {
    private Coor coor;
    private Player player;
    private int sigilCount;

    Cell (Coor coor, Player player, int sigilCount) {
        this.player = player;
        this.sigilCount = sigilCount;
    }
    Cell (Coor coor) {
        this(coor, null, 0);
    }

    public boolean isExplodable () {
        return coor.isExplodable(sigilCount);
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
    public Coor getCoor () {
        return coor;
    }
}
