package player;

public class GameData {
    public static Player[] players;
    public static int[] houses;
    public static int currPlayer = 0;
    public static int numPlayers;
    public static int round = 1;
    public static int winner;

    public static int rotation;
    public static int countExplodables;

    public static Explodables explodable;

    // Initialize players
    public static void initPlayers () {
        players = new Player[numPlayers];
        
        for (int i=0 ; i<numPlayers ; i++)
            players[i] = new Player(i);
    }

    // Initialize houses
    public static void initHouses () {
        houses = new int[numPlayers];

        for (int i=0 ; i<numPlayers ; i++)
            houses[i] = players[i].getHouse();
    }

    // Checker method
    public static void printStates () {
        System.out.println("Numplayers: " + numPlayers);
        System.out.println("Round: " + round);

        for (int i=0 ; i<numPlayers ; i++)
            System.out.println(players[i].toString());
        for (int h : houses)
            System.out.println(h);
    }
}
