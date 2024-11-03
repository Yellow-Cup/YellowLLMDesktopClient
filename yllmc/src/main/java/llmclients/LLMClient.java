package llmclients;

import java.util.HashMap;
import utils.UserSettings;

public abstract class LLMClient {
    protected String context = "";
    protected int interactionsCount = 0;
    protected int contextMaxInteractionsCount = 10;
    protected HashMap<String, String> parameters = new HashMap<String, String>();
    /*
    * hasmap with parameters, values are defaults
    */

    protected String name = "Dummy Client";
    protected UserSettings properties;

    public LLMClient(UserSettings properties) {
        this.properties = properties;
    }

    public String request(String request) {
        String response = Long.toString(System.currentTimeMillis()) + ": " + request;

        if (this.interactionsCount++ > this.contextMaxInteractionsCount) {
            this.dropContext();
            this.interactionsCount = 0;
        } else {
            context += request + "\n" + response  + "\n";
        }
        return response;
    }

    public HashMap<String, String> getParameters() {
        return this.parameters;
    }

    protected void dropContext() {
        this.context = "";
    }

    public String getName() {
        return this.name;
    }

}