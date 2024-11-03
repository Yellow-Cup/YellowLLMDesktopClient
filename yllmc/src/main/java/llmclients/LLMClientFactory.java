package llmclients;

import llmclients.LLMClient;
import llmclients.LLMDummyClient;
import llmclients.LLMOpenAIClient;
import utils.UserSettings;


public class LLMClientFactory {
    public static LLMClient produce(String client, UserSettings properties) {
        switch (client) {
            case "OpenAI":
                return new LLMOpenAIClient(properties);
            case "test":
            default: return new LLMDummyClient(properties);
        }
    }

    public static String[] getClientsList() {
        return new String[] {
            "OpenAI",
            "test"
        };
    }
}