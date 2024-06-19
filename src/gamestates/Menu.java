package gamestates;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import main.Game;
import ui.MenuButtons;
import utilz.LoadSave;

import static utilz.Constants.GameConstants.*;

public class Menu extends State implements Statemethods {
    private MenuButtons[] buttons = new MenuButtons[3];
    private BufferedImage bgImage;
    
    public Menu (Game game) {
        super(game);
        loadButtons();
        bgImage = LoadSave.GetImage(LoadSave.INTRO_PAGE);
    }

    private void loadButtons () {
        buttons[0] = new MenuButtons(GAME_WIDTH*2/12, GAME_HEIGHT/2 + CELL_SIZE*2, 0, GameState.NUMPLAYER);
        buttons[1] = new MenuButtons(GAME_WIDTH*5/12, GAME_HEIGHT/2 + CELL_SIZE*2, 1, GameState.RULES);
        buttons[2] = new MenuButtons(GAME_WIDTH*8/12, GAME_HEIGHT/2 + CELL_SIZE*2, 2, GameState.QUIT);
    }

    @Override
    public void update () {
        for (MenuButtons button : buttons)
            button.update();
    }

    @Override
    public void draw (Graphics g) {
        g.drawImage(bgImage, 0, 0, GAME_WIDTH, GAME_HEIGHT, null);
        for (MenuButtons button : buttons)
            button.draw(g);
    }

    @Override
    public void mouseClicked (MouseEvent e) {
        // TODO: MouseClicked event
    }

    @Override
    public void mousePressed (MouseEvent e) {
        for (MenuButtons button : buttons)
            button.setMousePressed(isInside(e, button));
    }

    @Override
    public void mouseReleased (MouseEvent e) {
        for (MenuButtons button : buttons) {
            if (isInside(e, button) && button.isMousePressed()) {
                button.applyGamestate();
                break;
            }
        }
        resetButtons();
    }

    private void resetButtons () {
        for (MenuButtons button : buttons)
            button.resetBools();
    }

    @Override
    public void mouseMoved (MouseEvent e) {
        for (MenuButtons button : buttons)
            button.setMouseOver(isInside(e, button));
    }

}
