package utilz;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class LoadSave {
    public static final String INTRO_PAGE = "intropage.png";

    public static final String MENU_BUTTONS = "menuButtons.png";

    public static final String SCROLL = "Rules_Scroll.png";

    public static final String NUMP_BUTTONS = "numButtons.png";

    public static final String PLAYER_SELECTION_BUTTONS = "playerSelectionButtons.png";
    public static final String HOUSE_SELECTION_BUTTONS = "houseSelectionButtons.png";
    public static final String MAP_IMAGE = "map.jpg";

    public static final String FLAGS = "Flags.png";
    public static final String SIGILS = "Sigils.png";

    public static final String GAME_OVER = "winnerpage.jpg";
    public static final String WINNER_PAGE_BUTTONS = "winnerPageButtons.png";

    public static BufferedImage GetImage (String fileName) {
        BufferedImage img = null;
        InputStream is = LoadSave.class.getResourceAsStream("/images/" + fileName);

        try {
            img = ImageIO.read(is);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return img;
    }
}
