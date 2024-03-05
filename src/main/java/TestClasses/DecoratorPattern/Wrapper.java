package TestClasses.DecoratorPattern;

public abstract class Wrapper implements ClassWrapping {

    protected ClassWrapping classWrapping;

    Wrapper(ClassWrapping classWrapping) {
        this.classWrapping = classWrapping;
    }

    @Override
    public void method1() {
        classWrapping.method1();
    }

    private void method3() {

    }

}
