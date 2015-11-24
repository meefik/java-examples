package ru.ifmo.javawebapp;

import org.json.simple.JSONAware;
import org.json.simple.JSONObject;

/**
 * Класс описывает контакт (JSON формат).
 * @author Anton Skshidlevsky
 * @see https://code.google.com/p/json-simple/
 */
public class Contact implements JSONAware {

    private Long id;
    private String fio;
    private String phone;
    private String email;

    /**
     * Конструктор контакта.
     * @param id идентификатор контакта.
     * @param fio ФИО.
     * @param phone телефон.
     * @param email электронная почта.
     */
    public Contact(Long id, String fio, String phone, String email) {
        this.id = id;
        this.fio = fio;
        this.phone = phone;
        this.email = email;
    }
    
    public Long getId() {
        return id;
    }

    public String getFIO() {
        return fio;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    /**
     * Преобразует данные контакта в JSON строку.
     * @return данные контакта в формате JSON.
     */
    @Override
    public String toJSONString() {
        StringBuilder sb = new StringBuilder();

        sb.append("{");

        sb.append("\"").append(JSONObject.escape("id")).append("\"");
        sb.append(":");
        sb.append(id);

        sb.append(",");

        sb.append("\"").append("fio").append("\"");
        sb.append(":");
        sb.append("\"").append(JSONObject.escape(fio)).append("\"");

        sb.append(",");

        sb.append("\"").append(JSONObject.escape("phone")).append("\"");
        sb.append(":");
        sb.append("\"").append(JSONObject.escape(phone)).append("\"");

        sb.append(",");

        sb.append("\"").append(JSONObject.escape("email")).append("\"");
        sb.append(":");
        sb.append("\"").append(JSONObject.escape(email)).append("\"");

        sb.append("}");

        return sb.toString();
    }
}
