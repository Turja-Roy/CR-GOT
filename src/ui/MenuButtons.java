package ui;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import gamestates.GameState;
import utilz.LoadSave;

import static utilz.Constants.UI.MenuButtons.*;
import static utilz.Constants.UI.MouseStates.*;

public class MenuButtons {
    private int xPos, yPos, whichButton, mouseState;
    private GameState state;
    private BufferedImage[][] images;
    private boolean mouseOver, mousePressed;
    private Rectangle bounds;

    public MenuButtons (int xPos, int yPos, int whichButton, GameState state) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.whichButton = whichButton;
        this.state = state;

        loadImages();
        initBounds();
    }

    private void loadImages () {
        images = new BufferedImage[3][3];
        BufferedImage tmp = LoadSave.GetImage(LoadSave.MENU_BUTTONS);

        for (int i=0 ; i<images.length ; i++)
            for (int j=0 ; j<images[i].length ; j++)
                images[i][j] = tmp.getSubimage(j*MB_WIDTH_DEFAULT, whichButton*MB_HEIGHT_DEFAULT, MB_WIDTH_DEFAULT, MB_HEIGHT_DEFAULT);
    }

    private void initBounds () {
        bounds = new Rectangle(xPos, yPos, MB_WIDTH, MB_HEIGHT);
    }

    public void update () {
        mouseState = NORMAL;
        if (mouseOver) mouseState = HOVER;
        if (mousePressed) mouseState = PRESSED;
    }

    public void draw (Graphics g) {
        g.drawImage(images[whichButton][mouseState], xPos, yPos, MB_WIDTH, MB_HEIGHT, null);
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

    public void applyGamestate () {
        GameState.state = state;
    }

    public void resetBools () {
        mouseOver = false;
        mousePressed = false;
    }
}
