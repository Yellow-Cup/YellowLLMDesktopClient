package controllers;

import ui.UIUserInput;
import ui.UIChatLog;
import llmclients.LLMClient;
import llmclients.LLMClientFactory;
import utils.Config;


public class ChatController {

    public UIUserInput userInputUI;
    public UIChatLog chatLogUI;
    private LLMClient llmClient;

    public ChatController() {
        this.llmClient = LLMClientFactory.produce(Config.DEFAULT_LLM_CLIENT);
        this.userInputUI = new UIUserInput(this);
        this.chatLogUI = new UIChatLog(this);
    }

    public void handleUserMessage(String message) {
        this.chatLogUI.appendMessage(message, true);
        String response = this.llmClient.request(message);
        this.chatLogUI.appendMessage(response, false);
    }
}