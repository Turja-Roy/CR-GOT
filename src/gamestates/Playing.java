package gamestates;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import player.GameData;
import player.Player;
import ui.Flags;
import utilz.LoadSave;
import main.Game;
import static utilz.Constants.GameConstants.*;
import static utilz.Constants.UI.SigilConstants.*;

public class Playing extends State implements Statemethods {
    private Flags[] flags;
    private BufferedImage mapImage;
    private BufferedImage[] sigils = new BufferedImage[7];

    private static boolean firstClick = true;

    public Playing (Game game) {
        super(game);
        mapImage = LoadSave.GetImage(LoadSave.MAP_IMAGE);

        loadFlags();
        sigils = LoadSave.GetSigils();
    }

    private void loadFlags () {
        flags = new Flags[GameData.numPlayers];

        int i=0;
        for (Player p : GameData.players)
            flags[i++] = new Flags(p.getID(), p.getHouse());
    }

    @Override
    public void update () {
        for (Flags flag : flags)
            flag.update();
    }

    @Override
    public void draw (Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        drawGrid(g2d);
        drawSigils(g);
        g.drawImage(mapImage, CELL_SIZE*10, 0, CELL_SIZE*8+2, CELL_SIZE*10, null);

        for (Flags flag : flags)
            flag.draw(g);
    }

    private void drawGrid (Graphics2D g) {
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
                if (GameData.grid.getCell(j,i).getPlayer() == null) continue;

                int house = GameData.grid.getCell(j,i).getPlayer().getHouse();

                if (GameData.grid.getCell(j,i).getSigilCount() == 1) {
                    g.drawImage(sigils[house], (int) (CELL_SIZE*i - CELL_SIZE*0.25 + SINGLE_SIGIL_X_POS), (int) (CELL_SIZE*j - CELL_SIZE*0.25 + SINGLE_SIGIL_Y_POS), SIGIL_SIZE, SIGIL_SIZE, null);
                }
                else if (GameData.grid.getCell(j,i).getSigilCount() == 2) {
                    g.drawImage(sigils[house], (int) (CELL_SIZE*i - CELL_SIZE*0.25 + DOUBLE_SIGIL_X_POS_1), (int) (CELL_SIZE*j - CELL_SIZE*0.25 + DOUBLE_SIGIL_Y_POS_1), SIGIL_SIZE, SIGIL_SIZE, null);
                    g.drawImage(sigils[house], (int) (CELL_SIZE*i - CELL_SIZE*0.25 + DOUBLE_SIGIL_X_POS_2), (int) (CELL_SIZE*j - CELL_SIZE*0.25 + DOUBLE_SIGIL_Y_POS_2), SIGIL_SIZE, SIGIL_SIZE, null);
                }
                else if (GameData.grid.getCell(j,i).getSigilCount() == 3) {
                    g.drawImage(sigils[house], (int) (CELL_SIZE*i - CELL_SIZE*0.25 + TRIPLE_SIGIL_X_POS_1), (int) (CELL_SIZE*j - CELL_SIZE*0.25 + TRIPLE_SIGIL_Y_POS_1), SIGIL_SIZE, SIGIL_SIZE, null);
                    g.drawImage(sigils[house], (int) (CELL_SIZE*i - CELL_SIZE*0.25 + TRIPLE_SIGIL_X_POS_2), (int) (CELL_SIZE*j - CELL_SIZE*0.25 + TRIPLE_SIGIL_Y_POS_2), SIGIL_SIZE, SIGIL_SIZE, null);
                    g.drawImage(sigils[house], (int) (CELL_SIZE*i - CELL_SIZE*0.25 + TRIPLE_SIGIL_X_POS_3), (int) (CELL_SIZE*j - CELL_SIZE*0.25 + TRIPLE_SIGIL_Y_POS_3), SIGIL_SIZE, SIGIL_SIZE, null);
                }
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
            boolean pass = GameData.grid.addSigil(GameData.grid.getCell(e), GameData.players[GameData.currPlayer]);
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
            applyGameState(game, GameData.winner);
        }
    }

    private void applyGameState (Game game, int winner) {
        GameState.state = GameState.GAMEOVER;
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
}
