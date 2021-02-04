package by.devincubator;

import by.devincubator.testEnvironment.AInterfaceDefaultConstructorTest;
import org.junit.Test;

import static org.junit.Assert.*;

public class ProviderImplTest {

    @Test
    public void providerImplTest() {
        Object object= new Object();
        Provider<Object> provider = new ProviderImpl(object);
        assertSame(object, provider.getInstance());
    }

}