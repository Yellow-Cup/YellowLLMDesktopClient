package ui;

import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.GroupLayout;
import javax.swing.JScrollPane;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

import ui.dialogues.AppMessage;
import ui.UIButton;
import controllers.ChatController;
import utils.FontUtils;
import utils.Config;


public class UIUserInput extends JPanel {
    private ChatController chatController;
    private GroupLayout layout;
    private JScrollPane userTextInputPane;
    private JTextArea userTextInput;
    private UIButton clearTextButton, sendButton;

    public UIUserInput(ChatController chatController) {
        super();
        this.chatController = chatController;
        this.setSize(
            this.chatController.getHub().getConfig().WINDOW_WIDTH,
            this.chatController.getHub().getConfig().WINDOW_HEIGHT - 
            this.chatController.getHub().getConfig().CHAT_LOG_PANEL_HEIGHT - 
            this.chatController.getHub().getConfig().WINDOW_MENU_BAR_HEIGHT
        );
        /** The hosting window is supposed to host two panels
        * of the same width: grpahic output and input controls.
        */

        this.buildInputsUI();
    }

    private class SendKeyListener implements KeyListener {
        UIUserInput master;
        public SendKeyListener(UIUserInput master) {
            this.master = master;
        }

        @Override
        public void keyPressed(KeyEvent event) {
            if ((event.getKeyCode()==KeyEvent.VK_ENTER)&&(event.isControlDown())) {
                this.master.sendMessage();            
            }
        }

        @Override
        public void keyTyped(KeyEvent event) {}

        @Override
        public void keyReleased(KeyEvent event) {}
    }

    private void buildInputsUI() {
        this.layout = new GroupLayout(this);
        this.setLayout(layout);
        this.layout.setAutoCreateGaps(true);
        this.layout.setAutoCreateContainerGaps(true);

        this.userTextInput = new JTextArea();
        this.userTextInput.setLineWrap(true);
        this.userTextInput.setWrapStyleWord(true);
        this.userTextInputPane = new JScrollPane(userTextInput);
        this.userTextInput.addKeyListener(new SendKeyListener(this));

        FontUtils.setContainerFontSize(this.userTextInput, this.chatController.getHub().getConfig().GENERAL_FONT_SIZE); 


        this.clearTextButton = new UIButton(
            "Clear",
            (int) this.chatController.getHub().getConfig().WINDOW_WIDTH / 6,
            this.chatController.getHub()
        );
        this.clearTextButton.addActionListener(
            (event) -> { 
                this.clearUserTextInput();
            }
        );

        this.sendButton = new UIButton(
            "Send",
            (int) this.chatController.getHub().getConfig().WINDOW_WIDTH / 6,
            this.chatController.getHub()
        );
        this.sendButton.setHeight(this.chatController.getHub().getConfig().BUTTON_DEFAULT_HEIGHT * 3);
        this.sendButton.addActionListener(
            (event) -> {
                this.sendMessage();
            }
        );

        this.layout.setHorizontalGroup(
            this.layout.createSequentialGroup()
                .addComponent(this.userTextInputPane)
                .addGroup(this.layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(this.sendButton)
                    .addComponent(this.clearTextButton))
        );

        this.layout.setVerticalGroup(
            this.layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(this.userTextInputPane)
                .addGroup(this.layout.createSequentialGroup()
                    .addComponent(this.sendButton)
                    .addComponent(this.clearTextButton)
                )
            );

    }

    private void sendMessage() {
        String message = this.userTextInput.getText();
        if (message.length() > 0) {
            this.chatController.handleUserMessage(message);
            this.clearUserTextInput();
        } else {
            new AppMessage("Nothing to send. Type something, dummy!", this.chatController.getHub());
        }
    }

    private void clearUserTextInput() {
        this.userTextInput.setText("");
        this.focusOnInput();
    }

    public void focusOnInput() {
        this.userTextInput.requestFocusInWindow();
    }

}