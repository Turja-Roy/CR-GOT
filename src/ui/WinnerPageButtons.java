package ui;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import gamestates.GameState;
import player.GameData;
import utilz.LoadSave;

import static utilz.Constants.UI.MouseStates.*;
import static utilz.Constants.UI.WinnerPageButtons.*;

public class WinnerPageButtons {
    private int xPos, yPos, whichButton, mouseState;
    private GameState state;
    private BufferedImage[] images;
    private boolean mouseOver, mousePressed;
    private Rectangle bounds;

    public WinnerPageButtons (int xPos, int yPos, int whichButton, GameState state) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.whichButton = whichButton;
        this.state = state;

        loadImages();
        bounds = new Rectangle(xPos, yPos, WPB_WIDTH, WPB_HEIGHT);
    }

    private void loadImages () {
        images = new BufferedImage[3];
        BufferedImage tmp = LoadSave.GetImage(LoadSave.WINNER_PAGE_BUTTONS);

        for (int i=0 ; i<3 ; i++)
            images[i] = tmp.getSubimage(i*WPB_WIDTH_DEFAULT, whichButton*WPB_HEIGHT_DEFAULT, WPB_WIDTH_DEFAULT, WPB_HEIGHT_DEFAULT);
    }

    public void update () {
        mouseState = NORMAL;
        if (mouseOver) mouseState = HOVER;
        if (mousePressed) mouseState = PRESSED;
    }

    public void draw (Graphics g) {
        g.drawImage(images[mouseState], xPos, yPos, WPB_WIDTH, WPB_HEIGHT, null);
    }

    // Operational methods
    public void applyGamestate () {
        if (state == GameState.MENU) {
            GameData.resetData();
            GameState.state = state;
        }
        else {
            GameState.state = state;
        }
    }

    public void resetBools () {
        mouseOver = mousePressed = false;
    }

    // Getters
    public boolean isMouseOver () {
        return mouseOver;
    }
    public boolean isMousePressed () {
        return mousePressed;
    }
    public Rectangle getBounds () {
        return bounds;
    }

    // Setters
    public void setMouseOver (boolean mouseOver) {
        this.mouseOver = mouseOver;
    }
    public void setMousePressed (boolean mousePressed) {
        this.mousePressed = mousePressed;
    }
}
