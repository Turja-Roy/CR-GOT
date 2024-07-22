package ui;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import gamestates.GameState;
import main.Game;
import player.GameData;
import player.Player;
import utilz.LoadSave;

import static utilz.Constants.UI.HousePlayerSelectionButtons.*;
import static utilz.Constants.UI.MouseStates.*;

public class HousePlayerButtons {
    private int xPos, yPos, whichButton, mouseState;
    private GameState state;
    private BufferedImage[][] playerImages, houseImages;
    private boolean mouseOver, mousePressed, mouseClicked;
    private Rectangle bounds;

    public HousePlayerButtons (int xPos, int yPos, int whichButton, GameState state) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.whichButton = whichButton;
        this.state = state;

        if (whichButton <= PSB_START)
            loadPlayerButtonImages();
        else
            loadHouseButtonImages();
        initBounds(whichButton);
    }

    private void loadPlayerButtonImages () {
        playerImages = new BufferedImage[10][3];
        BufferedImage tmp = LoadSave.GetImage(LoadSave.PLAYER_SELECTION_BUTTONS);

        for (int i=0 ; i<playerImages.length ; i++)
            for (int j=0 ; j<playerImages[i].length ; j++)
                playerImages[i][j] = tmp.getSubimage(j*PSB_WIDTH_DEFAULT, whichButton*PSB_HEIGHT_DEFAULT, PSB_WIDTH_DEFAULT, PSB_HEIGHT_DEFAULT);
    }

    private void loadHouseButtonImages () {
        houseImages = new BufferedImage[10][3];
        BufferedImage tmp = LoadSave.GetImage(LoadSave.HOUSE_SELECTION_BUTTONS);

        for (int i=0 ; i<houseImages.length ; i++)
            for (int j=0 ; j<houseImages[i].length ; j++)
                houseImages[i][j] = tmp.getSubimage(j*HSB_WIDTH_DEFAULT, (whichButton-PSB_START-1)*HSB_HEIGHT_DEFAULT, HSB_WIDTH_DEFAULT, HSB_HEIGHT_DEFAULT);
    }

    private void initBounds (int whichButton) {
        if (whichButton <= PSB_START)
            bounds = new Rectangle(xPos, yPos, PSB_WIDTH, PSB_HEIGHT);
        else
            bounds = new Rectangle(xPos, yPos, HSB_WIDTH, HSB_HEIGHT);
    }

    public void update () {
        mouseState = NORMAL;
        if (mouseOver) mouseState = HOVER;
        if (mousePressed) mouseState = PRESSED;
        if (mouseClicked) mouseState = PRESSED;
    }

    public void draw (Graphics g) {
        if (whichButton <= PSB_START)
            g.drawImage(playerImages[whichButton][mouseState], xPos, yPos, PSB_WIDTH, PSB_HEIGHT, null);
        else
            g.drawImage(houseImages[whichButton-PSB_START-1][mouseState], xPos, yPos, HSB_WIDTH, HSB_HEIGHT, null);
    }

    // Operational methods
    public boolean applyGamestate (Game game) {
        boolean pass = true;

        if (state == GameState.NUMPLAYER || state == GameState.QUIT)
            GameState.state = state;

        else if (state == GameState.PLAYING) {
            Player[] players = GameData.players;
            // Player[] players = game.getPlaying().getPlayers();
            for (Player player : players)
                if (player.getHouse() == -1)
                    pass = false;

            if (pass) {
                game.initPlaying();
                GameState.state = state;
            }
        } 

        return pass;
    }

    public void resetBools () {
        mouseOver = mousePressed = mouseClicked = false;
    }

    // Getters
    public boolean isMouseOver () {
        return mouseOver;
    }
    public boolean isMousePressed () {
        return mousePressed;
    }
    public boolean isMouseClicked () {
        return mouseClicked;
    }
    public Rectangle getBounds () {
        return bounds;
    }
    public int getWhichButton () {
        return whichButton;
    }

    // Setters
    public void setMouseOver (boolean mouseOver) {
        this.mouseOver = mouseOver;
    }
    public void setMousePressed (boolean mousePressed) {
        this.mousePressed = mousePressed;
    }
    public void setMouseClicked (boolean mouseClicked) {
        this.mouseClicked = mouseClicked;
    }
}
