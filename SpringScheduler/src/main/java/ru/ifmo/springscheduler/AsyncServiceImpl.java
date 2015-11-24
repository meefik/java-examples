package ru.ifmo.springscheduler;

// http://habrahabr.ru/company/luxoft/blog/157273/
import java.util.concurrent.Future;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

/**
 * Класс реализует интерфейс AsyncService.
 */
@Service("asyncService")
public class AsyncServiceImpl implements AsyncService {

    @Async
    public void asyncTask() {

        System.out.println("Start execution of async. task");

        try {
            Thread.sleep(10000);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        System.out.println("Complete execution of async. task");

    }

    @Async
    public Future<String> asyncWithReturn(String name) {

        System.out.println("Start execution of async. task with return");

        try {
            Thread.sleep(5000);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        System.out.println("Complete execution of async. task with return");

        return new AsyncResult<String>("Hello: " + name);

    }
}
