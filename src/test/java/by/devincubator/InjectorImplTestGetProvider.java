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
        Provider<BInterface> testProvider = injector.getProvider(BInterface.class); //получаем инстанс класса из инжектора
    }

    @Test(expected = BindingNotFoundException.class)
    public void BindingNotFoundExceptionSingletonTest() {
        Injector injector = new InjectorImpl();
        injector.bindSingleton(BInterface.class, BInterfaceImpl.class);
        Provider<BInterface> testProvider = injector.getProvider(BInterface.class); //получаем инстанс класса из инжектора
    }

    @Test
    public void nullProvider() {
        Injector injector = new InjectorImpl();
        Provider<BInterface> testProvider = injector.getProvider(BInterface.class); //получаем инстанс класса из инжектора
        assertNull(testProvider);
    }

    @Test
    public void testGetProvider()     {
        Injector injector = new InjectorImpl(); //создаем имплементацию инжектора
        injector.bindSingleton(AInterface.class, AInterfaceImplAnnotationInject.class); //добавляем в инжектор реализацию интерфейса
        injector.bind(BInterface.class, BInterfaceImpl.class); //добавляем в инжектор реализацию интерфейса
        Provider<AInterface> aInterfaceProvider1 = injector.getProvider(AInterface.class); //получаем инстанс класса из инжектора
        Provider<AInterface> aInterfaceProvider2 = injector.getProvider(AInterface.class); //получаем инстанс класса из инжектора
        Provider<BInterface> bInterfaceProvider1 = injector.getProvider(BInterface.class); //получаем инстанс класса из инжектора
        Provider<BInterface> bInterfaceProvider2 = injector.getProvider(BInterface.class); //получаем инстанс класса из инжектора

        assertNotNull(aInterfaceProvider1);
        assertNotNull(bInterfaceProvider1);

        assertNotNull(aInterfaceProvider1.getInstance());
        assertNotNull(bInterfaceProvider1.getInstance());

        assertSame(aInterfaceProvider1, aInterfaceProvider2);
        assertSame(bInterfaceProvider1.getInstance().getaInterface(), bInterfaceProvider2.getInstance().getaInterface());
        assertNotSame(bInterfaceProvider1.getInstance(), bInterfaceProvider2.getInstance());

    }








}