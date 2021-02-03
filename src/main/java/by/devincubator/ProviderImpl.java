package by.devincubator;

public class ProviderImpl<T> implements Provider<T> {

    T t;

    public ProviderImpl(T t) {
        this.t = t;
    }

    @Override
    public T getInstance() {
        return t;
    }
}
