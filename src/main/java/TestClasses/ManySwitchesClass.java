package TestClasses;

public class ManySwitchesClass {
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

    public String doSwitchIf(int x) {
        String result = "";
        if (x == 0) {
            result = "A";
        } else if (x == 1) {
            result = "B";
        } else if (x == 2) {
            result = "C";
        } else if (x == 3) {
            result = "D";
        }

        return result;
    }

    public String doSwitchBoth(int x) {
        String result = "";
        if (x == 0) {
            result = "A";
        } else if (x == 1) {
            result = "B";
        } else if (x == 2) {
            result = "C";
        } else if (x == 3) {
            result = "D";
        }

        switch (x) {
            case 0:
                return "A";
            case 1:
                return "B";
            default:
                return "C";
        }
    }




}
