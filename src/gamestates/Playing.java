package gamestates;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import player.Explodables;
import player.GameData;
import ui.Flags;
import ui.Sigils;
import utilz.LoadSave;
import main.Game;
import static utilz.Constants.GameConstants.*;

public class Playing extends PlayingHelperMethods implements Statemethods {
    private Game game;
    private BufferedImage mapImage;
    private long animTick=0;

    private static boolean firstClick = true;

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
        animTick++;
    }

    @Override
    public void draw (Graphics g) {
        drawGrid(g);
        g.drawImage(mapImage, CELL_SIZE*10, 0, CELL_SIZE*8+2, CELL_SIZE*10, null);

        for (Flags flag : flags)
            flag.draw(g);

        if (GameData.explodables != null) {
            for (Explodables ex : GameData.explodables) {
                sigils[ex.player].animateExplodable(g, ex, game);
            }
        }

        if (animTick > 24) {
            animTick = 0;
            GameData.explodables.clear();
        }

        drawSigils(g);
    }

    @Override
    public void mouseClicked (MouseEvent e) {
        if (firstClick) {
            firstClick = false;
            return;
        }
        if (insideBoard(e)) {
            animTick = 0;
            boolean pass = GameData.grid.addSigil(GameData.grid.getCell(e), GameData.players[GameData.currPlayer]);
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

}
