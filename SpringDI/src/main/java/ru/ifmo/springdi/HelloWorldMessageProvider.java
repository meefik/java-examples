package ru.ifmo.springdi;

/**
 * Класс реализует интерфейс MessageProvider.
 */
public class HelloWorldMessageProvider implements MessageProvider {

    private String msg;
    private int code;

    public HelloWorldMessageProvider() {
        this.msg = "Hello World!";
    }

    /**
     * Конструктор получает текст сообщения из конфигурационного файла.
     *
     * @param msg
     */
    public HelloWorldMessageProvider(String msg) {
        this.msg = msg;
    }

    /**
     * Обязательный метод для установки значения поля code из конфигурационного
     * файла.
     *
     * @param code
     */
    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return msg + "\nCode: " + String.valueOf(code);
    }
}
