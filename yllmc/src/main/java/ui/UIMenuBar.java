package ui;

import javax.swing.JMenuBar;
import java.awt.Dimension;
import java.util.HashMap;
import ui.UIMainWindow;
import llmclients.LLMClientFactory;
import llmclients.LLMClient;
import ui.properties_ui.UIProperties;
import ui.tweaks.UIMenuItem;
import ui.tweaks.UIMenu;


public class UIMenuBar extends JMenuBar {
    private UIMenu mainMenu, clientsMenu;
    private UIMainWindow hub;

    public UIMenuBar(UIMainWindow hub){
        super();
        this.hub = hub;
        mainMenu = new UIMenu("Menu", this);

        clientsMenu = new UIMenu("LLM Clients Properties", this);

        mainMenu.add(clientsMenu);

        UIMenuItem clearLogMenuItem = new UIMenuItem("Clear chat", this);
        clearLogMenuItem.addActionListener(
            (event) -> {
                this.hub
                    .getChatController()
                    .getChatLogUI()
                    .clear();
            }
        );

        UIMenuItem exitMenuItem = new UIMenuItem("Exit", this);
        exitMenuItem.addActionListener(
            (event) -> {
                System.exit(0);
            }
        );

        UIMenuItem clientItem;
        for (String clientName:LLMClientFactory.getClientsList()) {
            clientItem = new UIMenuItem(clientName, this);
            clientItem.addActionListener(
                (event) -> {
                    LLMClient clientInstance = LLMClientFactory.produce(clientName, this.hub.getUserSettings());
                    HashMap<String, String> clientParametersDefaults = clientInstance.getParameters();
                    new UIProperties(clientParametersDefaults, this.hub);
                }
            );
            clientsMenu.add(clientItem);
        }

        mainMenu.setPreferredSize(
            new Dimension(
                this.hub.getConfig().BUTTON_DEFAULT_WIDTH, 
                this.hub.getConfig().WINDOW_MENU_BAR_HEIGHT
            )
        );

        clearLogMenuItem.setPreferredSize(
            new Dimension(
                this.hub.getConfig().BUTTON_DEFAULT_WIDTH,
                this.hub.getConfig().WINDOW_MENU_BAR_HEIGHT
            )
        );

        exitMenuItem.setPreferredSize(
            new Dimension(
                this.hub.getConfig().BUTTON_DEFAULT_WIDTH,
                this.hub.getConfig().WINDOW_MENU_BAR_HEIGHT
            )
        );

        this.add(mainMenu);
        this.add(clearLogMenuItem);
        this.add(exitMenuItem);

        this.setPreferredSize(
            new Dimension(
                this.hub.getConfig().WINDOW_WIDTH,
                this.hub.getConfig().WINDOW_MENU_BAR_HEIGHT
            )
        );
    }


    public UIMainWindow getHub() {
        return this.hub;
    }
}