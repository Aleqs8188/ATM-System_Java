package pl.oleksii.FunctionalityInterface;

import pl.oleksii.ClientSettings.Client;

import java.io.IOException;

public interface Functionality<T, R> {
    default void init() {}

    default R init(T t) throws IOException {
        return null;
    }

    default void init(Client client) {}
    default void init(Client client, Double sum) throws InterruptedException, IOException {
    }
}
