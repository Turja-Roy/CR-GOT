package utilz;

import static utilz.Constants.GameConstants.*;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;

import player.Coor;
import player.Player;

public class Constants {
    // Game constants
    public static class GameConstants {
        public static final float SCALE = 1.5f;

        public static final int CELL_SIZE_DEFAULT = 45;
        public static final int CELL_SIZE = (int) (CELL_SIZE_DEFAULT * SCALE);

        public static final int CELLS_IN_WIDTH = 18;
        public static final int CELLS_IN_HEIGHT = 10;

        public static final int GAME_WIDTH = CELL_SIZE * CELLS_IN_WIDTH;
        public static final int GAME_HEIGHT = CELL_SIZE * CELLS_IN_HEIGHT;
    }

    // Rules page constants
    public static class RulesConstants {
        public static final int RULES_PAGE_SCALE = (int) (GameConstants.SCALE * 0.8);
        public static final int RULES_WIDTH = GameConstants.GAME_WIDTH * RULES_PAGE_SCALE;
        public static final int RULES_HEIGHT = GameConstants.GAME_HEIGHT * RULES_PAGE_SCALE;
    }
    
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
        public static final Color COLOR_LANNISTER = new Color(255, 185, 0);
        public static final Color COLOR_BARATHEON = new Color(255, 255, 0);
        public static final Color COLOR_TYRELL = new Color(0, 255, 0);
        public static final Color COLOR_ARRYN = new Color(0, 255, 255);
        public static final Color COLOR_GREYJOY = new Color(20, 75, 110);
        public static final Color COLOR_MARTELL = new Color(255, 128, 0);
        public static final Color COLOR_TULLY = new Color(0, 0, 120);
        public static final Color COLOR_BOLTON = new Color(83, 0, 14);

        public static Color getColor (Player player) {
            switch (player.getHouse()) {
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
            switch (player.getHouse()) {
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

    // UI constants
    public static class UI {

        public static class MenuButtons {
            public static final float BUTTON_COMPRESSION_FACTOR = 0.25f;

            public static final int MB_PLAY = 0;
            public static final int MB_RULES = 1;
            public static final int MB_QUIT = 2;

            public static final int MB_WIDTH_DEFAULT = 469;
            public static final int MB_HEIGHT_DEFAULT = 304;
            public static final int MB_WIDTH = (int) (MB_WIDTH_DEFAULT * BUTTON_COMPRESSION_FACTOR * SCALE);
            public static final int MB_HEIGHT = (int) (MB_HEIGHT_DEFAULT * BUTTON_COMPRESSION_FACTOR * SCALE);
        }

        public static class NumPlayerButtons {
            public static final float BUTTON_COMPRESSION_FACTOR = 0.22f;

            public static final int NPB_2P = 0;
            public static final int NPB_3P = 1;
            public static final int NPB_4P = 2;
            public static final int NPB_5P = 3;
            public static final int NPB_6P = 4;
            public static final int NPB_7P = 5;
            public static final int NPB_BACK = 6;
            public static final int NPB_QUIT = 7;

            public static final int NPB_WIDTH_DEFAULT = 500;
            public static final int NPB_HEIGHT_DEFAULT = 304;
            public static final int NPB_WIDTH = (int) (NPB_WIDTH_DEFAULT * BUTTON_COMPRESSION_FACTOR * SCALE);
            public static final int NPB_HEIGHT = (int) (NPB_HEIGHT_DEFAULT * BUTTON_COMPRESSION_FACTOR * SCALE);
        }

        public static class HousePlayerSelectionButtons {
            // Player selection buttons
            public static final float PSB_BUTTON_COMPRESSION_FACTOR = 0.18f;

            public static final int PSB_P1 = 0;
            public static final int PSB_P2 = 1;
            public static final int PSB_P3 = 2;
            public static final int PSB_P4 = 3;
            public static final int PSB_P5 = 4;
            public static final int PSB_P6 = 5;
            public static final int PSB_P7 = 6;

            public static final int PSB_BACK = 7;
            public static final int PSB_QUIT = 8;
            public static final int PSB_START = 9;

            public static final int PSB_WIDTH_DEFAULT = 500;
            public static final int PSB_HEIGHT_DEFAULT = 304;
            public static final int PSB_WIDTH = (int) (PSB_WIDTH_DEFAULT * PSB_BUTTON_COMPRESSION_FACTOR * SCALE);
            public static final int PSB_HEIGHT = (int) (PSB_HEIGHT_DEFAULT * PSB_BUTTON_COMPRESSION_FACTOR * SCALE);

            // House selection buttons
            public static final float HSB_BUTTON_COMPRESSION_FACTOR = 0.35f;

            public static final int HSB_TARGARYEN = 10;
            public static final int HSB_STARK = 11;
            public static final int HSB_LANNISTER = 12;
            public static final int HSB_BARATHEON = 13;
            public static final int HSB_TYRELL = 14;
            public static final int HSB_ARRYN = 15;
            public static final int HSB_GREYJOY = 16;
            public static final int HSB_MARTELL = 17;
            public static final int HSB_TULLY = 18;
            public static final int HSB_BOLTON = 19;

            public static final int HSB_WIDTH_DEFAULT = 500;
            public static final int HSB_HEIGHT_DEFAULT = 100;
            public static final int HSB_WIDTH = (int) (HSB_WIDTH_DEFAULT * HSB_BUTTON_COMPRESSION_FACTOR * SCALE);
            public static final int HSB_HEIGHT = (int) (HSB_HEIGHT_DEFAULT * HSB_BUTTON_COMPRESSION_FACTOR * SCALE);
        }

        public static class MouseStates {
            public static final int NORMAL = 0;
            public static final int HOVER = 1;
            public static final int PRESSED = 2;
            public static final int CLICKED = 3;
        }

        public static class GameBoard {
            public static final int GB_CENTER_X = CELL_SIZE * 5;
            public static final int GB_CENTER_Y = CELL_SIZE * 5;
            public static final int GB_WIDTH = CELL_SIZE * 10;
            public static final int GB_HEIGHT = CELL_SIZE * 10;
        }
    }
}
