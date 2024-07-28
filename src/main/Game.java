package main;

import java.awt.Graphics;

import gamestates.GameState;
import gamestates.HouseSelection;
import gamestates.Menu;
import gamestates.NumPlayer;
import gamestates.Playing;
import gamestates.Rules;

public class Game implements Runnable {
    private GameWindow gameWindow;
    private GamePanel gamePanel;
    private Thread thread;
    private final int FPS = 120;
    private final int UPS = 200;

    private Menu menu;
    private Rules rules;
    private NumPlayer numPlayer;
    private Playing playing;
    private HouseSelection houseSelection;

    private Graphics g;
    
    public Game () {
        initClasses();

        gamePanel = new GamePanel(this);
        gameWindow = new GameWindow(gamePanel);

        gamePanel.requestFocus();
        startGame();
    }

    private void initClasses() {
        menu = new Menu();
        rules = new Rules();
        numPlayer = new NumPlayer();
        // playing = new Playing(this);
        houseSelection = new HouseSelection(this);
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
            case RULES:
                rules.update();
                break;
            case NUMPLAYER:
                numPlayer.update();
                break;
            case HOUSE_SELECTION:
                houseSelection.update();
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
        this.g = g;
        switch (GameState.state) {
            case MENU:
                menu.draw(g);
                break;
            case RULES:
                rules.draw(g);
                break;
            case NUMPLAYER:
                numPlayer.draw(g);
                break;
            case HOUSE_SELECTION:
                houseSelection.draw(g);
                break;
            case PLAYING:
                playing.draw(g);
                break;
            case GAMEOVER:
            default:
                break;
        }
    }

    @Override
    public void run () {
        double tpf = 1_000_000_000.0 / FPS;
        double tpu = 1_000_000_000.0 / UPS;

        long prevTime = System.nanoTime();

        // int frames = 0;
        // int updates = 0;
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
                // updates++;
                deltaU--;
            }

            if (deltaF >= 1) {
                gamePanel.repaint();
                // frames++;
                deltaF--;
            }

            if (System.currentTimeMillis() - lastCheck >= 1000) {
                lastCheck = System.currentTimeMillis();
                // System.out.println("FPS: " + frames + " | UPS: " + updates);
                // frames = 0;
                // updates = 0;
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
    public Rules getRules () {
        return rules;
    }
    public NumPlayer getNumPlayer () {
        return numPlayer;
    }
    public Playing getPlaying () {
        return playing;
    }
    public HouseSelection getHouseSelection () {
        return houseSelection;
    }
    public GamePanel getGamePanel () {
        return gamePanel;
    }

    public void initPlaying () {
        playing = new Playing(this);
    }

    // Give a break
    public void giveAbreak (long milis) {
        try {
            thread.sleep(milis);
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}
