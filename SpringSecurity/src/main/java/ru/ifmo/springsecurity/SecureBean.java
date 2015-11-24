package ru.ifmo.springsecurity;

/**
 * Интерфейс защищаемого бина.
 */
public interface SecureBean {
    public String getAdminMessage();
    public String getUserMessage();
}
