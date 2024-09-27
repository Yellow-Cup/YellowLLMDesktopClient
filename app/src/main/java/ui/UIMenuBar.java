package ui;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.Dimension;
import utils.Config;


class UIMenuBar extends JMenuBar {
    JMenu menu;
    JMenuItem exitMenuItem;

    public UIMenuBar(){
        super();
        this.menu = new JMenu("Menu");

        this.exitMenuItem = new JMenuItem("Exit");
        this.exitMenuItem.addActionListener(
            (event) -> {
                System.exit(0);
            }
        );
        this.exitMenuItem.setSize(new Dimension(Config.WINDOW_MENU_ITEM_WIDTH, Config.WINDOW_MENU_ITEM_HEIGHT));
        this.exitMenuItem.setPreferredSize(new Dimension(Config.WINDOW_MENU_ITEM_WIDTH, Config.WINDOW_MENU_ITEM_HEIGHT));

        this.menu.add(exitMenuItem);
        this.add(menu);
        this.setPreferredSize(new Dimension(Config.WINDOW_WIDTH, Config.WINDOW_MENU_BAR_HEIGHT));
    }

}