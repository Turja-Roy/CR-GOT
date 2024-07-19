package gamestates;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import player.GameData;
import player.Grid;
import player.Player;
import utilz.LoadSave;
import main.Game;
import static utilz.Constants.GameConstants.*;

public class Playing extends State implements Statemethods {
    private boolean paused = false;
    private BufferedImage mapImage;

    public Playing (Game game) {
        super(game);
        mapImage = LoadSave.GetImage(LoadSave.MAP_IMAGE);
    }

    @Override
    public void update () {
        if (GameData.round == 0) GameData.currPlayer = GameData.players[0];
        if (GameData.round == 0) GameData.printStates();
    }

    @Override
    public void draw (Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        drawGrid(g2d, GameData.players[0]);
        g.drawImage(mapImage, CELL_SIZE*10, 0, CELL_SIZE*8+2, CELL_SIZE*10, null);
    }

    private void drawGrid (Graphics2D g, Player player) {
        g.setColor(player.getColor());
        g.fillRect(0, 0, CELL_SIZE*10+2, CELL_SIZE*10+2);
        g.setColor(Color.BLACK);

        for (int i=0 ; i<10 ; i++) {
            for (int j=0 ; j<10 ; j++) {
                g.fillRect(i*CELL_SIZE+1, j*CELL_SIZE+1, CELL_SIZE-1, CELL_SIZE-1);
            }
        }
    }

    @Override
    public void mouseClicked (MouseEvent e) {
        if (insideBoard(e))
            GameData.grid.addSigil(GameData.grid.getCell(e), GameData.currPlayer);
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
