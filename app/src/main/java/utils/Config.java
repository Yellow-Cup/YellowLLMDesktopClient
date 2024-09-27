package utils;

import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.border.Border;

public abstract class Config {
    public static float scale = 1;

    // App main window parameters
    public static final int BASE_WINDOW_WIDTH = 600;
    public static final int BASE_WINDOW_HEIGHT = 600; 
    public static final int BASE_WINDOW_MENU_BAR_HEIGHT = 24;

    public static final int WINDOW_MENU_BAR_HEIGHT = (int) (BASE_WINDOW_MENU_BAR_HEIGHT * scale);

    public static final int WINDOW_WIDTH = (int) (BASE_WINDOW_WIDTH * scale);
    public static final int WINDOW_HEIGHT = (int) ((BASE_WINDOW_HEIGHT * scale) + WINDOW_MENU_BAR_HEIGHT); 

    public static final int BASE_MENU_ITEM_WIDTH = BASE_WINDOW_HEIGHT / 4;
    public static final int BASE_MENU_ITEM_HEIGHT = 24;

    public static final int WINDOW_MENU_ITEM_WIDTH = (int) (BASE_MENU_ITEM_WIDTH * scale);
    public static final int WINDOW_MENU_ITEM_HEIGHT = (int) (BASE_MENU_ITEM_HEIGHT * scale);

    public static final Color DEFAULT_WINDOW_BACKGROUND = new Color(78, 78, 78);
    public static final Color DEFAULT_TEXT_COLOR = new Color(200, 200, 200);

    //-----
    //Main window UI components
    public static final int CHAT_LOG_PANEL_HEIGHT = (int) ((WINDOW_HEIGHT - WINDOW_MENU_BAR_HEIGHT) * 0.75); /** separation line between chat output and UI panel on the bottom */
    public static final Color DEFAULT_CHAT_LOG_TEXT_COLOR = new Color(94, 233, 168);

    //-----
    // App pop up dialogues
    public static final int BASE_DIALOGUE_DEFAULT_WIDTH = 300;
    public static final int BASE_DIALOGUE_DEFAULT_HEIGHT = 200;

    public static final int DIALOGUE_DEFAULT_WIDTH = (int) (BASE_DIALOGUE_DEFAULT_WIDTH * scale);
    public static final int DIALOGUE_DEFAULT_HEGHT = (int) (BASE_DIALOGUE_DEFAULT_HEIGHT * scale);

    public static final String MESSAGES_WINDOW_TITLE = "Message for you! 💌";

    //-----
    // Fitting fonts to containers and vice versa
    public static final int BASE_FONT_FIT_SIDES_MARGIN = 16;
    public static final int FONT_FIT_SIDES_MARGIN = (int) (BASE_FONT_FIT_SIDES_MARGIN * scale);

    //-----
    // UI Buttons
    public static final int BASE_BUTTON_DEFAULT_WIDTH = 64;
    public static final int BASE_BUTTON_DEFAULT_HEIGHT = 32;

    public static final int BUTTON_DEFAULT_WIDTH = (int) (BASE_BUTTON_DEFAULT_WIDTH * scale);
    public static final int BUTTON_DEFAULT_HEIGHT = (int) (BASE_BUTTON_DEFAULT_HEIGHT * scale);
    public static final Border DEFAULT_BUTTON_BORDER = BorderFactory.createRaisedBevelBorder();
    public static final boolean FOCUSED_BUTTON_TEXT_IS_BORDERED = false;

    // ----
    // Chat log view
    public static final float BASE_DEFAULT_TEXT_INDENT = 32;
    public static final float DEFAULT_TEXT_INDENT = (float) BASE_DEFAULT_TEXT_INDENT * scale;

    //-----
    // LLM
    public static final String DEFAULT_LLM_CLIENT = "test";

    //-----
    // Miscelaneous
    public static final String USER_SETTINGS_FILE_NAME = "yllmdc.cfg";
    //-----

}