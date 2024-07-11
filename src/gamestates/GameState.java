package gamestates;

public enum GameState {
    MENU, NUMPLAYER, HOUSE_SELECTION, PLAYING, RULES, QUIT, GAMEOVER;

    public static GameState state = MENU;

    public static int numPlayers = 2;
}
