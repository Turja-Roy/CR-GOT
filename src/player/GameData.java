package player;

import main.Game;

public class GameData {
    public static Player[] players;
    public static int[] houses;
    public static int currPlayer = 0;
    public static Grid grid;
    public static int numPlayers;
    public static int round = 1;

    public GameData (Game game) {
        grid = new Grid();
    }

    public static void initPlayers () {
        players = new Player[numPlayers];
        
        for (int i=0 ; i<numPlayers ; i++)
            players[i] = new Player(i);
    }

    public static void initHouses () {
        houses = new int[numPlayers];

        for (int i=0 ; i<numPlayers ; i++)
            houses[i] = players[i].getHouse();
    }

    public static void printStates () {
        System.out.println("Numplayers: " + numPlayers);
        System.out.println("Round: " + round);

        for (int i=0 ; i<numPlayers ; i++)
            System.out.println(players[i].toString());
        for (int h : houses)
            System.out.println(h);
    }

    public static void nextPlayer () {
        round++;
        if (currPlayer+1 == numPlayers) currPlayer = 0;
        else currPlayer++;
    }
}
