package ru.ifmo.springdi;

/**
 * Интерфейс для отображения сообщения.
 */
public interface MessageRenderer {

    public void render();

    public void setMessageProvider(MessageProvider provider);

    public MessageProvider getMessageProvider();
}
