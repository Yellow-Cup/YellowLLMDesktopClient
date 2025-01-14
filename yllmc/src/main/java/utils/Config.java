package utils;

import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.border.Border;
import java.awt.Toolkit;
import java.awt.Dimension;

public class Config {
    private float scale;
    private int BASELINE_SCREEN_HEIGHT = 900;
    private final int BASE_WINDOW_WIDTH = 600;
    private final int BASE_WINDOW_HEIGHT = 600; 
    private final int BASE_WINDOW_MENU_BAR_HEIGHT = 24;
    private final int BASE_GENERAL_FONT_SIZE = 16;
    private final int BASE_MENU_FONT_SIZE = 14;
    private final int BASE_MENU_ITEM_WIDTH = BASE_WINDOW_WIDTH / 4;
    private final int BASE_MENU_ITEM_HEIGHT = 24;
    private final int BASE_DIALOGUE_DEFAULT_WIDTH = 300;
    private final int BASE_DIALOGUE_DEFAULT_HEIGHT = 200;
    private final int BASE_FONT_FIT_SIDES_MARGIN = 16;
    private final int BASE_BUTTON_DEFAULT_WIDTH = 64;
    private final int BASE_BUTTON_DEFAULT_HEIGHT = 32;
    private final float BASE_DEFAULT_TEXT_INDENT = 32;

    public int GENERAL_FONT_SIZE;
    public int MENU_FONT_SIZE;
    public int WINDOW_MENU_BAR_HEIGHT;
    public int WINDOW_WIDTH;
    public int WINDOW_HEIGHT; 
    public int WINDOW_MENU_ITEM_WIDTH;
    public int WINDOW_MENU_ITEM_HEIGHT;
    public Color DEFAULT_WINDOW_BACKGROUND;
    public Color DEFAULT_TEXT_COLOR;
    public int CHAT_LOG_PANEL_HEIGHT;
    public Color DEFAULT_CHAT_LOG_TEXT_COLOR;
    public int DIALOGUE_DEFAULT_WIDTH;
    public int DIALOGUE_DEFAULT_HEGHT;
    public int FONT_FIT_SIDES_MARGIN;
    public int BUTTON_DEFAULT_WIDTH = (int) (this.BASE_BUTTON_DEFAULT_WIDTH * this.scale);
    public int BUTTON_DEFAULT_HEIGHT = (int) (this.BASE_BUTTON_DEFAULT_HEIGHT * this.scale);
    public Border DEFAULT_BUTTON_BORDER;
    public boolean FOCUSED_BUTTON_TEXT_IS_BORDERED = false;
    public float DEFAULT_TEXT_INDENT;

    public final String MESSAGES_WINDOW_TITLE = "Message for you! 💌";
    public final String DEFAULT_LLM_CLIENT = "OpenAI";
    public final String USER_SETTINGS_FILE_NAME = "yllmdc.cfg";


    public Config() {
        Dimension resolution = Toolkit.getDefaultToolkit().getScreenSize();
        this.scale = (float) resolution.height / this.BASELINE_SCREEN_HEIGHT;

        this.DEFAULT_WINDOW_BACKGROUND = new Color(78, 78, 78);
        this.DEFAULT_TEXT_COLOR = new Color(200, 200, 200);
        this.DEFAULT_CHAT_LOG_TEXT_COLOR = new Color(94, 233, 168);


        this.GENERAL_FONT_SIZE = (int) (this.BASE_GENERAL_FONT_SIZE * this.scale);
        this.MENU_FONT_SIZE = (int) (this.BASE_MENU_FONT_SIZE * this.scale);
    
        this.WINDOW_MENU_BAR_HEIGHT = (int) (this.BASE_WINDOW_MENU_BAR_HEIGHT * this.scale);
    
        this.WINDOW_WIDTH = (int) (this.BASE_WINDOW_WIDTH * this.scale);
        this.WINDOW_HEIGHT = (int) ((this.BASE_WINDOW_HEIGHT * this.scale) + this.WINDOW_MENU_BAR_HEIGHT); 

        this.WINDOW_MENU_ITEM_WIDTH = (int) (this.BASE_MENU_ITEM_WIDTH * this.scale);
        this.WINDOW_MENU_ITEM_HEIGHT = (int) (this.BASE_MENU_ITEM_HEIGHT * this.scale);
        
        this.CHAT_LOG_PANEL_HEIGHT = (int) ((this.WINDOW_HEIGHT - this.WINDOW_MENU_BAR_HEIGHT) * 0.75); /** separation line between chat output and UI panel on the bottom */

        this.DIALOGUE_DEFAULT_WIDTH = (int) (this.BASE_DIALOGUE_DEFAULT_WIDTH * this.scale);
        this.DIALOGUE_DEFAULT_HEGHT = (int) (this.BASE_DIALOGUE_DEFAULT_HEIGHT * this.scale);
        this.FONT_FIT_SIDES_MARGIN = (int) (this.BASE_FONT_FIT_SIDES_MARGIN * this.scale);
        this.BUTTON_DEFAULT_WIDTH = (int) (BASE_BUTTON_DEFAULT_WIDTH * this.scale);
        this.BUTTON_DEFAULT_HEIGHT = (int) (BASE_BUTTON_DEFAULT_HEIGHT * this.scale);
        this.DEFAULT_BUTTON_BORDER = BorderFactory.createRaisedBevelBorder();
        this.DEFAULT_TEXT_INDENT = (float) this.BASE_DEFAULT_TEXT_INDENT * this.scale;
           
    }

}