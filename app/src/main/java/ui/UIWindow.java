package ui;

import javax.swing.JFrame;
import ui.UIMenuBar;
import controllers.ChatController;
import utils.UserSettings;
import utils.Config;

public class UIWindow extends JFrame {
    ChatController chatController;
    UIMenuBar menuBar;
    UserSettings userSettings;

    public UIWindow() {
        super();
        this.chatController = new ChatController();
        this.setSize(Config.WINDOW_WIDTH, Config.WINDOW_HEIGHT);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setBackground(Config.DEFAULT_WINDOW_BACKGROUND);
        this.setLocationRelativeTo(null);

        this.buildUI();
        this.setVisible(true);
        this.chatController.userInputUI.focusOnInput();

        this.userSettings = new UserSettings();
        // this.userSettings.setProperty("a", "n");
        // this.userSettings.saveSettings();
    }

    private void buildUI() {
        this.setLayout(null);
        this.menuBar = new UIMenuBar();

        this.chatController.chatLogUI.setBounds(0,
                this.menuBar.getHeight(),
                this.chatController.chatLogUI.getWidth(),
                this.chatController.chatLogUI.getHeight());

        this.chatController.userInputUI.setBounds(
                0,
                Config.CHAT_LOG_PANEL_HEIGHT + this.menuBar.getHeight(),
                chatController.userInputUI.getWidth(), chatController.userInputUI.getHeight() - this.menuBar.getHeight());

        this.setJMenuBar(this.menuBar);
        this.add(this.chatController.chatLogUI);
        this.add(chatController.userInputUI);
    }

}