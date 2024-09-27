package ui.dialogues;

import javax.swing.JFrame;
import javax.swing.JLabel;
import ui.UIButton;
import utils.Config;


public class AppMessage extends JFrame {
    UIButton ackButton;

    public AppMessage(String message) {
        super();
        this.setSize(Config.DIALOGUE_DEFAULT_WIDTH, Config.DIALOGUE_DEFAULT_HEGHT);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLayout(null);
        this.getContentPane().setBackground(Config.DEFAULT_WINDOW_BACKGROUND);
        this.setTitle(Config.MESSAGES_WINDOW_TITLE);
        this.setLocationRelativeTo(null);
        this.setAlwaysOnTop(true);

        ackButton = new UIButton("OK");
        ackButton.setBounds(
            (int) (Config.DIALOGUE_DEFAULT_WIDTH / 2) - (Config.BUTTON_DEFAULT_WIDTH / 2),
            (int) (Config.DIALOGUE_DEFAULT_HEGHT - Config.BUTTON_DEFAULT_HEIGHT * 1.5),
            Config.BUTTON_DEFAULT_WIDTH,
            Config.BUTTON_DEFAULT_HEIGHT
        );

        ackButton.addActionListener(
            (event) -> {
                this.dispose();
            }
        );

        JLabel msgDisplay = new JLabel(message);
        msgDisplay.setBounds(
            (int) (Config.BUTTON_DEFAULT_WIDTH / 2),
            (int) (Config.BUTTON_DEFAULT_HEIGHT / 2),
            (int) (Config.DIALOGUE_DEFAULT_WIDTH - Config.BUTTON_DEFAULT_WIDTH / 2),
            (int) (Config.DIALOGUE_DEFAULT_HEGHT - Config.BUTTON_DEFAULT_HEIGHT * 2)
        );
        msgDisplay.setForeground(Config.DEFAULT_TEXT_COLOR);
        
        this.add(msgDisplay);
        this.add(ackButton);
    }

    public void popUp() {
        this.setVisible(true);
    }

}