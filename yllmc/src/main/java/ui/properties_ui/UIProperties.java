package ui.properties_ui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.event.DocumentListener;
import javax.swing.event.DocumentEvent;
import javax.swing.GroupLayout;
import javax.swing.BoxLayout;
import java.awt.Dimension;
import java.util.HashMap;
import java.util.Set;
import ui.UIButton;
import ui.dialogues.AppMessage;
import utils.UserSettings;
import ui.UIMainWindow;
import utils.FontUtils;

public class UIProperties extends JFrame{

    private JPanel propertiesPanel;
    private JScrollPane scrollPane;
    private GroupLayout layoutGeneral; 
    private BoxLayout layout4Properties;
    private UIButton saveButton, saveCloseButton, closeButton;
    private HashMap<String, String> defaults;
    private Set<String> propertiesKeys;
    private UIMainWindow hub;
    private String title = "Properties";
 
    public UIProperties(Set<String> propertiesKeys, UIMainWindow hub) {
        super();
        this.hub = hub;
        this.propertiesKeys = propertiesKeys;
        this.defaults = new HashMap<String, String>();
        this.buildUI();
    }


    public UIProperties(HashMap<String, String> defaults, UIMainWindow hub) {
        super();
        this.hub = hub;
        this.propertiesKeys = defaults.keySet();
        this.defaults = defaults;
        this.buildUI();
    }


    private void buildUI() {
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setMinimumSize(new Dimension(this.hub.getConfig().WINDOW_WIDTH, 0));
        this.setMaximumSize(new Dimension(this.hub.getConfig().WINDOW_WIDTH, this.hub.getConfig().WINDOW_HEIGHT));
        this.getContentPane().setBackground(this.hub.getConfig().DEFAULT_WINDOW_BACKGROUND);
        this.setTitle(this.title);

        this.layoutGeneral = new GroupLayout(this.getContentPane());
        this.getContentPane().setLayout(this.layoutGeneral);
        this.layoutGeneral.setAutoCreateGaps(true);
        this.layoutGeneral.setAutoCreateContainerGaps(true);

        this.buildPropertiesPanel();

        this.saveButton = new UIButton("Save", this.hub);
        this.saveButton.addActionListener(
            (event) -> {
                this.hub.getUserSettings().saveSettings();
                new AppMessage("Settings saved. :)", this.hub);
            }
        );
        this.saveCloseButton = new UIButton("Save and Close", this.hub);
        this.saveCloseButton.addActionListener(
            (event) -> {
                this.hub.getUserSettings().saveSettings();
                this.dispose();
            }
        );
        this.closeButton = new UIButton("Close w/o saving", this.hub);
        this.closeButton.addActionListener(
            (event) -> {
                this.dispose();
            }
        );

        this.layoutGeneral.setHorizontalGroup(
            this.layoutGeneral.createParallelGroup()
            .addComponent(this.scrollPane)
            .addGroup(
                this.layoutGeneral.createSequentialGroup()
                .addComponent(this.saveButton)
                .addComponent(this.saveCloseButton)
                .addComponent(this.closeButton)
            )
        );

        this.layoutGeneral.setVerticalGroup(
            this.layoutGeneral.createSequentialGroup()
            .addComponent(this.scrollPane)
            .addGroup(
                this.layoutGeneral.createParallelGroup()
                .addComponent(this.saveButton)
                .addComponent(this.saveCloseButton)
                .addComponent(this.closeButton)
            )
        );

        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }


    private class propertyTextFieldListener implements DocumentListener {
        private String propertyName;
        private UIProperties parent;
        private JTextField textField;

        public propertyTextFieldListener(String propertyName, JTextField textField, UIProperties parent){
            this.propertyName = propertyName;
            this.parent = parent;
            this.textField = textField;
        }

        @Override
        public void insertUpdate(DocumentEvent event){
            this.parent.hub.getUserSettings().setProperty(propertyName, textField.getText());
        }

        @Override
        public void removeUpdate(DocumentEvent event){
            this.parent.hub.getUserSettings().setProperty(propertyName, textField.getText());
        }

        @Override
        public void changedUpdate(DocumentEvent event) {
        }
    }


    private void buildPropertiesPanel() {
        this.propertiesPanel = new JPanel();
        this.layout4Properties = new BoxLayout(this.propertiesPanel, BoxLayout.Y_AXIS);
        this.propertiesPanel.setBackground(this.hub.getConfig().DEFAULT_WINDOW_BACKGROUND);
        this.propertiesPanel.setForeground(this.hub.getConfig().DEFAULT_TEXT_COLOR);
        this.scrollPane = new JScrollPane(this.propertiesPanel);

        this.propertiesPanel.setLayout(this.layout4Properties);


        for (String propertyName : this.propertiesKeys) {
            JLabel label = new JLabel(propertyName);
            label.setForeground(this.hub.getConfig().DEFAULT_TEXT_COLOR);
            label.setAlignmentX(0.0f);
            String value = this.hub.getUserSettings().getProperty(
                propertyName,
                this.defaults.getOrDefault(propertyName, "").toString()
            );

            if (value != "") {
                this.hub.getUserSettings().setProperty(propertyName, value); // to work the defaults if provided
            }
            
            FontUtils.setContainerFontSize(label, this.hub.getConfig().GENERAL_FONT_SIZE);
    
            JTextField textField = new JTextField(value);
            textField.getDocument().addDocumentListener(
                new propertyTextFieldListener(propertyName, textField, this)
            );

            FontUtils.setContainerFontSize(textField, this.hub.getConfig().GENERAL_FONT_SIZE);

            this.propertiesPanel.add(label);
            this.propertiesPanel.add(textField);
            this.propertiesPanel.add(new JLabel(" "));
        }
    }

}