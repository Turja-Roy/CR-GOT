package gamestates;

import java.awt.event.MouseEvent;

import main.Game;
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

    public Game getGame () {
        return game;
    }
}
