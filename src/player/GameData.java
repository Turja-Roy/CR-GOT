package player;

import main.Game;

public class GameData {
    public static Player[] players;
    public static Player currPlayer;
    public static Grid grid;
    public static int numPlayers;
    public static int round = 0;

    public GameData (Game game) {
        grid = new Grid();
    }

    public static void initPlayers () {
        players = new Player[numPlayers];
        for (int i=0 ; i<numPlayers ; i++)
            players[i] = new Player(i);
    }

    public static void printStates () {
        System.out.println("Numplayers: " + numPlayers);
        System.out.println("Round: " + round);

        // for (int i=0 ; i<10 ; i++) {
        //     for (int j=0 ; j<10 ; j++) {
        //         System.out.println("");
        //     }
        // }
    }
}
