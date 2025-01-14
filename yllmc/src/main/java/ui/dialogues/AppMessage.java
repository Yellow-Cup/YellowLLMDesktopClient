package ui.dialogues;

import javax.swing.JFrame;
import javax.swing.JLabel;
import ui.UIButton;
import ui.UIMainWindow;


public class AppMessage extends JFrame {
    private UIButton ackButton;

    public AppMessage(String message, UIMainWindow hub) {
        super();
        this.setSize(hub.getConfig().DIALOGUE_DEFAULT_WIDTH, hub.getConfig().DIALOGUE_DEFAULT_HEGHT);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLayout(null);
        this.getContentPane().setBackground(hub.getConfig().DEFAULT_WINDOW_BACKGROUND);
        this.setTitle(hub.getConfig().MESSAGES_WINDOW_TITLE);
        this.setLocationRelativeTo(null);
        this.setAlwaysOnTop(true);

        this.ackButton = new UIButton("OK", hub);
        this.ackButton.setBounds(
            (int) (hub.getConfig().DIALOGUE_DEFAULT_WIDTH / 2) - (hub.getConfig().BUTTON_DEFAULT_WIDTH / 2),
            (int) (hub.getConfig().DIALOGUE_DEFAULT_HEGHT - hub.getConfig().BUTTON_DEFAULT_HEIGHT * 1.5),
            hub.getConfig().BUTTON_DEFAULT_WIDTH,
            hub.getConfig().BUTTON_DEFAULT_HEIGHT
        );

        this.ackButton.addActionListener(
            (event) -> {
                this.dispose();
            }
        );

        JLabel msgDisplay = new JLabel(message);
        msgDisplay.setBounds(
            (int) (hub.getConfig().BUTTON_DEFAULT_WIDTH / 2),
            (int) (hub.getConfig().BUTTON_DEFAULT_HEIGHT / 2),
            (int) (hub.getConfig().DIALOGUE_DEFAULT_WIDTH - hub.getConfig().BUTTON_DEFAULT_WIDTH / 2),
            (int) (hub.getConfig().DIALOGUE_DEFAULT_HEGHT - hub.getConfig().BUTTON_DEFAULT_HEIGHT * 2)
        );
        msgDisplay.setForeground(hub.getConfig().DEFAULT_TEXT_COLOR);
        
        this.add(msgDisplay);
        this.add(this.ackButton);
        this.setVisible(true);
    }

}