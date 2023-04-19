package com.audreynanual.unittestgenerator;

import picocli.CommandLine;

@CommandLine.Command(name = "test-generator", mixinStandardHelpOptions = true,
        version = "1.0", description = "Generates test code using OpenAI API.")
public class Application {
    
    public static void main(String[] args) {
                
        int exitCode = new CommandLine(new TestGeneratorCLI()).execute(args);
        System.exit(exitCode);
    }
}
