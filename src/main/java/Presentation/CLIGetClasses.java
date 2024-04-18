package Presentation;

import Data.InputReader;

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

    public String[] getClasses(String[] args) {
        if (args.length > 0) {
            return args;
        } else {
            return getClassesFromCLI();
        }
    }

    protected String[] getClassesFromCLI() {
        List<String> classNames = new ArrayList<>();
        initialPrompt();
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
}
