package ru.ifmo.springdi;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Пример использования внедрения зависимостей Spring DI. Параметры бина render
 * читаются из файла app-context.xml. Вывод сообщения осуществляется через вызов
 * метода render() класса StandardOutMessageRenderer. Класс MessageProvider
 * подключается автоматически как зависимый. Чтобы информировать Spring о
 * требовании DI, мы используем атрибут пространства имен p. Атрибут
 * p:messageProvider-ref="provider" сообщает Spring, что свойство
 * messageProvider бина должно быть внедрено с помощью другого бина. Применение
 * DI не требует вносить изменения в классы, которые связаны вместе с
 * использованием Spring.
 */
public class HelloWorldSpringDI {

    public static void main(String[] args) {

        // Initialize Spring application context
        ApplicationContext ctx = new ClassPathXmlApplicationContext("app-context.xml");

        MessageRenderer mr = ctx.getBean("renderer", MessageRenderer.class);
        mr.render();

    }
}
