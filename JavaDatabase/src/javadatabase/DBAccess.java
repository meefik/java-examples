package javadatabase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Класс реализует интерфейс для доступа к БД
 */
public class DBAccess {
    final private static String driverName = "oracle.jdbc.driver.OracleDriver";
    private String dburl;
    private String user;
    private String passwd;
    private Connection conn;
    
    /**
     * Конструктор класса DBAccess
     * @param dburl строка подключения к базе данных, 
     * например: jdbc:oracle:thin:@oraclexe.academicmt.ru:1521:xe
     * @param user имя пользователя
     * @param passwd пароль пользователя
     */
    public DBAccess(String dburl, String user, String passwd) {
	this.dburl = dburl;
	this.user = user;
	this.passwd = passwd;
    }

    /**
     * Метод для установления соединения с БД
     * @return в случае удачного подключения возвращается true
     */
    public boolean connect() {
        boolean isConnected = false;
        try {
            Class.forName(driverName);
            conn = DriverManager.getConnection(dburl, user, passwd);
            if(conn != null) {
                //conn.setAutoCommit(false);
                isConnected = true;
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DBAccess.class.getName()).log(Level.SEVERE, null, ex);
        }
        return isConnected;
    }

    /**
     * Мотед для разрыва соединения с БД
     */
    public void disconnect() {
        try {
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(DBAccess.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Метод для получения списка сотрудников из БД
     * @return список сотрудников в виде List
     */
    public List<String> getStaffList() {
        List list = new ArrayList();
        try(Statement stmt = conn.createStatement()) {
            ResultSet rset =
                    stmt.executeQuery("select first_name, last_name from staff");
            while (rset.next()) {
                list.add(rset.getString(1)+" "+rset.getString(2));
            }
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(DBAccess.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    /**
     * Метод для получения названия отдела по его идентификатору
     * @param id идентификатор отдела
     * @return название отдела
     */
    public String getDepartment(int id) {
        String department = null;
        try(Statement stmt = conn.createStatement()) {
            PreparedStatement st = 
                    conn.prepareStatement("select department_name from "
                    + "departments where department_id = ?");
            st.setInt(1, id);
            ResultSet rset = st.executeQuery();
            rset.next();
            department = rset.getString(1);
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(DBAccess.class.getName()).log(Level.SEVERE, null, ex);
        }
        return department;
    }

}

