package Data;

import java.io.InputStream;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class InputReader {
    private Scanner scanner;

    public InputReader() {
        this.scanner = new Scanner(System.in);
    }

    public InputReader(InputStream input) {
        this.scanner = new Scanner(input);
    }

    public String nextLine() {
        try {
            return scanner.nextLine();
        } catch (NoSuchElementException e) {
            return null;
        }
    }

    public void close() {
        scanner.close();
    }
}
