package gamestates;

import static utilz.Constants.GameConstants.*;
import static utilz.Constants.UI.NumPlayerButtons.NPB_BACK;
import static utilz.Constants.RulesConstants.*;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import ui.NumButtons;
import utilz.LoadSave;

public class Rules implements Statemethods {
    private NumButtons backButton;
    private BufferedImage bgImage, scrollImage;

    public Rules () {
        backButton = new NumButtons(GAME_WIDTH/2 - CELL_SIZE, GAME_HEIGHT*7/8, NPB_BACK, GameState.MENU);
        bgImage = LoadSave.GetImage(LoadSave.INTRO_PAGE);
        scrollImage = LoadSave.GetImage(LoadSave.SCROLL);
    }

    @Override
    public void update() {
        backButton.update();
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(bgImage, 0, 0, GAME_WIDTH, GAME_HEIGHT, null);
        g.drawImage(scrollImage, 0, 0, RULES_WIDTH, RULES_HEIGHT, null);
        backButton.draw(g);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // 
    }

    @Override
    public void mousePressed(MouseEvent e) {
        backButton.setMousePressed(isInside(e, backButton));
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (isInside(e, backButton) && backButton.isMousePressed())
            backButton.applyGamestate();
        backButton.resetBools();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        backButton.setMouseOver(isInside(e, backButton));
    }

    private boolean isInside (MouseEvent e, NumButtons button) {
        return button.getBounds().contains(e.getX(), e.getY());
    }

}

