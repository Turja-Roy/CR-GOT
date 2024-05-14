package main.visuals;

public class Game implements Runnable {
    private GameWindow gameWindow;
    private GamePanel gamePanel;
    private Thread thread;
    
    public Game () {
        gamePanel = new GamePanel();
        gameWindow = new GameWindow(gamePanel);

        gamePanel.requestFocus();
        startGame();
    }

    private void startGame() {
        thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {

    }
}
