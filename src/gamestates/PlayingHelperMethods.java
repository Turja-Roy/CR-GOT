package gamestates;

import static utilz.Constants.GameConstants.*;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;

import main.Game;
import player.GameData;
import player.Player;
import ui.Flags;
import ui.Sigils;

public class PlayingHelperMethods {
    protected Game game;
    protected Flags[] flags;
    protected Sigils[] sigils;

    public PlayingHelperMethods (Game game) {
        this.game = game;
    }

    protected void loadFlags () {
        flags = new Flags[GameData.numPlayers];

        int i=0;
        for (Player p : GameData.players)
            flags[i++] = new Flags(p.getID(), p.getHouse());
    }

    protected void loadSigils () {
        sigils = new Sigils[GameData.numPlayers];

        int i=0;
        for (Player p : GameData.players)
            sigils[i++] = new Sigils(p.getHouse());
    }

    protected void drawGrid (Graphics g) {
        g.setColor(GameData.players[GameData.currPlayer].getColor());
        g.fillRect(0, 0, CELL_SIZE*10+2, CELL_SIZE*10+2);
        g.setColor(Color.BLACK);

        for (int i=0 ; i<10 ; i++) {
            for (int j=0 ; j<10 ; j++) {
                g.fillRect(i*CELL_SIZE+1, j*CELL_SIZE+1, CELL_SIZE-1, CELL_SIZE-1);
            }
        }
    }

    protected void drawSigils (Graphics g) {
        for (int i=0 ; i<10 ; i++) {
            for (int j=0 ; j<10 ; j++) {
                Player whichPlayer = GameData.grid.getCell(i,j).getPlayer();
                if (whichPlayer == null) continue;
                sigils[whichPlayer.getID()].draw(g, GameData.grid.getCell(i,j).getSigilCount(), j, i);
            }
        }
    }

    protected void nextPlayer () {
        if (GameData.round++ <= GameData.numPlayers) {
            GameData.currPlayer = (GameData.currPlayer == GameData.numPlayers-1 ? 0 : GameData.currPlayer+1);
            return;
        }

        GameData.currPlayer = (GameData.currPlayer == GameData.numPlayers-1 ? 0 : GameData.currPlayer+1);
        while ( GameData.players[GameData.currPlayer].getTotalSigils() == 0 )
            GameData.currPlayer = (GameData.currPlayer == GameData.numPlayers-1 ? 0 : GameData.currPlayer+1);
    }

    protected void findWinner () {
        if (GameData.round <= GameData.numPlayers) return;

        int count=0;
        for (Player p : GameData.players) {
            if (p.getTotalSigils() == 0) count++;
            else GameData.winner = p.getID();
        }

        if (count == GameData.numPlayers-1) {
            applyGameState(game);
            System.out.println("Winner: " + GameData.players[GameData.winner]);
        }
    }

    protected void applyGameState (Game game) {
        GameState.state = GameState.GAMEOVER;
    }

    protected boolean insideBoard (MouseEvent e) {
        Rectangle bounds = new Rectangle(0, 0, CELL_SIZE*10, CELL_SIZE*10);
        return bounds.contains(e.getX(), e.getY());
    }

}
