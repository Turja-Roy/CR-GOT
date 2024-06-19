package ui;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import gamestates.GameState;
import utilz.LoadSave;

import static utilz.Constants.UI.NumPlayerButtons.*;
import static utilz.Constants.UI.MouseStates.*;

public class NumButtons {
    private int xPos, yPos, whichButton, mouseState;
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
        mouseState = NORMAL;
        if (mouseOver) mouseState = HOVER;
        if (mousePressed) mouseState = PRESSED;
    }

    public void draw (Graphics g) {
        g.drawImage(images[whichButton][mouseState], xPos, yPos, NPB_WIDTH, NPB_HEIGHT, null);
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

    public int getNumPlayers () {
        switch (whichButton) {
            case NPB_2P:
                return 2;
            case NPB_3P:
                return 3;
            case NPB_4P:
                return 4;
            case NPB_5P:
                return 5;
            case NPB_6P:
                return 6;
            case NPB_7P:
                return 7;
            default:
                return 0;
        }
    }

    public void applyGamestate () {
        GameState.state = state;
    }

    public void resetBools () {
        mouseOver = false;
        mousePressed = false;
    }
}
