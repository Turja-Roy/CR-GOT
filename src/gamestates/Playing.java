package gamestates;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import player.GameData;
import player.Grid;
import player.Player;
import ui.Flags;
import ui.Sigils;
import utilz.LoadSave;
import main.Game;

import static utilz.Constants.GameConstants.*;

public class Playing extends Grid implements Statemethods {
    private Flags[] flags;
    private Sigils[] sigils;

    private BufferedImage mapImage;

    private boolean firstClick = true;

    public Playing (Game game) {
        super(game);
        mapImage = LoadSave.GetImage(LoadSave.MAP_IMAGE);

        loadFlags();
        loadSigils();
    }

    @Override
    public void update () {
        for (Flags flag : flags)
            flag.update();
        if (GameData.countExplodables > 0) GameData.rotation++;
    }

    @Override
    public void draw (Graphics g) {
        drawGrid(g);

        g.drawImage(mapImage, CELL_SIZE*10, 0, CELL_SIZE*8+2, CELL_SIZE*10, null);
        for (Flags flag : flags)
            flag.draw(g);

        drawSigils(g);

        if (animExplode) {
            sigils[GameData.explodable.player].animateExplodable(g, GameData.explodable, game);
            if (animTick++ > 24) {
                animTick = 0;
                animExplode = false;
            }
        }

        if (!animExplode) checkAllExplosives();
    }

    @Override
    public void mouseClicked (MouseEvent e) {
        if (firstClick) {
            firstClick = false;
            return;
        }
        if (insideBoard(e)) {
            animTick = 0;
            boolean pass = addSigil(getCell(e), GameData.players[GameData.currPlayer]);

            if ( getCell(e).isExplodable(true) ) GameData.countExplodables++;

            if ( getCell(e).isExplosive() ) {
                explode(getCell(e), GameData.players[GameData.currPlayer]);

                animExplode = true;
            }

            if (pass) nextPlayer();
            findWinner();
        }
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

    /*
        Constructor helper methods
    */
    private void loadFlags () {
        flags = new Flags[GameData.numPlayers];

        int i=0;
        for (Player p : GameData.players)
            flags[i++] = new Flags(p.getID(), p.getHouse());
    }
    private void loadSigils () {
        sigils = new Sigils[GameData.numPlayers];

        int i=0;
        for (Player p : GameData.players)
            sigils[i++] = new Sigils(p.getHouse());
    }

    /*
        Drawing helper methods
    */
    private void drawGrid (Graphics g) {
        g.setColor(GameData.players[GameData.currPlayer].getColor());
        g.fillRect(0, 0, CELL_SIZE*10+2, CELL_SIZE*10+2);
        g.setColor(Color.BLACK);

        for (int i=0 ; i<10 ; i++) {
            for (int j=0 ; j<10 ; j++) {
                g.fillRect(i*CELL_SIZE+1, j*CELL_SIZE+1, CELL_SIZE-1, CELL_SIZE-1);
            }
        }
    }
    private void drawSigils (Graphics g) {
        for (int i=0 ; i<10 ; i++) {
            for (int j=0 ; j<10 ; j++) {
                Player whichPlayer = getCell(i,j).getPlayer();
                if (whichPlayer == null) continue;
                sigils[whichPlayer.getID()].draw(g, getCell(i,j), i, j);
            }
        }
    }

    /*
        Game logic methods
    */
    private void nextPlayer () {
        if (GameData.round++ <= GameData.numPlayers) {
            GameData.currPlayer = (GameData.currPlayer == GameData.numPlayers-1 ? 0 : GameData.currPlayer+1);
            return;
        }

        GameData.currPlayer = (GameData.currPlayer == GameData.numPlayers-1 ? 0 : GameData.currPlayer+1);
        while ( GameData.players[GameData.currPlayer].getTotalSigils() == 0 )
            GameData.currPlayer = (GameData.currPlayer == GameData.numPlayers-1 ? 0 : GameData.currPlayer+1);
    }
    private void findWinner () {
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

    /*
        Misc methods
    */
    private void applyGameState (Game game) {
        GameState.state = GameState.GAMEOVER;
    }

    private boolean insideBoard (MouseEvent e) {
        Rectangle bounds = new Rectangle(0, 0, CELL_SIZE*10, CELL_SIZE*10);
        return bounds.contains(e.getX(), e.getY());
    }
}
