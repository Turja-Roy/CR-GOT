package gamestates;

import static utilz.Constants.GameConstants.*;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import player.GameData;
import utilz.LoadSave;
import utilz.Constants.Colors;
import utilz.Constants.House;

public class GameOver implements Statemethods {
    private BufferedImage bgImage;

    public GameOver () {
        bgImage = LoadSave.GetImage(LoadSave.GAME_OVER);
    }

    @Override
    public void update () {

    }

    @Override
    public void draw (Graphics g) {
        g.drawImage(bgImage, 0, 0, GAME_WIDTH, GAME_HEIGHT, null);

        Font font = new Font("Book Antiqua", Font.BOLD, 30);
        String text = "House " + House.getHouseName(GameData.players[GameData.winner]) + " (Player " + (GameData.winner+1) + ")" + " takes the Iron Throne";

        FontRenderContext frc = new FontRenderContext(null, true, true);

        Rectangle r = new Rectangle(0, 0, GAME_WIDTH, GAME_HEIGHT);
        Rectangle2D r2D = font.getStringBounds(text, frc);
        int rWidth = (int) Math.round(r2D.getWidth());
        int rHeight = (int) Math.round(r2D.getHeight());
        int rX = (int) Math.round(r2D.getX());
        int rY = (int) Math.round(r2D.getY());

        int a = (r.width / 2) - (rWidth / 2) - rX;
        int b = (r.height / 2) - (rHeight / 2) - rY;

        g.setColor(Colors.getColor(GameData.players[GameData.winner]));

        g.fillRect(r.x + a - 50, r.y + b - 55, rWidth + 100, rHeight + 60);

        g.setFont(font);
        g.setColor(new Color(255, 255, 255));

        g.drawString(text, r.x + a, r.y + b);
    }

    @Override
    public void mouseClicked (MouseEvent e) {

    }

    @Override
    public void mousePressed (MouseEvent e) {

    }

    @Override
    public void mouseReleased (MouseEvent e) {

    }

    @Override
    public void mouseMoved (MouseEvent e) {

    }

}
