package main;

import java.awt.event.WindowFocusListener;
import javax.swing.JFrame;

public class GameWindow extends JFrame {

    public GameWindow (GamePanel gamePanel) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // setTitle("Game Title");

        add(gamePanel);
        setLocationRelativeTo(null);
        setResizable(false);
        pack();
        setVisible(true);

        addWindowFocusListener(new WindowFocusListener() {
            @Override
            public void windowGainedFocus(java.awt.event.WindowEvent e) {
                gamePanel.requestFocus();
            }

            @Override
            public void windowLostFocus(java.awt.event.WindowEvent e) {
                gamePanel.getGame().windowFocusLost();
            }
        });
    }

}
