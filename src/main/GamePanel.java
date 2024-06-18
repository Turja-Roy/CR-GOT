package main;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;
import inputs.MouseInputs;

import static utilz.Constants.GameConstants.*;

public class GamePanel extends JPanel {
    private MouseInputs mouseInputs;
    private Game game;

    public GamePanel () {
        mouseInputs = new MouseInputs(this);
        this.game = game;

        setPanel();
        addMouseListener(mouseInputs);
        // addMouseMotionListener(mouseInputs);
    }

    private void setPanel () {
        Dimension size = new Dimension(GAME_WIDTH, GAME_HEIGHT);
        setPreferredSize(size);
        System.out.println("Size: " + GAME_WIDTH + " : " + GAME_HEIGHT);
    }

    public void paintComponent (Graphics g) {
        super.paintComponent(g);
        game.render(g);
    }

    public Game getGame () {
        return game;
    }
}
