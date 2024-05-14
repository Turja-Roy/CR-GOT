package main.visuals;

import javax.swing.JFrame;

public class GameWindow extends JFrame {

    public GameWindow (GamePanel gamePanel) {
        setTitle("Game Title");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        add(gamePanel);
        setLocationRelativeTo(null);
        setResizable(false);
        pack();

        setVisible(true);
    }

}
