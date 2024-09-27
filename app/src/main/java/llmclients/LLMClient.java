package llmclients;

public abstract class LLMClient {
    private String context = "";
    private int interactionsCount = 0;
    private int contextMaxInteractionsCount = 10;

    public LLMClient() {

    }

    public String request(String request) {
        System.out.println(request);
        String response = Long.toString(System.currentTimeMillis()) + ": " + request;

        if (this.interactionsCount++ > this.contextMaxInteractionsCount) {
            this.dropContext();
            this.interactionsCount = 0;
        } else {
            context += request + "\n" + response  + "\n";
        }
        return response;
    }

    public void dropContext() {
        this.context = "";
    }

}