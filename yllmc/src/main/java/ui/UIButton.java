package ui;

import javax.swing.JButton;
import java.awt.Dimension;
import utils.Config;
import utils.FontUtils;

public class UIButton extends JButton {

    public UIButton(String text) {
        super(text);
        this.init();

        this.setSize(Config.BUTTON_DEFAULT_WIDTH, Config.BUTTON_DEFAULT_HEIGHT);
        this.setMinimumSize(new Dimension(Config.BUTTON_DEFAULT_WIDTH, Config.BUTTON_DEFAULT_HEIGHT));

        FontUtils.fitContainer(this, text);
    }

    public UIButton(String text, int width) {
        super(text);
        this.init();

        this.setSize(width, Config.BUTTON_DEFAULT_HEIGHT);
        this.setMinimumSize(new Dimension(width, Config.BUTTON_DEFAULT_HEIGHT));
        FontUtils.fitFont(this, text);
    }

    public void setWidth(int width) {
        this.setSize(width, getHeight());
        this.setMinimumSize(new Dimension(width, getHeight()));
    }

    public void setHeight(int height) {
        this.setSize(getWidth(), height);
        this.setMinimumSize(new Dimension(getWidth(), height));
    }

    private void init() {
        this.setBackground(Config.DEFAULT_TEXT_COLOR);
        this.setBorder(Config.DEFAULT_BUTTON_BORDER);
        this.setFocusPainted(Config.FOCUSED_BUTTON_TEXT_IS_BORDERED);
    }

    public void setBounds(int x, int y) {
        super.setBounds(x, y, getWidth(), getHeight());
    }

}