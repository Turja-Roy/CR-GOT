package gamestates;

import java.awt.Graphics;
import java.awt.event.MouseEvent;

import player.Player;
import main.Game;

public class Playing extends State implements Statemethods {
    private Player[] players;
    private int numPlayers;
    private int round;

    public Playing (Game game) {
        super(game);
        initClasses();
    }

    private void initClasses () {
        // players = new Player[2];
        // players[0] = new Player(game, 1);
        // players[1] = new Player(game, 2);
    }

    @Override
    public void update () {
        // TODO Auto-generated method stub
    }

    @Override
    public void draw (Graphics g) {
        // TODO Auto-generated method stub
    }

    @Override
    public void mouseClicked (MouseEvent e) {
        // TODO Auto-generated method stub
    }

    @Override
    public void mousePressed (MouseEvent e) {
        // TODO Auto-generated method stub
    }

    @Override
    public void mouseReleased (MouseEvent e) {
        // TODO Auto-generated method stub
    }

    @Override
    public void mouseMoved (MouseEvent e) {
        // TODO Auto-generated method stub
    }

    // Getters and Setters
    public int getNumPlayers () {
        return numPlayers;
    }
    public void setNumPlayers (int numPlayers) {
        this.numPlayers = numPlayers;
    }
}
