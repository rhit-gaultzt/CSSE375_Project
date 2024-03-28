package Presentation;

import java.io.InputStream;
import java.util.Scanner;

public class InputReader {
    private Scanner scanner;

    public InputReader() {
        this.scanner = new Scanner(System.in);
    }

    public InputReader(InputStream input) {
        this.scanner = new Scanner(input);
    }

    String nextLine() {
        return scanner.nextLine();
    }
}
