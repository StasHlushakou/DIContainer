package by.devincubator;

import by.devincubator.testEnvironment.AInterface;
import by.devincubator.testEnvironment.AInterfaceImplAnnotationInject;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;

public class InjectorImplTest {

    @Test
    public void testExistingBinding()     {
        Injector injector = new InjectorImpl(); //создаем имплементацию инжектора
        injector.bind(AInterface.class, AInterfaceImplAnnotationInject.class); //добавляем в инжектор реализацию интерфейса
        Provider<AInterface> aInterfaceProvider = injector.getProvider(AInterface.class); //получаем инстанс класса из инжектора
        assertNotNull(aInterfaceProvider);
        assertNotNull(aInterfaceProvider.getInstance());
        assertSame(AInterfaceImplAnnotationInject.class, aInterfaceProvider.getInstance().getClass());
    }

}