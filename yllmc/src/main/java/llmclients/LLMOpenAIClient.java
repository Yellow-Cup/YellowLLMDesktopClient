package llmclients;

import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpClient;
import java.net.URI;
import org.json.JSONObject;
import org.json.JSONArray;
import java.util.HashMap;

import llmclients.LLMClient;
import utils.UserSettings;

class LLMOpenAIClient extends LLMClient {

    protected String name = "OpenAI";
    private String apiKey;
    private String latestHttpResponse = "";
    private String latestHttpPayload = "";
    private HashMap<String, String> roles = new HashMap<String, String>();
    private String role = "basic";

    LLMOpenAIClient(UserSettings properties) {
        super(properties);
        this.name = "OpenAI";
        this.parameters.put("url", "https://api.openai.com/v1/chat/completions");
        this.parameters.put("default_model", "gpt-4o");
        this.parameters.put("API_key", "");

        this.roles.put("basic", "You are a helpful assistant");
    }

    @Override
    public String request(String request) {
        String response = "";

        if (this.isKeySet()) {
            HttpRequest httpRequest = HttpRequest.newBuilder()
                    .POST(HttpRequest.BodyPublishers
                            .ofString(prepareJSON(request, this.properties.getProperty("default_model"))))
                    .uri(URI.create("https://api.openai.com/v1/chat/completions"))
                    .header("Content-Type", "application/json")
                    .header("Authorization", "Bearer " + this.properties.getProperty("API_key"))
                    .build();

            try {
                HttpResponse<String> httpResponse = HttpClient.newHttpClient()
                        .send(httpRequest, HttpResponse.BodyHandlers.ofString());

                this.latestHttpResponse = httpResponse.body();
                JSONObject json = new JSONObject(this.latestHttpResponse);

                response = json
                        .getJSONArray("choices")
                        .getJSONObject(0)
                        .getJSONObject("message")
                        .getString("content");

            } catch (Exception e) {
                response = "Connection error: "
                    + e.getMessage()
                    + "\n\n"
                    + "latest http response: \n\n"
                    + this.latestHttpResponse
                    + "\n\n"
                    + "latest http payload: \n\n"
                    + this.latestHttpPayload;
            }

        } else {
            response = "ERROR: You need to setup the API key, use the Menu.";
        }

        return response;
    }

    private String prepareJSON(String request, String model) {
        JSONObject payload = new JSONObject();
        JSONObject msg;
        JSONArray messages = new JSONArray();

        payload.put("model",model);

        msg = new JSONObject();
        msg.put("role", "system");
        msg.put("content", this.roles.get(this.role));
        messages.put(msg);
        msg = new JSONObject();
        msg.put("role", "user");
        msg.put("content", request);
        messages.put(msg);

        payload.put("messages", messages);

        this.latestHttpPayload = payload.toString();
        return this.latestHttpPayload;
    }

    private boolean isKeySet() {
        this.apiKey = this.properties.getProperty("API_key");
        if ((this.apiKey == null) || (this.apiKey.length() == 0)) {
            return false;
        } else {
            return true;
        }
    }

}