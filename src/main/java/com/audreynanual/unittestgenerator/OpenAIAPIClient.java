// This class encapsulates the logic for calling the OpenAI API endpoints and processing the responses.
// It can use the OpenAI Java SDK or create its own HTTP client to make the API requests.

package com.audreynanual.unittestgenerator;

import okhttp3.*; // ‘okhttp3’ is a library that provides an HTTP client for Java
import org.springframework.stereotype.Component; // ‘@Component’ is a Spring annotation that indicates that this class is a Spring component

import java.io.IOException; // ‘IOException’ is a checked exception that is thrown when an I/O operation fails

@Component
public class OpenAIAPIClient {
 
    private final OkHttpClient httpClient; // ‘OkHttpClient’ is a class that is used to make HTTP requests to the OpenAI API
    private final String openaiApiKey; // holds OpenAI API key
    private final String openaiApiUrl; // holds the base URL for the OpenAI API

    public OpenAIAPIClient() { // constructor method
        httpClient = new OkHttpClient();
        openaiApiKey = "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";
        openaiApiUrl = "https://api.openai.com/v1/completions";
    }


}