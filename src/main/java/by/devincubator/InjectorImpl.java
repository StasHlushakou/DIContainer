package by.devincubator;

import by.devincubator.annotation.Inject;
import by.devincubator.exception.BindingNotFoundException;
import by.devincubator.exception.ConstructorNotFoundException;
import by.devincubator.exception.TooManyConstructorsException;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InjectorImpl implements Injector {

    private Map <Class, Class> bindingMap = new HashMap();
    private Map <Class, Class> singletonMap = new HashMap();

    public <T> Provider<T> getProvider(Class<T> type) throws BindingNotFoundException {

        Class c =  bindingMap.get(type);

        return null;

    }

    public <T> void bind(Class<T> intf, Class<? extends T> impl) throws TooManyConstructorsException, ConstructorNotFoundException {

        Constructor<?>[] constructors = impl.getDeclaredConstructors();

        int constructorWithAnnotationInjectCounter = 0;
        Constructor constructorWithAnnotationInject = null;


        for (Constructor constructor : constructors) {
            if (constructor.isAnnotationPresent(Inject.class)){
                constructorWithAnnotationInject = constructor;
                constructorWithAnnotationInjectCounter++;
                if (constructorWithAnnotationInjectCounter > 1) throw new TooManyConstructorsException();
            }
        }


        if (constructorWithAnnotationInjectCounter == 1 ){
            bindingMap.put(intf, impl);
            return;
        }


        try {
            impl.getDeclaredConstructor();
            bindingMap.put(intf, impl);
        } catch (NoSuchMethodException e) {
            throw new ConstructorNotFoundException();
        }

    }

    public <T> void bindSingleton(Class<T> intf, Class<? extends T> impl) throws TooManyConstructorsException, ConstructorNotFoundException {

    }
}
