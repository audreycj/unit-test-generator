// This file contains the main method and will be the entry point for the application.
// It will be responsible for parsing command-line arguments using the Picocli library and invoking the appropriate methods based on user input.
// It may also be responsible for initializing any necessary objects or services that your application requires to run.

package com.audreynanual.unittestgenerator;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import picocli.CommandLine;
import picocli.CommandLine.Command;

@SpringBootApplication // This annotation is not necessary for this class, but it is required for the TestGenerator class.
@Command(name = "UnitTestGenerator", mixinStandardHelpOptions = true,
        version = "UnitTestGenerator 1.0",
        description = "Generates unit test code based on user-specified inputs and expected outputs.")
public class Main implements Runnable { // This class implements the Runnable interface so that it can be executed by the Picocli library.

    @CommandLine.Mixin // Generates a command-line option for each field in the CommandOptions class.
    private CommandOptions commandOptions; // This field will contain the command-line options and parameters specified by the user.

    public static void main(String[] args) {

		// This method is the entry point for the application.
        int exitCode = new CommandLine(new Main()).execute(args); // This line will parse the command-line arguments and invoke the run() method.
        System.exit(exitCode); // This line will exit the application with the appropriate exit code.

		// The following code is an alternative way to parse the command-line arguments. (temporary addition)
		CommandLine commandLine = new CommandLine(new CommandOptions()); // This line will create a new CommandLine object.
		commandLine.parseArgs(args); // This line will parse the command-line arguments.
		CommandOptions commandOptions = commandLine.getCommand(); // This line will get the CommandOptions object from the CommandLine object.
    }

	// This method will be invoked by the Picocli library when the user runs the application.
    @Override
    public void run() {
        TestGenerator testGenerator = new TestGenerator(commandOptions); // This object will be responsible for generating the test code.
        testGenerator.generateTests(); // This method will invoke the OpenAI API to generate the test code.
    }

}