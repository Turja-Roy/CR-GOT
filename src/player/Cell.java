package player;

public class Cell {
    private Player player;
    private int sigilCount;

    public Cell (Player player, int sigilCount) {
        this.player = player;
        this.sigilCount = sigilCount;
    }

    public Player getPlayer () {
        return player;
    }
    public int getSigilCount () {
        return sigilCount;
    }

    public void setPlayer (Player player) {
        this.player = player;
    }
    public void setSigilCount (int sigilCount) {
        this.sigilCount = sigilCount;
    }
}
