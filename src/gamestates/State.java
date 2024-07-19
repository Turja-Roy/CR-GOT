package gamestates;

import static utilz.Constants.GameConstants.CELL_SIZE;

import java.awt.Rectangle;
import java.awt.event.MouseEvent;

import main.Game;
import ui.HousePlayerButtons;
import ui.MenuButtons;
import ui.NumButtons;

public class State {
    protected Game game;

    public State (Game game) {
        this.game = game;
    }

    public boolean isInside (MouseEvent e, MenuButtons button) {
        return button.getBounds().contains(e.getX(), e.getY());
    }

    public boolean isInside (MouseEvent e, NumButtons button) {
        return button.getBounds().contains(e.getX(), e.getY());
    }

    public boolean isInside (MouseEvent e, HousePlayerButtons button) {
        return button.getBounds().contains(e.getX(), e.getY());
    }

    public boolean insideBoard (MouseEvent e) {
        Rectangle bounds = new Rectangle(0, 0, CELL_SIZE*10, CELL_SIZE*10);
        return bounds.contains(e.getX(), e.getY());
    }

    public Game getGame () {
        return game;
    }
}
