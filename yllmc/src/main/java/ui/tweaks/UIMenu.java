package ui.tweaks;

import javax.swing.JMenu;
import java.awt.Dimension;
import utils.FontUtils;
import ui.UIMenuBar;;

public class UIMenu extends JMenu {

    public UIMenu(String label, UIMenuBar menuBar) {
        super(label);
        FontUtils.setContainerFontSize(this, menuBar.getHub().getConfig().MENU_FONT_SIZE);
        this.setPreferredSize(
            new Dimension(
                menuBar.getHub().getConfig().WINDOW_WIDTH,
                menuBar.getHub().getConfig().WINDOW_MENU_BAR_HEIGHT
            )
        );
    }
}