package com.audreynanual.unittestgenerator;

import picocli.CommandLine;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application implements Runnable {

    @CommandLine.Mixin
    private CommandLineOptions options;

    public static void main(String[] args) {
        int exitCode = new CommandLine(new Application()).execute(args);
        System.exit(exitCode);
    }

    @Override
    public void run() {
        // Call the run method of the CommandLineOptions object
        options.run();
    }
}
