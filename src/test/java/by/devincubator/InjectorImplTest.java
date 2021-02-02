package by.devincubator;

import org.junit.Test;

public class InjectorImplTest {

    @Test
    public void getProvider() {
    }

    @Test
    public void bind() {
    }

    @Test
    public void bindSingleton() {
    }

    /*
    @Test
    public void testExistingBinding()     {
        Injector injector = new InjectorImpl(); //создаем имплементацию инжектора
        injector.bind(EventDAO.class, InMemoryEventDAOImpl.class); //добавляем в инжектор реализацию интерфейса
        Provider<EventDAO> daoProvider = injector.getProvider(EventDAO.class); //получаем инстанс класса из инжектора
        assertNotNull(daoProvider);
        assertNotNull(daoProvider.getInstance());
        assertSame(InMemoryEventDAOImpl.class, daoProvider.getInstance().getClass());
    }

     */


}