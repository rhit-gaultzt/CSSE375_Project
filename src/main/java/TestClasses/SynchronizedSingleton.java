package TestClasses;

public class SynchronizedSingleton {
    private static SynchronizedSingleton uniqueInstance;

    private SynchronizedSingleton() {
    }

    public static synchronized SynchronizedSingleton getInstance() {
        if (uniqueInstance == null) {
            return new SynchronizedSingleton();
        }
        return uniqueInstance;
    }
}
