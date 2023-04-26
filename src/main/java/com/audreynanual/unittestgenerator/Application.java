package com.audreynanual.unittestgenerator;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class Application {
    public static void main(String[] args) throws IOException, InterruptedException {

        // TODO: Accept user input for the function they want to generate a unit test for
        System.out.println("==================== CLI Unit Test Generator ====================");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the function you want to generate a unit test for: ");
        String searchString = scanner.nextLine();

        // %s is a string format specifier that serves as a placeholder for a value that will be substituted at runtime.
        // Specifically, it is used as a placeholder for the value of the searchString variable, which is a string.
        String input = """
                {
                  "model": "text-davinci-003",
                  "prompt": "%s",
                  "temperature": 0.3,
                  "max_tokens": 100
                }
                """.formatted(searchString);

        // TODO: Create a request to the OpenAI API
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.openai.com/v1/completions"))
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + System.getenv("OPENAI_API_KEY"))
                .POST(HttpRequest.BodyPublishers.ofString(input))
                .build();

        // TODO: Create a client to send the request to the OpenAI API
        HttpClient client = HttpClient.newHttpClient();
        var response = client.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println(response.statusCode());
        System.out.println(response.body());

        // TODO: Use the OpenAI API to generate a unit test for the function
    }
}
