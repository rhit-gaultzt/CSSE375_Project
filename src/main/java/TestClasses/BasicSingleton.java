package TestClasses;

public class BasicSingleton {
    private static BasicSingleton uniqueInstance;

    private BasicSingleton() {
    }

    public static BasicSingleton getInstance() {
        if (uniqueInstance == null) {
            return new BasicSingleton();
        }
        return uniqueInstance;
    }
}
