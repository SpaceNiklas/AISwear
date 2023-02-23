package com.spaceniklas.aiswear;

import com.theokanning.openai.OpenAiService;
import com.theokanning.openai.completion.CompletionRequest;

public class Swear {

    Plugin plugin;


    private OpenAiService service = new OpenAiService(plugin.getApikey(), 0);

    public boolean check(String s) {
        CompletionRequest request = CompletionRequest.builder()
                .prompt("Does \"" + s + "\" contain a swear word or is it maybe insulting to another player? Only answer with \"true\" or \"false\" in lower case.")
                .model("text-davinci-003")
                .temperature(0D)
                .maxTokens(4)
                .topP(1D)
                .frequencyPenalty(0.5D)
                .presencePenalty(0D)
                .bestOf(1)
                .build();

        System.out.println(service.createCompletion(request).getChoices().get(0).getText());

        if (service.createCompletion(request).getChoices().get(0).getText().contains("true")) {
            return true;
        }
        for (String s2 : plugin.getBlacklist()) {
            if (s2.equalsIgnoreCase(s)) {
                return true;
            }
        }
        return false;
    }

}
