package by.devincubator.testEnvironment.bind;

import by.devincubator.annotation.Inject;

public class TooManyConstructorsExceptionTestInterfaceImpl implements TestInterface {


    @Inject
    public TooManyConstructorsExceptionTestInterfaceImpl(Object object1, Object object2) {
    }

    @Inject
    public TooManyConstructorsExceptionTestInterfaceImpl(Object object) {
    }

    @Override
    public void test() {

    }
}
