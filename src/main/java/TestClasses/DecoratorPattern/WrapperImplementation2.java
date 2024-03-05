package TestClasses.DecoratorPattern;

public class WrapperImplementation2 extends Wrapper {

    WrapperImplementation2(ClassWrapping classWrapping) {
        super(classWrapping);
    }

    @Override
    public void method1() {
        this.classWrapping.method1();
    }


    @Override
    public void method2() {
        this.classWrapping.method2();
    }

    private void method4() {

    }

}
