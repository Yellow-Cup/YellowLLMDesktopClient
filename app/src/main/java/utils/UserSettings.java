package utils;

import java.util.Properties;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.lang.SecurityException;
import java.io.IOException;
import ui.dialogues.AppMessage;
import utils.Config;

public class UserSettings {
    String userHomeDir;
    String fileSeparator;
    String settingsFilePath;
    Properties properties;

    public UserSettings() {
        this.userHomeDir = System.getProperty("user.home");
        this.fileSeparator = System.getProperty("file.separator");
        this.settingsFilePath = this.userHomeDir
            .concat(this.fileSeparator)
            .concat(Config.USER_SETTINGS_FILE_NAME);

        this.properties = new Properties();
        this.readUserSettings();
    }

    private void readUserSettings() {
        FileInputStream settingsFile = null;

        try {
            settingsFile = new FileInputStream(this.settingsFilePath);
        } catch(FileNotFoundException e) {
            new AppMessage("File with settings not found. Creating anew.").popUp();
            this.saveSettings();
        } catch(SecurityException e){
            new AppMessage("Something wrong with your privileges to read the file.").popUp();
        }

        try {
            this.properties.load(settingsFile);
        } catch(IOException e) {
            new AppMessage("Could not read user settings.").popUp();
        }
    }

    public void saveSettings() {
        FileWriter settingsFileWriter = null;

        try {
            settingsFileWriter = new FileWriter(this.settingsFilePath);
            this.properties.store(settingsFileWriter, "update");
        } catch (Exception e) {
            new AppMessage("Can't write to the settings file.").popUp();
            System.out.println(e.getMessage());
        }

    }

    public void setProperty(String key, String value) {
        this.properties.setProperty(key, value);
    }

}