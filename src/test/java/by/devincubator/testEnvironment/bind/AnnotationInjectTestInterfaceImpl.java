package by.devincubator.testEnvironment.bind;

import by.devincubator.annotation.Inject;

public class AnnotationInjectTestInterfaceImpl implements TestInterface {

    @Inject
    public AnnotationInjectTestInterfaceImpl(Object o) {
    }

    @Override
    public void test() {

    }
}
