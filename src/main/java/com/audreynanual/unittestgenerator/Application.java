package com.audreynanual.unittestgenerator;

import picocli.CommandLine;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application implements Runnable {

    // The @Mixin annotation is used to add the CommandLineOptions object to the Application object
    @CommandLine.Mixin
    private CommandLineOptions options;

    public static void main(String[] args) {
        // Call the execute method of the CommandLine object
        int exitCode = new CommandLine(new Application()).execute(args);
        System.exit(exitCode);
    }

    // The run method is called when the command line options are parsed
    @Override
    public void run() {
        // Call the run method of the CommandLineOptions object
        options.run();
    }
}
