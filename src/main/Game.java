package main;

import java.awt.Graphics;

import gamestates.GameState;
import gamestates.Menu;
import gamestates.Playing;

public class Game implements Runnable {
    private GameWindow gameWindow;
    private GamePanel gamePanel;
    private Thread thread;
    private final int FPS = 120;
    private final int UPS = 200;

    private Menu menu;
    private Playing playing;
    
    public Game () {
        initClasses();

        gamePanel = new GamePanel(this);
        gameWindow = new GameWindow(gamePanel);

        gamePanel.requestFocus();
        startGame();
    }

    private void initClasses() {
        menu = new Menu(this);
        playing = new Playing(this);
    }

    private void startGame() {
        thread = new Thread(this);
        thread.start();
    }

    public void update () {
        switch (GameState.state) {
            case MENU:
                menu.update();
                break;
            case PLAYING:
                playing.update();
                break;
            case GAMEOVER:
            default:
                System.exit(0);
                break;
        }
    }

    public void render (Graphics g) {
        switch (GameState.state) {
            case MENU:
                menu.draw(g);
                break;
            case PLAYING:
                playing.draw(g);
                break;
            default:
                break;
        }
    }

    @Override
    public void run () {
        double tpf = 1_000_000_000.0 / FPS;
        double tpu = 1_000_000_000.0 / UPS;

        long prevTime = System.nanoTime();

        int frames = 0;
        int updates = 0;
        long lastCheck = System.currentTimeMillis();

        double deltaU = 0;
        double deltaF = 0;

        while (true) {
            long currTime = System.nanoTime();
            deltaU += (currTime - prevTime) / tpu;
            deltaF += (currTime - prevTime) / tpf;
            prevTime = currTime;

            if (deltaU >= 1) {
                update();
                updates++;
                deltaU--;
            }

            if (deltaF >= 1) {
                gamePanel.repaint();
                frames++;
                deltaF--;
            }

            if (System.currentTimeMillis() - lastCheck >= 1000) {
                lastCheck = System.currentTimeMillis();
                System.out.println("FPS: " + frames + " | UPS: " + updates);
                frames = 0;
                updates = 0;
            }
        }
    }

    public void windowFocusLost () {
        // if (GameState.state == GameState.PLAYING)
        //     // TODO: Pause the game
    }

    // Getters
    public Menu getMenu () {
        return menu;
    }
    public Playing getPlaying () {
        return playing;
    }
}
