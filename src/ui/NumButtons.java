package ui;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import gamestates.GameState;
import utilz.LoadSave;

import static utilz.Constants.UI.NumPlayerButtons.*;

public class NumButtons {
    private int xPos, yPos, whichButton, index;
    private GameState state;
    private BufferedImage[][] images;
    private boolean mouseOver, mousePressed;
    private Rectangle bounds;

    public NumButtons (int xPos, int yPos, int whichButton, GameState state) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.whichButton = whichButton;
        this.state = state;

        loadImages();
        initBounds();
    }

    private void loadImages () {
        images = new BufferedImage[8][3];
        BufferedImage tmp = LoadSave.GetImage(LoadSave.NUMP_BUTTONS);

        for (int i=0 ; i<images.length ; i++)
            for (int j=0 ; j<images[i].length ; j++)
                images[i][j] = tmp.getSubimage(j*NPB_WIDTH_DEFAULT, whichButton*NPB_HEIGHT_DEFAULT, NPB_WIDTH_DEFAULT, NPB_HEIGHT_DEFAULT);
    }

    private void initBounds () {
        bounds = new Rectangle(xPos, yPos, NPB_WIDTH, NPB_HEIGHT);
    }

    public void update () {
        index = 0;
        if (mouseOver) index = 1;
        if (mousePressed) index = 2;
    }

    public void draw (Graphics g) {
        g.drawImage(images[whichButton][index], xPos, yPos, NPB_WIDTH, NPB_HEIGHT, null);
    }

    // Getters and Setters
    public boolean isMouseOver () {
        return mouseOver;
    }
    public void setMouseOver (boolean mouseOver) {
        this.mouseOver = mouseOver;
    }
    public boolean isMousePressed () {
        return mousePressed;
    }
    public void setMousePressed (boolean mousePressed) {
        this.mousePressed = mousePressed;
    }
    public Rectangle getBounds () {
        return bounds;
    }
    public int getWhichButton () {
        return whichButton;
    }

    public void applyGamestate () {
        GameState.state = state;
    }

    public void resetBools () {
        mouseOver = false;
        mousePressed = false;
    }
}
