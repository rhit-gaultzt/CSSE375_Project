package TestClasses;

public class SwitchStatementClass {
    public int doSwitch(String value) {
        switch (value) {
            case "A":
                return 1;
            case "B":
                return 2;
            default:
                return 3;
        }
    }
}
