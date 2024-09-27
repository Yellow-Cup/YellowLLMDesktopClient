package ui;

import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.JScrollPane;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.BadLocationException;
import javax.swing.text.StyleContext;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.GroupLayout;
import ui.dialogues.AppMessage;
import controllers.ChatController;
import utils.Config;

public class UIChatLog extends JPanel {
    ChatController chatController;
    JTextPane textPane;
    JScrollPane scrollPane;
    DefaultStyledDocument chatLog;
    StyleContext styleContext;
    Style userStyle, correspondentStyle;
    GroupLayout layout;

    public UIChatLog(ChatController chatController) {
        super();
        this.chatController = chatController;
        this.setSize(Config.WINDOW_WIDTH, Config.CHAT_LOG_PANEL_HEIGHT);

        /**
         * The hosing window is supposed to host two panels
         * of the same width: grpahic output and input controls.
         */
        this.styleContext = new StyleContext();
        this.chatLog = new DefaultStyledDocument(styleContext);
        this.textPane = new JTextPane(chatLog);
        this.textPane.setEditable(false);

        this.setBackground(Config.DEFAULT_WINDOW_BACKGROUND);
        this.textPane.setBackground(Config.DEFAULT_WINDOW_BACKGROUND);

        this.defineStyles();
        this.buildChatLogUI();

    }

    private void defineStyles() {
        Style defaultStyle = styleContext.getStyle(StyleContext.DEFAULT_STYLE);
        StyleConstants.setForeground(defaultStyle, Config.DEFAULT_CHAT_LOG_TEXT_COLOR);

        this.userStyle = styleContext.addStyle("userStyle", defaultStyle);
        this.correspondentStyle = styleContext.addStyle("correspondentStyle", defaultStyle);

        StyleConstants.setBold(userStyle, true);
        StyleConstants.setRightIndent(userStyle, Config.DEFAULT_TEXT_INDENT);
        StyleConstants.setLeftIndent(correspondentStyle, Config.DEFAULT_TEXT_INDENT);
    }

    private void buildChatLogUI() {
        this.layout = new GroupLayout(this);
        this.setLayout(layout);
        this.layout.setAutoCreateContainerGaps(true);
        this.layout.setAutoCreateGaps(true);

        this.scrollPane = new JScrollPane(textPane);

        this.layout.setHorizontalGroup(
            this.layout.createSequentialGroup()
                        .addComponent(this.scrollPane));

        this.layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addComponent(this.scrollPane));
    }

    public void appendMessage(String message, boolean isUser) {
        Style style = correspondentStyle;
        if (isUser)
            style = userStyle;

        int logLength = chatLog.getLength();

        try {
            this.chatLog.insertString(logLength, message + "\n\n", null);
            this.chatLog.setParagraphAttributes(logLength, logLength, style, true);
        } catch (BadLocationException e) {
            new AppMessage("ERROR: " + e.getMessage()).popUp();
        }
    }
}