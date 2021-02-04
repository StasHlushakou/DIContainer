package by.devincubator;

import by.devincubator.exception.BindingNotFoundException;
import by.devincubator.testEnvironment.AInterface;
import by.devincubator.testEnvironment.AInterfaceImplAnnotationInject;
import by.devincubator.testEnvironment.BInterface;
import by.devincubator.testEnvironment.BInterfaceImpl;
import org.junit.Test;
import static org.junit.Assert.*;

public class InjectorImplTestGetProvider {


    @Test(expected = BindingNotFoundException.class)
    public void bindingNotFoundExceptionTest() {
        Injector injector = new InjectorImpl();
        injector.bind(BInterface.class, BInterfaceImpl.class);
        Provider<BInterface> bInterfaceProvider = injector.getProvider(BInterface.class);
    }

    @Test(expected = BindingNotFoundException.class)
    public void BindingNotFoundExceptionSingletonTest() {
        Injector injector = new InjectorImpl();
        injector.bindSingleton(BInterface.class, BInterfaceImpl.class);
        Provider<BInterface> bInterfaceProvider = injector.getProvider(BInterface.class);
    }

    @Test
    public void nullProvider() {
        Injector injector = new InjectorImpl();
        Provider<BInterface> testProvider = injector.getProvider(BInterface.class);
        assertNull(testProvider);
    }

    @Test
    public void testGetProvider()     {
        Injector injector = new InjectorImpl();
        injector.bindSingleton(AInterface.class, AInterfaceImplAnnotationInject.class);
        injector.bind(BInterface.class, BInterfaceImpl.class);
        Provider<AInterface> aInterfaceProvider1 = injector.getProvider(AInterface.class);
        Provider<AInterface> aInterfaceProvider2 = injector.getProvider(AInterface.class);
        Provider<BInterface> bInterfaceProvider1 = injector.getProvider(BInterface.class);
        Provider<BInterface> bInterfaceProvider2 = injector.getProvider(BInterface.class);

        assertNotNull(aInterfaceProvider1);
        assertNotNull(bInterfaceProvider1);

        assertNotNull(aInterfaceProvider1.getInstance());
        assertNotNull(bInterfaceProvider1.getInstance());

        assertSame(aInterfaceProvider1, aInterfaceProvider2);
        assertSame(bInterfaceProvider1.getInstance().getaInterface(), bInterfaceProvider2.getInstance().getaInterface());
        assertNotSame(bInterfaceProvider1.getInstance(), bInterfaceProvider2.getInstance());

    }








}