package TestClasses;

public class DoubleCheckLockSingleton {
    private volatile static DoubleCheckLockSingleton uniqueInstance;

    private DoubleCheckLockSingleton() {
    }

    public static DoubleCheckLockSingleton getInstance() {
        if (uniqueInstance == null) {
            synchronized (DoubleCheckLockSingleton.class) {
                if (uniqueInstance == null) {
                    return new DoubleCheckLockSingleton();
                }
            }
        }
        return uniqueInstance;
    }
}
