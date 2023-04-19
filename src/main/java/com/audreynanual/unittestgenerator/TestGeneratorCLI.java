package com.audreynanual.unittestgenerator;

import picocli.CommandLine;

@CommandLine.Command(name = "test-generator-cli", mixinStandardHelpOptions = true,
        version = "1.0", description = "Generates test code using OpenAI API.")
public class TestGeneratorCLI implements Runnable {
    
    @CommandLine.Parameters(index = "0", description = "The function or module to be tested.")
    private String function;

    @CommandLine.Option(names = {"-i", "--input"}, description = "The input parameters and expected output values for each test case.")
    private String input;

    @CommandLine.Option(names = {"-f", "--framework"}, description = "The testing framework to use.")
    private String framework;

    @Override
    public void run() {
        // Call the TestCodeGenerator to generate test code based on the user inputs
        // and display the generated code to the user
    }
}
