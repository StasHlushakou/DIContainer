package by.devincubator;

import by.devincubator.annotation.Inject;
import by.devincubator.exception.BindingNotFoundException;
import by.devincubator.exception.ConstructorNotFoundException;
import by.devincubator.exception.TooManyConstructorsException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class InjectorImpl implements Injector {

    private Map <Class, Class> bindingMap = new HashMap();
    private Map <Class, Provider> singletonMap = new HashMap();

    public <T> Provider<T> getProvider(Class<T> type) throws BindingNotFoundException {

        Class impl = findBind(type);
        if (impl == null) return null;

        Constructor neededConstructor = findAnnotateInjectConstructorOrDefaultConstructor(impl);
        List<Object> argsList = new ArrayList();

        for (Class parameterType : neededConstructor.getParameterTypes()) {
            if (getProvider(parameterType) == null) throw new BindingNotFoundException();
            argsList.add(getProvider(parameterType).getInstance());
        }

        Provider provider = null;
        try {
            provider = new ProviderImpl(neededConstructor.newInstance(argsList.toArray()));
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        return provider;

    }

    public <T> void bind(Class<T> intf, Class<? extends T> impl) throws TooManyConstructorsException, ConstructorNotFoundException {

        findAnnotateInjectConstructorOrDefaultConstructor(impl);

        bindingMap.put(intf, impl);
    }

    public <T> void bindSingleton(Class<T> intf, Class<? extends T> impl) throws TooManyConstructorsException, ConstructorNotFoundException {


    }

    private <T> Class findBind(Class<T> type) {
        return bindingMap.get(type);
    }

    private static <T> Constructor findAnnotateInjectConstructorOrDefaultConstructor(Class<? extends T> impl) throws TooManyConstructorsException, ConstructorNotFoundException{

        int constructorWithAnnotationInjectCounter = 0;
        Constructor returnedConstructor = null;

        for (Constructor constructor : impl.getDeclaredConstructors()) {
            if (constructor.isAnnotationPresent(Inject.class)){
                returnedConstructor = constructor;
                constructorWithAnnotationInjectCounter++;
                if (constructorWithAnnotationInjectCounter > 1) throw new TooManyConstructorsException();
            }
        }

        if (constructorWithAnnotationInjectCounter == 1 ){
            return returnedConstructor;
        } else {
            try {
                returnedConstructor = impl.getDeclaredConstructor();
            } catch (NoSuchMethodException e) {
                throw new ConstructorNotFoundException();
            }
        }

        return returnedConstructor;
    }

}
