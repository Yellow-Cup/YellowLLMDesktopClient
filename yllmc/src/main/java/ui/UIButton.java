package ui;

import javax.swing.JButton;
import java.awt.Dimension;
import ui.UIMainWindow;
import utils.FontUtils;

public class UIButton extends JButton {

    private UIMainWindow hub;

    public UIButton(String text, UIMainWindow hub) {
        super(text);
        this.hub = hub;
        this.init();

        this.setSize(this.hub.getConfig().BUTTON_DEFAULT_WIDTH, this.hub.getConfig().BUTTON_DEFAULT_HEIGHT);
        this.setMinimumSize(
            new Dimension(
                this.hub.getConfig().BUTTON_DEFAULT_WIDTH,
                this.hub.getConfig().BUTTON_DEFAULT_HEIGHT
            )
        );
        FontUtils.setContainerFontSize(this, this.hub.getConfig().GENERAL_FONT_SIZE);

        FontUtils.fitContainer(this, text, this.hub.getConfig());
    }

    public UIButton(String text, int width, UIMainWindow hub) {
        super(text);
        this.hub = hub;
        this.init();

        this.setSize(width, this.hub.getConfig().BUTTON_DEFAULT_HEIGHT);
        this.setMinimumSize(
            new Dimension(
                width,
                this.hub.getConfig().BUTTON_DEFAULT_HEIGHT)
            );
        FontUtils.fitFont(this, text, this.hub.getConfig());
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
        this.setBackground(this.hub.getConfig().DEFAULT_TEXT_COLOR);
        this.setBorder(this.hub.getConfig().DEFAULT_BUTTON_BORDER);
        this.setFocusPainted(this.hub.getConfig().FOCUSED_BUTTON_TEXT_IS_BORDERED);
    }

    public void setBounds(int x, int y) {
        super.setBounds(x, y, getWidth(), getHeight());
    }

}