// This file will define the command structure of the CLI application.
// It will use the Picocli library to define the options and parameters that the user can specify when running the application.
// For example, the user may specify the function or module to be tested, the testing framework to use, and the input and output values for each test case.
// The Command class will define the structure of the command, and Picocli will handle the parsing of the command-line arguments.

package com.audreynanual.unittestgenerator;

import picocli.CommandLine.Option;


// In this file, we define a CommandOptions class with several @Option annotations that correspond to the
// various command-line options we defined in the @Command annotation in the Main class. 
public class CommandOptions {

    // The following fields will define the command-line options and parameters that the user can specify when running the application.
    @Option(names = {"-f", "--function"}, required = true, description = "Name of function or module to be tested")
    private String functionName;

    @Option(names = {"-i", "--input"}, required = true, description = "Input parameter(s) for function or module (comma-separated)")
    private String inputParameters;

    @Option(names = {"-o", "--output"}, required = true, description = "Expected output(s) for function or module (comma-separated)")
    private String expectedOutputs;

    @Option(names = {"-t", "--testframework"}, defaultValue = "JUnit5", description = "Testing framework to use (default: JUnit5)")
    private String testFramework;

    // The ComandOptions class also has getters and setters for each of the options,
    // which will allow us to access the option values from the TestGenerator class.
    public String getFunctionName() {
        return functionName;
    }

    public void setFunctionName(String functionName) {
        this.functionName = functionName;
    }

    public String getInputParameters() {
        return inputParameters;
    }

    public void setInputParameters(String inputParameters) {
        this.inputParameters = inputParameters;
    }

    public String getExpectedOutputs() {
        return expectedOutputs;
    }

    public void setExpectedOutputs(String expectedOutputs) {
        this.expectedOutputs = expectedOutputs;
    }

    public String getTestFramework() {
        return testFramework;
    }

    public void setTestFramework(String testFramework) {
        this.testFramework = testFramework;
    }
}
