package ui;

import static utilz.Constants.GameConstants.CELL_SIZE;
import static utilz.Constants.GameConstants.SCALE;
import static utilz.Constants.UI.SigilConstants.*;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import main.Game;
import player.Cell;
import player.Explodables;
import static player.GameData.rotation;
import utilz.LoadSave;

public class Sigils {
    private int whichSigil;
    private BufferedImage[] images;

    public Sigils (int whichSigil) {
        this.whichSigil = whichSigil;

        loadImages();
    }

    private void loadImages () {
        images = new BufferedImage[4];
        BufferedImage tmp = LoadSave.GetImage(LoadSave.SIGILS);

        for (int i=0 ; i<4 ; i++)
            images[i] = tmp.getSubimage(i*SIGIL_WIDTH_DEFAULT, whichSigil*SIGIL_HEIGHT_DEFAULT, SIGIL_WIDTH_DEFAULT, SIGIL_HEIGHT_DEFAULT);
    }

    public void update () {

    }

    public void draw (Graphics g, Cell cell, int row, int col) {
        Graphics2D g2d = (Graphics2D) g;
        if ( cell.isExplodable(true) )
            g2d.rotate( Math.toRadians(rotation), CELL_SIZE*col + SIGIL_X_POS + SIGIL_SIZE/2, CELL_SIZE*row + SIGIL_Y_POS + SIGIL_SIZE/2 );

        g2d.drawImage( images[cell.getSigilCount()-1],
                    (int) (CELL_SIZE*col + SIGIL_X_POS),
                    (int) (CELL_SIZE*row + SIGIL_Y_POS),
                    SIGIL_SIZE, SIGIL_SIZE, null
        );

        if ( cell.isExplodable(true) )
            g2d.rotate( Math.toRadians(-rotation), CELL_SIZE*col + SIGIL_X_POS + SIGIL_SIZE/2, CELL_SIZE*row + SIGIL_Y_POS + SIGIL_SIZE/2 );
    }

    public void animateExplodable (Graphics g, Explodables ex, Game game) {
        int j=ex.rowIndex, i=ex.colIndex;
        int scale = 10;
        int initial_i = CELL_SIZE*i + SIGIL_X_POS;
        int initial_j = CELL_SIZE*j + SIGIL_Y_POS;
        float i_pos_plus = (float) initial_i;
        float j_pos_plus = (float) initial_j;
        float i_pos_minus = (float) initial_i;
        float j_pos_minus = (float) initial_j;

        while (i_pos_plus <= initial_i+CELL_SIZE) {
            g.drawImage( images[0],
                        (int) (i_pos_plus),
                        (int) (initial_j),
                        SIGIL_SIZE, SIGIL_SIZE, null
            );
            g.drawImage( images[0],
                        (int) (i_pos_minus),
                        (int) (initial_j),
                        SIGIL_SIZE, SIGIL_SIZE, null
            );
            g.drawImage( images[0],
                        (int) (initial_i),
                        (int) (j_pos_plus),
                        SIGIL_SIZE, SIGIL_SIZE, null
            );
            g.drawImage( images[0],
                        (int) (initial_i),
                        (int) (j_pos_minus),
                        SIGIL_SIZE, SIGIL_SIZE, null
            );

            i_pos_plus += scale*SCALE;
            j_pos_plus += scale*SCALE;
            i_pos_minus -= scale*SCALE;
            j_pos_minus -= scale*SCALE;
        }
    }

}
