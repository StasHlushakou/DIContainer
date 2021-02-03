package by.devincubator;

public interface Injector {
    <T> Provider<T> getProvider(Class<T> type); //получение по классу интерфейса инстанса класса со всеми иньекциями
    <T> void bind(Class<T> intf, Class<? extends T> impl); //регистрация байндинга по классу интерфейса и его реализации
    <T> void bindSingleton(Class<T> intf, Class<? extends T> impl); //регистрация синглтон класса
}

