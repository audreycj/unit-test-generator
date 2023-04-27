package com.audreynanual.unittestgenerator;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Command(name = "unittestgenerator", mixinStandardHelpOptions = true, version = "1.0",
        description = "Generates unit tests for a given programming language and function")
public class CommandLineOptions implements Runnable {

    @Option(names = {"-l", "--language"}, required = true, description = "Programming language used")
    public String language;

    @Option(names = {"-f", "--function"}, required = true, description = "Function to generate unit tests for")
    public String function;

    public void run() {
        // Create a request object to send to the OpenAI API
        ObjectMapper objectMapper = new ObjectMapper();
        ApiRequest apiRequest = new ApiRequest("text-davinci-003", "Generate unit test for this "
                + language + " function:\n\n" + function + "\n\nInclude comments for each unit test.",
                0.3, 4000);
        String input;
        try {
            input = objectMapper.writeValueAsString(apiRequest);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        // Create a request to send to the OpenAI API
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.openai.com/v1/completions"))
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + System.getenv("OPENAI_API_KEY"))
                .POST(HttpRequest.BodyPublishers.ofString(input))
                .build();

        // Send the request to the OpenAI API
        HttpClient client = HttpClient.newHttpClient();
        try {
            var response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                ApiResponse apiResponse = objectMapper.readValue(response.body(), ApiResponse.class);
                String answer = apiResponse.choices()[apiResponse.choices().length - 1].text();

                if (!answer.isEmpty()) {
                    System.out.print(answer);
                }
            } else {
                System.out.println(response.statusCode());
                System.out.println(response.body());
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
