package com.audreynanual.unittestgenerator;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.ExitCodeGenerator;
import org.springframework.stereotype.Component;
import picocli.CommandLine;
import picocli.CommandLine.IFactory;

@Component
public class PicoCliRunner implements CommandLineRunner, ExitCodeGenerator {
    private final CommandLineOptions cliCommand;
    private final IFactory factory;
    private int exitCode;

    public PicoCliRunner(CommandLineOptions cliCommand, IFactory factory) {
        this.cliCommand = cliCommand;
        this.factory = factory;
    }

    @Override
    public void run(String... args) throws Exception {
        exitCode = new CommandLine(cliCommand, factory)
                .setUnmatchedArgumentsAllowed(true)
                .execute(args);
    }

    @Override
    public int getExitCode() {
        return exitCode;
    }
}
