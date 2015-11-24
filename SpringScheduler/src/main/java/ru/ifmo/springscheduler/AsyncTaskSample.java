package ru.ifmo.springscheduler;

import java.util.concurrent.Future;

import org.springframework.context.support.GenericXmlApplicationContext;

/**
 * Демонстрация работы асинхронного планировщика.
 */
public class AsyncTaskSample {

    public static void main(String[] args) {

        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:async-app-context.xml");
        ctx.refresh();

        AsyncService asyncService = ctx.getBean("asyncService", AsyncService.class);

        for (int i = 0; i < 5; i++) {
            asyncService.asyncTask();
        }

        Future<String> result1 = asyncService.asyncWithReturn("Task 1");
        Future<String> result2 = asyncService.asyncWithReturn("Task 2");
        Future<String> result3 = asyncService.asyncWithReturn("Task 3");

        try {
            Thread.sleep(6000);

            System.out.println("Result1: " + result1.get());
            System.out.println("Result2: " + result2.get());
            System.out.println("Result3: " + result3.get());
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
}
