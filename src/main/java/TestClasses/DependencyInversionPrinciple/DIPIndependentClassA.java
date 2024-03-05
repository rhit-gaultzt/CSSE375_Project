package TestClasses.DependencyInversionPrinciple;

public class DIPIndependentClassA {
    public int methodIndependentA() {
        int foo = 1;
        return foo;
    }

    public String methodIndependentB() {
        String a = "a";
        for (int index = 0; index < 10; index++) {
            a += "a";
        }
        return a;
    }
}


