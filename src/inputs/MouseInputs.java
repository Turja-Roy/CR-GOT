package inputs;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import gamestates.GameState;
import main.GamePanel;

public class MouseInputs implements MouseListener, MouseMotionListener {
    private GamePanel gamePanel;

    public MouseInputs (GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        switch (GameState.state) {
            case MENU:
                gamePanel.getGame().getMenu().mouseClicked(e);
                break;
            case RULES:
                gamePanel.getGame().getRules().mouseClicked(e);
                break;
            case NUMPLAYER:
                gamePanel.getGame().getNumPlayer().mouseClicked(e);
                break;
            case HOUSE_SELECTION:
                gamePanel.getGame().getHouseSelection().mouseClicked(e);
                break;
            case PLAYING:
                gamePanel.getGame().getPlaying().mouseClicked(e);
                break;
            case GAMEOVER:
                gamePanel.getGame().getGameOver().mouseClicked(e);
                break;
            default:
                break;
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        switch (GameState.state) {
            case MENU:
                gamePanel.getGame().getMenu().mousePressed(e);
                break;
            case RULES:
                gamePanel.getGame().getRules().mousePressed(e);
                break;
            case NUMPLAYER:
                gamePanel.getGame().getNumPlayer().mousePressed(e);
                break;
            case HOUSE_SELECTION:
                gamePanel.getGame().getHouseSelection().mousePressed(e);
                break;
            case PLAYING:
                gamePanel.getGame().getPlaying().mousePressed(e);
                break;
            case GAMEOVER:
                gamePanel.getGame().getGameOver().mousePressed(e);
                break;
            default:
                break;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        switch (GameState.state) {
            case MENU:
                gamePanel.getGame().getMenu().mouseReleased(e);
                break;
            case RULES:
                gamePanel.getGame().getRules().mouseReleased(e);
                break;
            case NUMPLAYER:
                gamePanel.getGame().getNumPlayer().mouseReleased(e);
                break;
            case HOUSE_SELECTION:
                gamePanel.getGame().getHouseSelection().mouseReleased(e);
                break;
            case PLAYING:
                gamePanel.getGame().getPlaying().mouseReleased(e);
                break;
            case GAMEOVER:
                gamePanel.getGame().getGameOver().mouseReleased(e);
                break;
            default:
                break;
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // System.out.println("Mouse Entered");
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // System.out.println("Mouse Exited");
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        // TODO: Implement mouseDragged
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        switch (GameState.state) {
            case MENU:
                gamePanel.getGame().getMenu().mouseMoved(e);
                break;
            case RULES:
                gamePanel.getGame().getRules().mouseMoved(e);
                break;
            case NUMPLAYER:
                gamePanel.getGame().getNumPlayer().mouseMoved(e);
                break;
            case HOUSE_SELECTION:
                gamePanel.getGame().getHouseSelection().mouseMoved(e);
                break;
            case PLAYING:
                gamePanel.getGame().getPlaying().mouseMoved(e);
                break;
            case GAMEOVER:
                gamePanel.getGame().getGameOver().mouseMoved(e);
                break;
            default:
                break;
        }
    }
}
