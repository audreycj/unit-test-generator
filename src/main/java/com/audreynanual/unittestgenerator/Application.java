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
        System.out.println("==================== CLI Unit Test Generator ====================");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the function you want to generate a unit test for: ");
        String searchString = scanner.nextLine();

        // Create a request object to send to the OpenAI API
        ObjectMapper objectMapper = new ObjectMapper();
        ApiRequest apiRequest = new ApiRequest("text-davinci-003", searchString, 0.3, 1000);
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

        System.out.println(response.statusCode());
        System.out.println(response.body());

        // TODO: Use the OpenAI API to generate a unit test for the function
    }
}
