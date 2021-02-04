package by.devincubator;

import by.devincubator.exception.ConstructorNotFoundException;
import by.devincubator.exception.TooManyConstructorsException;
import by.devincubator.testEnvironment.AInterface;
import by.devincubator.testEnvironment.AInterfaceImplAnnotationInject;
import by.devincubator.testEnvironment.AInterfaceImplConstructorNotFoundException;
import by.devincubator.testEnvironment.AInterfaceTooManyConstructorsException;
import org.junit.Test;
import static org.junit.Assert.*;

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

    @Test
    public void testExistingBinding()     {
        Injector injector = new InjectorImpl();
        injector.bind(AInterface.class, AInterfaceImplAnnotationInject.class);
        Provider<AInterface> aInterfaceProvider1 = injector.getProvider(AInterface.class);
        Provider<AInterface> aInterfaceProvider2 = injector.getProvider(AInterface.class);
        assertNotNull(aInterfaceProvider1);
        assertNotNull(aInterfaceProvider1.getInstance());
        assertNotSame(aInterfaceProvider1, aInterfaceProvider2);
    }

}