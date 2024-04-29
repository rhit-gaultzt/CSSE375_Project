package Presentation;

import Data.InputReader;

import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class CLIGetClasses {

    private InputReader input;
    private PrintStream output;


    public CLIGetClasses() {
        this.input = new InputReader();
        this.output = System.out;
    }

    public CLIGetClasses(InputReader input, PrintStream printStream) {
        this.input = input;
        this.output = printStream;
    }

    public String[] getClasses(String[] args, boolean doInitialPrompt) {
        if (args.length > 0) {
            return args;
        } else {
            return getClassesFromCLI(doInitialPrompt);
        }
    }

    protected String[] getClassesFromCLI(boolean doInitialPrompt) {
        List<String> classNames = new ArrayList<>();
        if (doInitialPrompt) {
            initialPrompt();
        }
        do {
            classNames.add(getClassName());
        } while (wantsToAddMoreClasses());
        return classNames.toArray(new String[classNames.size()]);
    }

    protected void initialPrompt() {
        this.output.println("Welcome to the Java Linter. You can either " +
                "specify classes as initial arguments or by specifying their paths here");
    }

    protected String getClassName() {
        this.output.println("What class would you like to lint?\n");
        return input.nextLine();
    }

    protected boolean wantsToAddMoreClasses() {
        this.output.println("Would you like to add another class? (y/n)\n");

        while(true) {
            String response = input.nextLine();
            if (response.equals("y")) {
                return true;
            } else if (response.equals("n")) {
                return false;
            } else {
                this.output.println("Please input y or n\n");
            }
        }
    }

    public void displayInvalidClass(IOException e) {
        System.out.println("\nInvalid Class/Jar Found: " + e.getMessage());
        System.out.println("Please verify paths and try again.\n");
    }
}
