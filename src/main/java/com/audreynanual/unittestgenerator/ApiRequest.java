package com.audreynanual.unittestgenerator;

// This record represents what a request looks like.
public record ApiRequest(String model, String prompt, double temperature, int max_tokens) {

}
