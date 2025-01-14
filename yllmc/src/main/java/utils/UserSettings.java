package utils;

import java.util.Properties;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.lang.SecurityException;
import java.io.IOException;
import ui.UIMainWindow;
import ui.dialogues.AppMessage;

public class UserSettings extends Properties {
    private String userHomeDir;
    private String fileSeparator;
    private String settingsFilePath;
    private UIMainWindow hub;

    public UserSettings(UIMainWindow hub) {
        super();
        this.hub = hub;
        this.userHomeDir = System.getProperty("user.home");
        this.fileSeparator = System.getProperty("file.separator");
        this.settingsFilePath = this.userHomeDir
            .concat(this.fileSeparator)
            .concat(this.hub.getConfig().USER_SETTINGS_FILE_NAME);

        this.readUserSettings();
    }

    
    public void saveSettings() {
        FileWriter settingsFileWriter = null;

        try {
            settingsFileWriter = new FileWriter(this.settingsFilePath);
            this.store(settingsFileWriter, "");
        } catch (Exception e) {
            new AppMessage("Can't write to the settings file.", this.hub);
            System.out.println(e.getMessage());
        }
    }

    
    private void readUserSettings() {
        FileInputStream settingsFile = null;
        boolean firstTime = false;

        try {
            settingsFile = new FileInputStream(this.settingsFilePath);
        } catch(FileNotFoundException e) {
            new AppMessage("File with settings not found. Creating anew.", this.hub);
            this.saveSettings();
            firstTime = true;
        } catch(SecurityException e){
            new AppMessage("Something wrong with your privileges to read the file.", this.hub);
        }

        if (firstTime) {
            try {
                settingsFile = new FileInputStream(this.settingsFilePath);
            } catch(FileNotFoundException e) {
                new AppMessage("Something is wrong with the settings file creation.", this.hub);
            } 
        }

        try {
            this.load(settingsFile);
        } catch(IOException e) {
            new AppMessage("Could not read user settings.", this.hub);
        }
    }

}