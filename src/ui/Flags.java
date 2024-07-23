package ui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import gamestates.GameState;
import player.GameData;
import player.Player;
import utilz.LoadSave;

import static utilz.Constants.UI.FlagConstants.*;

public class Flags {
    private int xPos, yPos, whichFlag, whichPlayer;
    private BufferedImage[] images;
    private boolean move, dead;
    
    public Flags (int whichPlayer, int whichFlag) {
        this.whichPlayer = whichPlayer;
        this.whichFlag = whichFlag;
        this.xPos = FLAG_X_POS[whichFlag];
        this.yPos = FLAG_Y_POS[whichFlag];

        move = whichPlayer == 0;
        dead = false;

        loadImages();
    }

    private void loadImages () {
        images = new BufferedImage[3];
        BufferedImage tmp = LoadSave.GetImage(LoadSave.FLAGS);

        for (int i=0 ; i<3 ; i++)
            images[i] = tmp.getSubimage(i*FLAG_WIDTH_DEFAULT, whichFlag*FLAG_HEIGHT_DEFAULT, FLAG_WIDTH_DEFAULT, FLAG_HEIGHT_DEFAULT);
    }

    public void update () {
        dead = GameData.players[whichPlayer].getTotalSigils() == 0 && GameData.round > GameData.numPlayers;
        move = GameData.currPlayer == whichPlayer;
    }

    public void draw (Graphics g) {
        g.drawImage( images[ dead ? 2 : move ? 1 : 0 ],
                        xPos, yPos,
                        FLAG_WIDTH, FLAG_HEIGHT, null
        );
    }

    public void applyGamestate () {
        GameData.winner = whichFlag;
        GameState.state = GameState.GAMEOVER;
    }
}
