package ru.ifmo.springscheduler;

import java.util.concurrent.Future;

/**
 * Интерфейс асинхронного сервиса.
 */
public interface AsyncService {

    public void asyncTask();

    public Future<String> asyncWithReturn(String name);
}
