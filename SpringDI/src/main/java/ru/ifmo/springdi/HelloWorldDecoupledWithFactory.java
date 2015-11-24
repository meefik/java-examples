package ru.ifmo.springdi;

/**
 * Пример Hello World на основе собственной фабрики классов. Получает экземпляры
 * классов, реализующие интерфейсы MessageRender и MessageProvider. Это
 * позволяет достичь определенной гибкости в приложении, теперь мы можем менять
 * реализацию данных интерфейсов как хотим без необходимости менять код
 * основного класса.
 */
public class HelloWorldDecoupledWithFactory {

    public static void main(String[] args) {
        MessageRenderer mr = MessageSupportFactory.getInstance().getMessageRenderer();
        MessageProvider mp = MessageSupportFactory.getInstance().getMessageProvider();
        mr.setMessageProvider(mp);
        mr.render();
    }
}
