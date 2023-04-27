package com.audreynanual.unittestgenerator;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class Application {
    public static void main(String[] args) throws IOException, InterruptedException {

        // Accept user input for the function they want to generate a unit test for
        System.out.println("==================== CLI Unit Test Generator ====================\n");
        Scanner scanner = new Scanner(System.in);
        System.out.print(("Programming language used: "));
        String language = scanner.nextLine();
        System.out.println("\nFunction you want to test: ");
        String function = scanner.nextLine();

        // Create a request object to send to the OpenAI API
        ObjectMapper objectMapper = new ObjectMapper();
        ApiRequest apiRequest = new ApiRequest("text-davinci-003", "Create unit tests for this "
                + language + " function: " + function + "\n Include comments for each unit test.", 0.3, 2000);
        String input = objectMapper.writeValueAsString(apiRequest);

        // Create a request to send to the OpenAI API
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.openai.com/v1/completions"))
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + System.getenv("OPENAI_API_KEY"))
                .POST(HttpRequest.BodyPublishers.ofString(input))
                .build();

        // Send the request to the OpenAI API
        HttpClient client = HttpClient.newHttpClient();
        var response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200) {
            ApiResponse apiResponse = objectMapper.readValue(response.body(), ApiResponse.class);
            String answer = apiResponse.choices()[apiResponse.choices().length - 1].text();
            if(!answer.isEmpty()) {
                System.out.println(answer);
        } else {
            System.out.println(response.statusCode());
            System.out.println(response.body());
        }
    }
}}