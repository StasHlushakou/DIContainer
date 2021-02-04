package by.devincubator;

import by.devincubator.annotation.Inject;
import by.devincubator.exception.BindingNotFoundException;
import by.devincubator.exception.ConstructorNotFoundException;
import by.devincubator.exception.TooManyConstructorsException;
import java.lang.reflect.Constructor;
import java.util.*;

public class InjectorImpl implements Injector {

    private Map <Class, Class> bindingMap = new HashMap();
    private Map <Class, Class> singletonMap = new HashMap();
    private Map <Class, Provider> SingletonProviderMap = new HashMap();

    public synchronized <T> Provider<T> getProvider(Class<T> type) throws BindingNotFoundException {

        if (SingletonProviderMap.containsKey(type)){
            return SingletonProviderMap.get(type);
        }

        Provider provider = null;
        boolean isSingletone;
        Class impl;

        if (bindingMap.containsKey(type)){
            impl =  bindingMap.get(type);
            isSingletone = false;
        } else if (singletonMap.containsKey(type)) {
            impl =  singletonMap.get(type);
            isSingletone = true;
        } else {
            return null;
        }

        Constructor neededConstructor = findAnnotateInjectConstructorOrDefaultConstructor(impl);
        List<Object> argsList = new ArrayList();
        for (Class parameterType : neededConstructor.getParameterTypes()) {
            if (getProvider(parameterType) == null) throw new BindingNotFoundException("Missing binding for " + parameterType.getTypeName());
            argsList.add(getProvider(parameterType).getInstance());
        }
        try {
            provider = new ProviderImpl(neededConstructor.newInstance(argsList.toArray()));
        } catch (ReflectiveOperationException e) {
            e.printStackTrace();
        }

        if(isSingletone) SingletonProviderMap.put(type, provider);

        return provider;
    }

    public <T> void bind(Class<T> intf, Class<? extends T> impl) throws TooManyConstructorsException, ConstructorNotFoundException {
        findAnnotateInjectConstructorOrDefaultConstructor(impl);
        bindingMap.put(intf, impl);
    }

    public <T> void bindSingleton(Class<T> intf, Class<? extends T> impl) throws TooManyConstructorsException, ConstructorNotFoundException {
        findAnnotateInjectConstructorOrDefaultConstructor(impl);
        singletonMap.put(intf, impl);
    }

    private static <T> Constructor findAnnotateInjectConstructorOrDefaultConstructor(Class<? extends T> impl) throws TooManyConstructorsException, ConstructorNotFoundException{

        int constructorWithAnnotationInjectCounter = 0;
        Constructor returnedConstructor = null;

        for (Constructor constructor : impl.getConstructors()) {
            if (constructor.isAnnotationPresent(Inject.class)){
                returnedConstructor = constructor;
                constructorWithAnnotationInjectCounter++;
                if (constructorWithAnnotationInjectCounter > 1) throw new TooManyConstructorsException("The class "+ impl.getName() +" have more than one constructor with @Inject annotation");
            }
        }

        if (constructorWithAnnotationInjectCounter == 1 ){
            return returnedConstructor;
        } else {
            try {
                returnedConstructor = impl.getConstructor();
            } catch (NoSuchMethodException e) {
                throw new ConstructorNotFoundException("The class "+ impl.getName() +" does not have a constructor with @Inject annotation or default constructor");
            }
        }

        return returnedConstructor;
    }

}
