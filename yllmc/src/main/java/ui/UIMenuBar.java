package ui;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.Dimension;
import java.util.HashMap;
import ui.UIMainWindow;
import llmclients.LLMClientFactory;
import llmclients.LLMClient;
import ui.properties_ui.UIProperties;
import utils.Config;


class UIMenuBar extends JMenuBar {
    private JMenu mainMenu, clientsMenu;
    private UIMainWindow hub;

    public UIMenuBar(UIMainWindow hub){
        super();
        this.hub = hub;
        mainMenu = new JMenu("Menu");
        clientsMenu = new JMenu("LLM Clients Properties");
        mainMenu.add(clientsMenu);

        JMenuItem clearLogMenuItem = new JMenuItem("Clear chat");
        clearLogMenuItem.addActionListener(
            (event) -> {
                this.hub
                    .getChatController()
                    .getChatLogUI()
                    .clear();
            }
        );
        
        JMenuItem exitMenuItem = new JMenuItem("Exit");
        exitMenuItem.addActionListener(
            (event) -> {
                System.exit(0);
            }
        );


        JMenuItem clientItem;
        for (String clientName:LLMClientFactory.getClientsList()) {
            clientItem = new JMenuItem(clientName);
            clientItem.addActionListener(
                (event) -> {
                    LLMClient clientInstance = LLMClientFactory.produce(clientName, this.hub.getUserSettings());
                    HashMap<String, String> clientParametersDefaults = clientInstance.getParameters();
                    new UIProperties(clientParametersDefaults, this.hub.getUserSettings());
                }
            );
            clientsMenu.add(clientItem);
        }

        mainMenu.setPreferredSize(new Dimension(Config.BUTTON_DEFAULT_WIDTH, Config.WINDOW_MENU_BAR_HEIGHT));
        clearLogMenuItem.setPreferredSize(new Dimension(Config.BUTTON_DEFAULT_WIDTH, Config.WINDOW_MENU_BAR_HEIGHT));
        exitMenuItem.setPreferredSize(new Dimension(Config.BUTTON_DEFAULT_WIDTH, Config.WINDOW_MENU_BAR_HEIGHT));

        this.add(mainMenu);
        this.add(clearLogMenuItem);
        this.add(exitMenuItem);
        this.setPreferredSize(new Dimension(Config.WINDOW_WIDTH, Config.WINDOW_MENU_BAR_HEIGHT));
    }

}