package llmclients;

import llmclients.LLMClient;
import llmclients.LLMDummyClient;


public class LLMClientFactory {
    public static LLMClient produce(String client) {
        switch (client) {
            case "test":
            default: return new LLMDummyClient();
        }
    }
}