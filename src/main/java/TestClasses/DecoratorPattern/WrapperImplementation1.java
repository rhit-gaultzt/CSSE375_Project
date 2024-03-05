package TestClasses.DecoratorPattern;

public class WrapperImplementation1 extends Wrapper {

    WrapperImplementation1(ClassWrapping classWrapping) {
        super(classWrapping);
    }


    @Override
    public void method1() {
        super.method1();
    }


    @Override
    public void method2() {
//        this.classWrapping.method2();
    }


}
