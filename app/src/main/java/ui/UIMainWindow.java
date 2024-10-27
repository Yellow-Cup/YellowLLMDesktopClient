package ui;

import javax.swing.JFrame;
import ui.UIMenuBar;
import controllers.ChatController;
import utils.UserSettings;
import llmclients.LLMClientFactory;
import llmclients.LLMClient;
import utils.Config;

public class UIMainWindow extends JFrame {
    private ChatController chatController;
    private LLMClient llmClient;
    private UIMenuBar menuBar;
    private UserSettings userSettings;


    public UIMainWindow() {
        super();
        this.initComponents();
        this.setSize(Config.WINDOW_WIDTH, Config.WINDOW_HEIGHT);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setBackground(Config.DEFAULT_WINDOW_BACKGROUND);
        this.setLocationRelativeTo(null);

        this.buildUI();
        this.setVisible(true);
        this.chatController.getUserInputUI().focusOnInput();
    }


    public LLMClient getLLMClient() {
        return this.llmClient;
    }


    public UserSettings getUserSettings() {
        return this.userSettings;
    }


    public ChatController getChatController() {
        return this.chatController;
    }


    private void initComponents() {
        this.userSettings = new UserSettings();
        this.llmClient = LLMClientFactory.produce(Config.DEFAULT_LLM_CLIENT, this.userSettings);
        this.chatController = new ChatController(this);
    }


    private void buildUI() {
        this.setLayout(null);
        this.menuBar = new UIMenuBar(this);

        this.chatController.getChatLogUI().setBounds(0,
                this.menuBar.getHeight(),
                this.chatController.getChatLogUI().getWidth(),
                this.chatController.getChatLogUI().getHeight());

        this.chatController.getUserInputUI().setBounds(
                0,
                Config.CHAT_LOG_PANEL_HEIGHT + this.menuBar.getHeight(),
                chatController.getUserInputUI().getWidth(), chatController.getUserInputUI().getHeight() - this.menuBar.getHeight());

        this.setJMenuBar(this.menuBar);
        this.add(this.chatController.getChatLogUI());
        this.add(chatController.getUserInputUI());
    }

}