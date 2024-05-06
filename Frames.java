public class Frames {
    public class Button extends Frame implements MouseListener {
        public Button(String imagePath) {
            super(imagePath);
            addMouseListener(this);
        }

        public void mouseClicked(MouseEvent e) {
            System.out.println("Button.mouseClicked");
        }

        public void mouseEntered(MouseEvent e) {
            System.out.println("Button.mouseEntered");
        }

        public void mouseExited(MouseEvent e) {
            System.out.println("Button.mouseExited");
        }

        public void mousePressed(MouseEvent e) {
            System.out.println("Button.mousePressed");
        }

        public void mouseReleased(MouseEvent e) {
            System.out.println("Button.mouseReleased");
        }
    }
}
