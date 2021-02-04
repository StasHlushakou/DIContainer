package by.devincubator;

import by.devincubator.exception.BindingNotFoundException;
import by.devincubator.testEnvironment.BInterface;
import by.devincubator.testEnvironment.BInterfaceImpl;
import org.junit.Test;
import static org.junit.Assert.*;

public class InjectorImplTestGetProvider {


    @Test(expected = BindingNotFoundException.class)
    public void bindingNotFoundExceptionTest() {
        Injector injector = new InjectorImpl();
        injector.bind(BInterface.class, BInterfaceImpl.class);
        Provider<BInterface> testProvider = injector.getProvider(BInterface.class); //получаем инстанс класса из инжектора
    }

    @Test
    public void nullProvider() {
        Injector injector = new InjectorImpl();
        Provider<BInterface> testProvider = injector.getProvider(BInterface.class); //получаем инстанс класса из инжектора
        assertNull(testProvider);
    }


}