package javahibernate;

import java.util.List;
import javax.persistence.*;

/**
 * Java Persistence API (JPA)
 * 
 * @author Anton Skshidlevsky
 */
public class JavaHibernate {
    
    private static EntityManager em = null;
    
    static {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JavaHibernatePU");
        em = emf.createEntityManager();
    }
    
    /**
     * Добавить нового пользователя в БД
     * @param user пользователь
     */
    public static void addUser(Users user) {
        em.getTransaction().begin();
        em.persist(user);
        em.getTransaction().commit();
    }
    
    /**
     * Изменить пользователя в БД
     * @param user пользователь
     */
    public static void updateUser(Users user) {
        em.getTransaction().begin();
        em.merge(user);
        em.getTransaction().commit();
    }
    
    /**
     * Удалить пользователя из БД
     * @param id идентификатор пользователя 
     */
    public static void deleteUser(Long id) {
        em.getTransaction().begin();
        Users u = (Users) em.find(Users.class, id);
        em.remove(u);
        em.getTransaction().commit();
    }
    
    /**
     * Найти пользователя по его ID
     * @param id 
     * @return идентификатор пользователя
     */
    public static Users findByPrimaryKey(Long id) {
        em.getTransaction().begin();
        Users gs = (Users) em.find(Users.class, id);
        em.getTransaction().commit();
        return gs;
    }
    
    /**
     * Получить списко всех пользователей
     * @return список пользователей
     */
    public static List<Users> findAll() {
        em.getTransaction().begin();
        Query queryResult = em.createQuery("from Users");
        List<Users> allSummaries = queryResult.getResultList();
        em.getTransaction().commit();
        return allSummaries;
    }

    /**
     * @param args не используется
     */
    public static void main(String[] args) {
        Users user;
        
        // Добавить нового пользователя
        user = new Users();
        user.setName("Legolas");
        user.setPasswd(MD5("secret1"));
        addUser(user);

        // Изменить существующего пользователя
        user = new Users();
        user.setId(1l);
        user.setName("Gandalf");
        user.setPasswd(MD5("secret2"));
        updateUser(user);
        
        // Удалить пользователя с идентификатором 2
        deleteUser(2l);
        
        // Получить список всех записей
        for (Users u: findAll()) {
            System.out.println(u.getName());
        }
    }
    
    /**
     * Перевод строки в MD5 хеш
     * @param s строка
     * @return MD5 хеш
     */
    private static String MD5(String s) {
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
            byte[] array = md.digest(s.getBytes());
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < array.length; ++i) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1, 3));
            }
            return sb.toString();
        } catch (java.security.NoSuchAlgorithmException e) {
            // ignore
        }
        return null;
    }
}
