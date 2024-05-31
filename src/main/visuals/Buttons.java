package main.visuals;

import java.awt.event.*;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class Buttons implements MouseListener {

    public static class startButton extends Buttons {
        private InputStream is = getClass().getResourceAsStream("button.png");
        private BufferedImage startButton;
        try {
            startButton = ImageIO.read(is);
        } catch (Exception e) {
            e.printStackTrace();
        }

        @Override
        public void mouseClicked (MouseEvent e) {
            // Start Game
        }
    }

    public void mouseClicked(MouseEvent e) {
        // Do nothing
    }
    public void mousePressed(MouseEvent e) {
        // Do nothing
    }
    public void mouseReleased(MouseEvent e) {
        // Do nothing
    }
    public void mouseEntered(MouseEvent e) {
        // Do nothing
    }
    public void mouseExited(MouseEvent e) {
        // Do nothing
    }
}
