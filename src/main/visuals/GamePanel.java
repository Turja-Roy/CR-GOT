package main.visuals;

import javax.swing.JPanel;

import inputs.MouseInputs;

public class GamePanel extends JPanel {
    private MouseInputs mouseInputs;

    public GamePanel () {
        mouseInputs = new MouseInputs(this);

        setPanel();
        addMouseListener(mouseInputs);
    }

    private void setPanel () {
        Dimension size = new Dimension(1280, 720); // (720 + 560, 720)
        setPreferredSize(size);
        setMinimumSize(size);
        setMaximumSize(size);
    }

    public void paintComponent (Graphics g) {
        super.paintComponent(g);

        draw
    }
}
