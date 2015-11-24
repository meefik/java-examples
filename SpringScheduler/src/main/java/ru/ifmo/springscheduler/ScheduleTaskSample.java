package ru.ifmo.springscheduler;

import org.springframework.context.support.GenericXmlApplicationContext;

/**
 * Демонстрация работы планировщика.
 */
public class ScheduleTaskSample {

    public static void main(String[] args) {

        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:app-context.xml");
        ctx.refresh();

        while (true) {
        }
    }
}
