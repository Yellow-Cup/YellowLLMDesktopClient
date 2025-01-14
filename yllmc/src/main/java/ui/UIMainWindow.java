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
    private Config config;


    public UIMainWindow(Config config) {
        super();
        this.config = config;
        this.initComponents();
        //this.setSize(this.config.WINDOW_WIDTH, this.config.WINDOW_HEIGHT);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setBackground(this.config.DEFAULT_WINDOW_BACKGROUND);

        this.buildUI();
        this.setVisible(true);
        this.setSize(
            this.config.WINDOW_WIDTH,
            this.config.WINDOW_HEIGHT
            + this.getInsets().top
            + this.getInsets().bottom
            + this.menuBar.getHeight()
        );
        this.setLocationRelativeTo(null);
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


    public Config getConfig() {
        return this.config;
    }

    private void initComponents() {
        this.userSettings = new UserSettings(this);
        this.llmClient = LLMClientFactory.produce(this.config.DEFAULT_LLM_CLIENT, this.userSettings);
        this.chatController = new ChatController(this);
    }


    private void buildUI() {
        this.setLayout(null);
        this.menuBar = new UIMenuBar(this);

        this.chatController.getChatLogUI()
            .setBounds(
                0,
                this.menuBar.getHeight(),
                this.chatController.getChatLogUI().getWidth(),
                this.chatController.getChatLogUI().getHeight()
            );

        this.chatController.getUserInputUI()
            .setBounds(
                0,
                this.config.CHAT_LOG_PANEL_HEIGHT + this.menuBar.getHeight(),
                chatController.getUserInputUI().getWidth(),
                chatController.getUserInputUI().getHeight() - this.menuBar.getHeight()
            );

        this.setJMenuBar(this.menuBar);
        this.add(this.chatController.getChatLogUI());
        this.add(chatController.getUserInputUI());
    }

}