package TestClasses;

import Domain.Rule;
import Domain.Rules.ClassNameRule;
import Domain.Rules.ObserverPatternRule;
import Domain.Rules.SingletonRule;

public class SwitchByIfsClass {

    private String doSwitchIf(int x) {
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
}
