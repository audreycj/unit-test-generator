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

        // Get the programming language and function from the user
        String language;
        String function;

        if(args.length > 0) { // If the user has provided arguments, use those
            language = args[0]; // The first argument is the language
            function = args[1]; // The second argument is the function

        } else { // Otherwise, prompt the user for input
            Scanner scanner = new Scanner(System.in);
            System.out.print(("Programming language used: "));
            language = scanner.nextLine();
            System.out.println("\nFunction you want to test: ");
            function = scanner.nextLine();
        }

        // Create a request object to send to the OpenAI API
        ObjectMapper objectMapper = new ObjectMapper();
        ApiRequest apiRequest = new ApiRequest("text-davinci-003", "Generate unit test for this "
                + language + " function:\n\n" + function + "\n\nInclude comments for each unit test.",
                0.3, 4000);
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
        var response = client.send(request, HttpResponse.BodyHandlers.ofString()); // Send the request to the OpenAI API

        if (response.statusCode() == 200) { // If the request was successful, print the response
            ApiResponse apiResponse = objectMapper.readValue(response.body(), ApiResponse.class); // Parse the response
            String answer = apiResponse.choices()[apiResponse.choices().length - 1].text(); // Get the last choice (assuming it's the best)

            if(!answer.isEmpty()) { // If the answer is not empty, print it
                System.out.print(answer);

        } else { // Otherwise, print the status code and body
            System.out.println(response.statusCode());
            System.out.println(response.body());
        }
    }
}}