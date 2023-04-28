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

// This class is used to parse command line options
@Command(name = "unittestgenerator", mixinStandardHelpOptions = true, version = "1.0",
        description = "Generates unit tests for a given programming language and function")
public class CommandLineOptions implements Runnable {

    // The @Option annotation is used to specify command line options
    @Option(names = {"-l", "--language"}, required = false, description = "Programming language used") // The required flag is set to false because the default value is "java"
    public String language = "java";

    @Option(names = {"-f", "--function"}, required = false, description = "Function to generate unit tests for") // The required flag is set to false because the default value is "public int sum(int a, int b) { return a + b; }"
    public String function = "public int sum(int a, int b) { return a + b; }";

    // The run method is called when the command line options are parsed
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
