package ui;

import static utilz.Constants.GameConstants.CELL_SIZE;
import static utilz.Constants.UI.SigilConstants.*;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import utilz.LoadSave;

public class Sigils {
    private int whichPlayer, whichSigil;
    private BufferedImage[] images;

    public Sigils (int whichPlayer, int whichSigil) {
        this.whichPlayer = whichPlayer;
        this.whichSigil = whichSigil;

        loadImages();
    }

    private void loadImages () {
        images = new BufferedImage[3];
        BufferedImage tmp = LoadSave.GetImage(LoadSave.SIGILS);

        for (int i=0 ; i<3 ; i++)
            images[i] = tmp.getSubimage(i*SIGIL_WIDTH_DEFAULT, whichSigil*SIGIL_HEIGHT_DEFAULT, SIGIL_WIDTH_DEFAULT, SIGIL_HEIGHT_DEFAULT);
    }

    public void update () {

    }

    public void draw (Graphics g, int count, int i, int j) {
        g.drawImage( images[--count],
                    (int) (CELL_SIZE*i + SIGIL_X_POS),
                    (int) (CELL_SIZE*j + SIGIL_Y_POS),
                    SIGIL_SIZE, SIGIL_SIZE, null
        );
    }

}
