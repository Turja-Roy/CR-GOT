package utilz;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class LoadSave {
    public static final String SIGIL_TARGARYEN = "Targaryen.png";
    public static final String SIGIL_STARK = "Stark.png";
    public static final String SIGIL_LANNISTER = "Lannister.png";
    public static final String SIGIL_BARATHEON = "Baratheon.png";
    public static final String SIGIL_TYRELL = "Tyrell.png";
    public static final String SIGIL_ARRYN = "Arryn.png";
    public static final String SIGIL_GREYJOY = "Greyjoy.png";
    public static final String SIGIL_MARTELL = "Martell.png";
    public static final String SIGIL_TULLY = "Tully.png";
    public static final String SIGIL_BOLTON = "Bolton.png";

    public static final String FLAG_TARGARYEN = "Targaryen2.png";
    public static final String FLAG_STARK = "Stark2.png";
    public static final String FLAG_LANNISTER = "Lannister2.png";
    public static final String FLAG_BARATHEON = "Baratheon2.png";
    public static final String FLAG_TYRELL = "Tyrell2.png";
    public static final String FLAG_ARRYN = "Arryn2.png";
    public static final String FLAG_GREYJOY = "Greyjoy2.png";
    public static final String FLAG_MARTELL = "Martell2.png";
    public static final String FLAG_TULLY = "Tully2.png";
    public static final String FLAG_BOLTON = "Bolton2.png";

    public static final String MENU_BUTTONS = "menuButtons.png";
    public static final String NUMP_BUTTONS = "numButtons.png";
    public static final String PLAYER_SELECTION_BUTTONS = "playerSelectionButtons.png";
    public static final String HOUSE_SELECTION_BUTTONS = "houseSelectionButtons.png";
    public static final String MAP_IMAGE = "map.jpg";

    public static final String INTRO_PAGE = "intropage.png";
    public static final String SCROLL = "Rules_Scroll.png";

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
