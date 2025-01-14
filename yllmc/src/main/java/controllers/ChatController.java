package controllers;

import ui.UIMainWindow;
import ui.UIUserInput;
import ui.UIChatLog;


public class ChatController {

    private UIUserInput userInputUI;
    private UIChatLog chatLogUI;
    private UIMainWindow hub;

    private class LLMClientPoller implements Runnable {
            private ChatController controller;
            private String message;
        public LLMClientPoller(ChatController controller, String message){
            super();
            this.controller = controller;
            this.message = message;
        }
        public void run() {
            String response = this.controller.getHub().getLLMClient().request(this.message);
            this.controller.getChatLogUI().appendMessage(response, false);
        }
    }

    public ChatController(UIMainWindow hub) {
        this.hub = hub;
        
        this.userInputUI = new UIUserInput(this);
        this.chatLogUI = new UIChatLog(this);
    }

    public void handleUserMessage(String message) {
        this.chatLogUI.appendMessage(message, true);
        new Thread(new LLMClientPoller(this, message)).start();
    }

    public UIUserInput getUserInputUI() {
        return this.userInputUI;
    }

    public UIChatLog getChatLogUI() {
        return this.chatLogUI;
    }

    public UIMainWindow getHub() {
        return this.hub;
    }
}