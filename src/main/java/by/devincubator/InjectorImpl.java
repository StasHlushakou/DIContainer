package by.devincubator;

public class InjectorImpl implements Injector {

    public <T> Provider<T> getProvider(Class<T> type) {
        return null;
    }

    public <T> void bind(Class<T> intf, Class<? extends T> impl) {

    }

    public <T> void bindSingleton(Class<T> intf, Class<? extends T> impl) {

    }
}
