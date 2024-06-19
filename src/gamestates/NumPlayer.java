package gamestates;

import static utilz.Constants.GameConstants.*;
import static utilz.Constants.UI.NumPlayerButtons.*;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import main.Game;
import ui.NumButtons;
import utilz.LoadSave;

public class NumPlayer extends State implements Statemethods {
    private NumButtons[] buttons = new NumButtons[8];
    private BufferedImage bgImage;

    public NumPlayer (Game game) {
        super(game);
        loadButtons();
        bgImage = LoadSave.GetImage(LoadSave.INTRO_PAGE);
    }

    private void loadButtons () {
        buttons[0] = new NumButtons(GAME_WIDTH*2/12, GAME_HEIGHT*2/6 - CELL_SIZE, NPB_2P, GameState.PLAYING);
        buttons[1] = new NumButtons(GAME_WIDTH*8/12, GAME_HEIGHT*2/6 - CELL_SIZE, NPB_3P, GameState.PLAYING);
        buttons[2] = new NumButtons(GAME_WIDTH*2/12, GAME_HEIGHT*3/6 - CELL_SIZE, NPB_4P, GameState.PLAYING);
        buttons[3] = new NumButtons(GAME_WIDTH*8/12, GAME_HEIGHT*3/6 - CELL_SIZE, NPB_5P, GameState.PLAYING);
        buttons[4] = new NumButtons(GAME_WIDTH*2/12, GAME_HEIGHT*4/6 - CELL_SIZE, NPB_6P, GameState.PLAYING);
        buttons[5] = new NumButtons(GAME_WIDTH*8/12, GAME_HEIGHT*4/6 - CELL_SIZE, NPB_7P, GameState.PLAYING);
        buttons[6] = new NumButtons(GAME_WIDTH*2/12, GAME_HEIGHT*5/6 - CELL_SIZE, NPB_BACK, GameState.MENU);
        buttons[7] = new NumButtons(GAME_WIDTH*8/12, GAME_HEIGHT*5/6 - CELL_SIZE, NPB_QUIT, GameState.QUIT);
    }

    @Override
    public void update() {
        for (NumButtons button : buttons)
            button.update();
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(bgImage, 0, 0, GAME_WIDTH, GAME_HEIGHT, null);
        for (NumButtons button : buttons)
            button.draw(g);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        //
    }

    @Override
    public void mousePressed(MouseEvent e) {
        for (NumButtons button : buttons)
            button.setMousePressed(isInside(e, button));
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        for (NumButtons button : buttons) {
            if (isInside(e, button) && button.isMousePressed()) {
                game.getPlaying().setNumPlayers(button.getNumPlayers());
                button.applyGamestate();
                break;
            }
        }
        resetButtons();
    }

    private void resetButtons() {
        for (NumButtons button : buttons)
            button.setMousePressed(false);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        for (NumButtons button : buttons)
            button.setMouseOver(isInside(e, button));
    }
}
