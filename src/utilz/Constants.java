package utilz;

import java.awt.Color;

import main.technicals.Player;

public class Constants {
    
    // House constants
    public static class House {
        public static final int TARGARYEN = 1;
        public static final int STARK = 2;
        public static final int LANNISTER = 3;
        public static final int BARATHEON = 4;
        public static final int TYRELL = 5;
        public static final int ARRYN = 6;
        public static final int GREYJOY = 7;
        public static final int MARTELL = 8;
        public static final int TULLY = 9;
        public static final int BOLTON = 10;
    }

    // Color constants
    public static class Colors {
        public static final Color COLOR_BLACK = new Color(0, 0, 0);
        public static final Color COLOR_TARGARYEN = new Color(255, 0, 0);
        public static final Color COLOR_STARK = new Color(128, 128, 128);
        public static final Color COLOR_LANNISTER = new Color(255, 215, 0);
        public static final Color COLOR_BARATHEON = new Color(255, 255, 0);
        public static final Color COLOR_TYRELL = new Color(0, 255, 0);
        public static final Color COLOR_ARRYN = new Color(0, 255, 255);
        public static final Color COLOR_GREYJOY = new Color(20, 75, 110);
        public static final Color COLOR_MARTELL = new Color(255, 128, 0);
        public static final Color COLOR_TULLY = new Color(0, 0, 120);
        public static final Color COLOR_BOLTON = new Color(83, 0, 14);

        public static Color getColor (Player player) {
            switch (player.house) {
                case House.TARGARYEN:
                    return COLOR_TARGARYEN;
                case House.STARK:
                    return COLOR_STARK;
                case House.LANNISTER:
                    return COLOR_LANNISTER;
                case House.BARATHEON:
                    return COLOR_BARATHEON;
                case House.TYRELL:
                    return COLOR_TYRELL;
                case House.ARRYN:
                    return COLOR_ARRYN;
                case House.GREYJOY:
                    return COLOR_GREYJOY;
                case House.MARTELL:
                    return COLOR_MARTELL;
                case House.TULLY:
                    return COLOR_TULLY;
                case House.BOLTON:
                    return COLOR_BOLTON;
                default:
                    return COLOR_BLACK;
            }
        }
    }

    // Sigil constants
    public static class Sigils {
        public static final String SIGIL_TARGARYEN = "/images/Targaryen.png";
        public static final String SIGIL_STARK = "/images/Stark.png";
        public static final String SIGIL_LANNISTER = "/images/Lannister.png";
        public static final String SIGIL_BARATHEON = "/images/Baratheon.png";
        public static final String SIGIL_TYRELL = "/images/Tyrell.png";
        public static final String SIGIL_ARRYN = "/images/Arryn.png";
        public static final String SIGIL_GREYJOY = "/images/Greyjoy.png";
        public static final String SIGIL_MARTELL = "/images/Martell.png";
        public static final String SIGIL_TULLY = "/images/Tully.png";
        public static final String SIGIL_BOLTON = "/images/Bolton.png";

        public static String getSigil (Player player) {
            switch (player.house) {
                case House.TARGARYEN:
                    return SIGIL_TARGARYEN;
                case House.STARK:
                    return SIGIL_STARK;
                case House.LANNISTER:
                    return SIGIL_LANNISTER;
                case House.BARATHEON:
                    return SIGIL_BARATHEON;
                case House.TYRELL:
                    return SIGIL_TYRELL;
                case House.ARRYN:
                    return SIGIL_ARRYN;
                case House.GREYJOY:
                    return SIGIL_GREYJOY;
                case House.MARTELL:
                    return SIGIL_MARTELL;
                case House.TULLY:
                    return SIGIL_TULLY;
                case House.BOLTON:
                    return SIGIL_BOLTON;
                default:
                    return null;
            }
        }
    }
}
