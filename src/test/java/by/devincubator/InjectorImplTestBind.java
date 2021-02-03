package by.devincubator;

import by.devincubator.exception.ConstructorNotFoundException;
import by.devincubator.exception.TooManyConstructorsException;
import by.devincubator.test.A;
import by.devincubator.test.B;
import by.devincubator.test.IA;
import by.devincubator.test.IB;
import by.devincubator.testEnvironment.bind.AnnotationInjectTestInterfaceImpl;
import by.devincubator.testEnvironment.bind.ConstructorNotFoundExceptionTestInterfaceImpl;
import by.devincubator.testEnvironment.bind.TestInterface;
import by.devincubator.testEnvironment.bind.TooManyConstructorsExceptionTestInterfaceImpl;
import org.junit.Test;

import static org.junit.Assert.*;

public class InjectorImplTestBind {


    @Test(expected = ConstructorNotFoundException.class)
    public void constructorNotFoundExceptionTest() {
        Injector injector = new InjectorImpl();
        injector.bind(TestInterface.class, ConstructorNotFoundExceptionTestInterfaceImpl.class);
    }

    @Test(expected = TooManyConstructorsException.class)
    public void tooManyConstructorsExceptionTest() {
        Injector injector = new InjectorImpl();
        injector.bind(TestInterface.class, TooManyConstructorsExceptionTestInterfaceImpl.class);
    }




}