package by.devincubator;

import by.devincubator.exception.ConstructorNotFoundException;
import by.devincubator.exception.TooManyConstructorsException;
import by.devincubator.testEnvironment.AInterface;
import by.devincubator.testEnvironment.AInterfaceImplConstructorNotFoundException;
import by.devincubator.testEnvironment.AInterfaceTooManyConstructorsException;
import org.junit.Test;

public class InjectorImplTestBind {


    @Test(expected = ConstructorNotFoundException.class)
    public void constructorNotFoundExceptionTest() {
        Injector injector = new InjectorImpl();
        injector.bind(AInterface.class, AInterfaceImplConstructorNotFoundException.class);
    }

    @Test(expected = TooManyConstructorsException.class)
    public void tooManyConstructorsExceptionTest() {
        Injector injector = new InjectorImpl();
        injector.bind(AInterface.class, AInterfaceTooManyConstructorsException.class);
    }




}