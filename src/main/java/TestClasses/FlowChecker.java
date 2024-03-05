package TestClasses;

public class FlowChecker {
    public int method(boolean boolVal) {
        int number = -1;
        if (boolVal) {
            number = 1;
        }
        if (boolVal) {
            number = 9;
        }
        if (boolVal) {
            number = 3;
        }
        if (boolVal) {
            number = 5;
        }
        if (boolVal) {
            number = 5;
        }
        if (boolVal) {
            number = 3;
        }
        if (boolVal) {
            number = 1;
        }
        if (boolVal) {
            number = 2;
        }
        if (boolVal) {
            number = 8;
        }
        if (boolVal) {
            number = 4;
        }
        if (boolVal) {
            number = 0;
        }
        return number;
    }
}
