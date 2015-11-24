package ru.ifmo.javawebapp;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * В классе реализованы основные операции с контакт листом.
 * @author Anton Skshidlevsky
 * @see http://www.h2database.com/html/quickstart.html 
 */
public class ContactList {
    
    private static final String dbUrl = "jdbc:h2:~/contacts";

    /**
     * Конструктор класса, регистрирует драйвер БД.
     */
    public ContactList() {
        try {
            Class.forName("org.h2.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ContactList.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Вспомогательный метод для закрытия соединений с БД.
     * @param closeable 
     */
    private static void closeQuietly(Connection closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (SQLException ex) {
                // ignore
            }
        }
    }
    
    /**
     * Создать БД.
     * @return в случае успеха возвражает пустой JSON объект, иначе null.
     */
    public JSONObject createDB() {
        Connection conn = null;
        JSONObject result = null;
        try {
            conn = DriverManager.getConnection(dbUrl);
            
            Statement st = conn.createStatement();
            st.execute("create table people(id INT PRIMARY KEY AUTO_INCREMENT, fio varchar(255), phone varchar(255), email varchar(255))");
            
            result = new JSONObject();
        } catch (SQLException ex) {
            Logger.getLogger(ContactList.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeQuietly(conn);
        }
        return result;
    }

    /**
     * Получить список контактов.
     * @return массив объектов JSON.
     */
    public JSONArray list() {
        Connection conn = null;
        JSONArray list = null;
        try {
            conn = DriverManager.getConnection(dbUrl);
            
            Statement st = conn.createStatement();
            ResultSet result = st.executeQuery("SELECT * FROM PEOPLE");
            
            list = new JSONArray();
            while (result.next()) {
                Contact c = new Contact(result.getLong("ID"),
                        result.getString("FIO"),
                        result.getString("PHONE"),
                        result.getString("EMAIL"));
                list.add(c);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ContactList.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeQuietly(conn);
        }
        return list;
    }

    /**
     * Добавить контакт.
     * @param c контакт, в качестве id любое значение или null.
     * @return в случае успеха возвражает id контакта в формате JSON, иначе null.
     */
    public JSONObject add(Contact c) {
        Connection conn = null;
        JSONObject result = null;
        try {
            conn = DriverManager.getConnection(dbUrl);
            
            String q = "INSERT INTO PEOPLE(fio, phone, email) VALUES(?,?,?)";
            PreparedStatement st = conn.prepareStatement(q);

            st.setString(1, c.getFIO());
            st.setString(2, c.getPhone());
            st.setString(3, c.getEmail());
            st.execute();
            
            long id = -1;
            ResultSet rs = st.getGeneratedKeys();
            if (rs.next()) {
                id = rs.getInt(1);
            }
            
            result = new JSONObject();
            result.put("id", id);
        } catch (SQLException ex) {
            Logger.getLogger(ContactList.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeQuietly(conn);
        }
        return result;
    }
    
    /**
     * Удалить контакт.
     * @param id идентификатор контакта.
     * @return в случае успеха возвражает пустой JSON объект, иначе null.
     */
    public JSONObject remove(String id) {
        Connection conn = null;
        JSONObject result = null;
        try {
            conn = DriverManager.getConnection(dbUrl);

            String q = "DELETE FROM PEOPLE WHERE ID = ?";
            PreparedStatement st = conn.prepareStatement(q);

            st.setString(1, id);
            st.execute();
            
            result = new JSONObject();
        } catch (SQLException ex) {
            Logger.getLogger(ContactList.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeQuietly(conn);
        }
        return result;
    }
    
}
