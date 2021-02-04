package by.devincubator.testEnvironment;

import by.devincubator.annotation.Inject;

public class AInterfaceTooManyConstructorsException implements AInterface {


    @Inject
    public AInterfaceTooManyConstructorsException(BInterface bInterface1, BInterface bInterface2) {
    }

    @Inject
    public AInterfaceTooManyConstructorsException(BInterface bInterface) {
    }

    @Override
    public void testA() {

    }
}
