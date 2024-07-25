package gamestates;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import player.Explodables;
import player.GameData;
import player.Grid;
import player.Player;
import ui.Flags;
import ui.Sigils;
import utilz.LoadSave;
import main.Game;
import static utilz.Constants.GameConstants.*;

public class Playing extends Grid implements Statemethods {
    private Game game;
    private Flags[] flags;
    private Sigils[] sigils;

    private BufferedImage mapImage;
    private int animTick=0, animInd=0, animDone=0;

    private boolean firstClick = true;

    public Playing (Game game) {
        super();
        this.game = game;
        mapImage = LoadSave.GetImage(LoadSave.MAP_IMAGE);

        loadFlags();
        loadSigils();
    }

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

    @Override
    public void update () {
        for (Flags flag : flags)
            flag.update();
    }

    @Override
    public void draw (Graphics g) {
        if ( !GameData.explodables.isEmpty() ) {
            animTick++;

            drawGrid(g);
            
            Explodables ex = GameData.explodables.getFirst();
            sigils[ex.player].animateExplodable(g, ex, game);

            if (animInd == GameData.explodables.size()) {
                animTick = animInd = animDone = 0;
                GameData.explodables.clear();
            }

            g.drawImage(mapImage, CELL_SIZE*10, 0, CELL_SIZE*8+2, CELL_SIZE*10, null);
            for (Flags flag : flags)
            flag.draw(g);

            drawSigils(g);

            if (animTick <= 48) return;
            else {
                animInd++;
                GameData.explodables.removeFirst();
            }

            // for (Explodables ex : GameData.explodables) {
            //     if (animInd++ == animDone) {
            //         sigils[ex.player].animateExplodable(g, ex, game);
            //         if (animTick <= 24) return;
            //     }
            //     if (animTick >= 24) animDone++;
            // }
        }

        drawGrid(g);

        g.drawImage(mapImage, CELL_SIZE*10, 0, CELL_SIZE*8+2, CELL_SIZE*10, null);
        for (Flags flag : flags)
            flag.draw(g);

        drawSigils(g);
    }

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
                sigils[whichPlayer.getID()].draw(g, getCell(i,j).getSigilCount(), i, j);
            }
        }
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
            if (pass) nextPlayer();

            findWinner();
        }
    }

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

    private void applyGameState (Game game) {
        GameState.state = GameState.GAMEOVER;
    }

    private boolean insideBoard (MouseEvent e) {
        Rectangle bounds = new Rectangle(0, 0, CELL_SIZE*10, CELL_SIZE*10);
        return bounds.contains(e.getX(), e.getY());
    }
}
