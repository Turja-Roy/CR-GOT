package gamestates;

import static utilz.Constants.GameConstants.*;
import static utilz.Constants.UI.HousePlayerSelectionButtons.*;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import main.Game;
import player.GameData;
import ui.HousePlayerButtons;
import utilz.LoadSave;

public class HouseSelection extends State implements Statemethods {
    private HousePlayerButtons[] playerButtons = new HousePlayerButtons[10];
    private HousePlayerButtons[] houseButtons = new HousePlayerButtons[10];
    private BufferedImage bgImage;
    private boolean playerButtonClicked=false, houseButtonClicked=false;
    private int playerButtonOnHold=-1, houseButtonOnHold=-1;

    private static boolean firstClick = true;
    
    public HouseSelection (Game game) {
        super(game);
        loadPlayerButtons();
        loadHouseButtons();
        bgImage = LoadSave.GetImage(LoadSave.INTRO_PAGE);
    }

    private void loadPlayerButtons () {
        playerButtons[0] = new HousePlayerButtons(GAME_WIDTH*2/12, (int) (GAME_HEIGHT*2.0/12), PSB_P1, null);
        playerButtons[1] = new HousePlayerButtons(GAME_WIDTH*4/12, (int) (GAME_HEIGHT*2.0/12), PSB_P2, null);
        playerButtons[2] = new HousePlayerButtons(GAME_WIDTH*2/12, (int) (GAME_HEIGHT*3.5/12), PSB_P3, null);
        playerButtons[3] = new HousePlayerButtons(GAME_WIDTH*4/12, (int) (GAME_HEIGHT*3.5/12), PSB_P4, null);
        playerButtons[4] = new HousePlayerButtons(GAME_WIDTH*2/12, (int) (GAME_HEIGHT*5.0/12), PSB_P5, null);
        playerButtons[5] = new HousePlayerButtons(GAME_WIDTH*4/12, (int) (GAME_HEIGHT*5.0/12), PSB_P6, null);
        playerButtons[6] = new HousePlayerButtons(GAME_WIDTH*3/12, (int) (GAME_HEIGHT*6.5/12), PSB_P7, null);
        playerButtons[7] = new HousePlayerButtons(GAME_WIDTH*2/12, (int) (GAME_HEIGHT*8.0/12), PSB_BACK, GameState.NUMPLAYER);
        playerButtons[8] = new HousePlayerButtons(GAME_WIDTH*4/12, (int) (GAME_HEIGHT*8.0/12), PSB_QUIT, GameState.QUIT);
        playerButtons[9] = new HousePlayerButtons(GAME_WIDTH*6/12, (int) (GAME_HEIGHT*5.0/12), PSB_START, GameState.PLAYING);
    }

    private void loadHouseButtons () {
        houseButtons[0] = new HousePlayerButtons(GAME_WIDTH*8/12, GAME_HEIGHT*1/12, HSB_TARGARYEN, null);
        houseButtons[1] = new HousePlayerButtons(GAME_WIDTH*8/12, GAME_HEIGHT*2/12, HSB_STARK, null);
        houseButtons[2] = new HousePlayerButtons(GAME_WIDTH*8/12, GAME_HEIGHT*3/12, HSB_LANNISTER, null);
        houseButtons[3] = new HousePlayerButtons(GAME_WIDTH*8/12, GAME_HEIGHT*4/12, HSB_BARATHEON, null);
        houseButtons[4] = new HousePlayerButtons(GAME_WIDTH*8/12, GAME_HEIGHT*5/12, HSB_TYRELL, null);
        houseButtons[5] = new HousePlayerButtons(GAME_WIDTH*8/12, GAME_HEIGHT*6/12, HSB_ARRYN, null);
        houseButtons[6] = new HousePlayerButtons(GAME_WIDTH*8/12, GAME_HEIGHT*7/12, HSB_GREYJOY, null);
        houseButtons[7] = new HousePlayerButtons(GAME_WIDTH*8/12, GAME_HEIGHT*8/12, HSB_MARTELL, null);
        houseButtons[8] = new HousePlayerButtons(GAME_WIDTH*8/12, GAME_HEIGHT*9/12, HSB_TULLY, null);
        houseButtons[9] = new HousePlayerButtons(GAME_WIDTH*8/12, GAME_HEIGHT*10/12, HSB_BOLTON, null);
    }

    @Override
    public void update () {
        int numPlayers = GameData.numPlayers;

        for (int i=0 ; i<numPlayers ; i++)
            playerButtons[i].update();
        for (int i=7 ; i<=9 ; i++)
            playerButtons[i].update();
        for (HousePlayerButtons button : houseButtons)
            button.update();
    }

    @Override
    public void draw (Graphics g) {
        int numPlayers = GameData.numPlayers;

        g.drawImage(bgImage, 0, 0, GAME_WIDTH, GAME_HEIGHT, null);

        g.setFont(new Font("Book Antiqua", Font.BOLD, 30));
        g.setColor(new Color(255, 255, 255));
        g.drawString("Select your house", (int) (GAME_WIDTH*2.5/12), GAME_HEIGHT*1/12);

        for (int i=0 ; i<numPlayers ; i++)
            playerButtons[i].draw(g);
        for (int i=7 ; i<=9 ; i++)
            playerButtons[i].draw(g);
        for (HousePlayerButtons button : houseButtons)
            button.draw(g);
    }

    @Override
    public void mouseClicked (MouseEvent e) {
        if (firstClick) {
            firstClick = false;
            return;
        }
        int numPlayers = GameData.numPlayers;

        for (int i=0 ; i<numPlayers ; i++) {
            HousePlayerButtons button = playerButtons[i];
            if (isInside(e, button)) {
                if (button.isMouseClicked()) continue;
                if (playerButtonOnHold != -1) {
                    button.setMouseClicked(true);
                    playerButtonClicked = true;
                    playerButtons[playerButtonOnHold].setMouseClicked(false);
                    playerButtonOnHold = button.getWhichButton();
                    continue;
                }
                if (!houseButtonClicked) {
                    button.setMouseClicked(true);
                    playerButtonClicked = true;
                    playerButtonOnHold = button.getWhichButton();
                }
                else {
                    button.setMouseClicked(true);
                    GameData.players[button.getWhichButton()].setHouse(houseButtonOnHold - PSB_START - 1);
                    // game.getPlaying().getPlayers()[button.getWhichButton()].setHouse(houseButtonOnHold - PSB_START - 1);
                    houseButtonClicked = playerButtonClicked = false;
                    playerButtonOnHold = houseButtonOnHold = -1;
                }
            }
        }

        for (int i=0 ; i<10 ; i++) {
            HousePlayerButtons button = houseButtons[i];
            if (isInside(e, button)) {
                if (button.isMouseClicked()) continue;
                if (houseButtonOnHold != -1) {
                    button.setMouseClicked(true);
                    houseButtonClicked = true;
                    houseButtons[houseButtonOnHold - PSB_START - 1].setMouseClicked(false);
                    houseButtonOnHold = button.getWhichButton();
                    continue;
                }
                if (!playerButtonClicked) {
                    button.setMouseClicked(true);
                    houseButtonClicked = true;
                    houseButtonOnHold = button.getWhichButton();
                }
                else {
                    button.setMouseClicked(true);
                    GameData.players[playerButtonOnHold].setHouse(button.getWhichButton() - PSB_START - 1);
                    // game.getPlaying().getPlayers()[playerButtonOnHold].setHouse(button.getWhichButton() - PSB_START - 1);
                    playerButtonClicked = houseButtonClicked = false;
                    playerButtonOnHold = houseButtonOnHold = -1;
                }
            }
        }
    }

    @Override
    public void mousePressed (MouseEvent e) {
        for (int i=7 ; i<=9 ; i++)
            playerButtons[i].setMousePressed(isInside(e, playerButtons[i]));
    }

    @Override
    public void mouseReleased (MouseEvent e) {
        boolean pass = false;
        for (int i=7 ; i<=9 ; i++) {
            if (isInside(e, playerButtons[i]) && playerButtons[i].isMousePressed()) {
                pass = playerButtons[i].applyGamestate(game);
                if (pass) {
                    firstClick = true;
                    resetButtons();
                    GameData.initHouses();
                }
                break;
            }
            playerButtons[i].resetBools();
        }
    }

    private void resetButtons () {
        for (HousePlayerButtons button : playerButtons)
            button.resetBools();
        for (HousePlayerButtons button : houseButtons)
            button.resetBools();
    }

    @Override
    public void mouseMoved (MouseEvent e) {
        for (HousePlayerButtons button : playerButtons)
            button.setMouseOver(isInside(e, button));
        for (HousePlayerButtons button : houseButtons)
            button.setMouseOver(isInside(e, button));
    }
}
