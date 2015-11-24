package ru.ifmo.springdi;

/**
 * Пример Hello World разделен на отдельные компоненты. Созданы отдельные
 * интерфейсы для вывода сообщения и для отображения сообщения, добавлены
 * соответствующие реализации. Таким образом можно изменять сообщение и/или
 * способ его отображения без необходимости модифицировать главный класс.
 */
public class HelloWorldDecoupled {

    public static void main(String[] args) {
        MessageRenderer mr = new StandardOutMessageRenderer();
        MessageProvider mp = new HelloWorldMessageProvider();
        mr.setMessageProvider(mp);
        mr.render();
    }
}
