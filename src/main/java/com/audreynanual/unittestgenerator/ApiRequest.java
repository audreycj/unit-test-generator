package com.audreynanual.unittestgenerator;

// This record represents what a request looks like.
public record ApiRequest(String model, String prompt, double temperature, int max_tokens) {

    // This is a constructor that takes in the values for the record.
    // It is a shorthand way of writing a constructor.
    // The constructor is used to create an instance of the record.
    // The constructor is used in the Application.java file.
}
