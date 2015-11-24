package javaconfig;

import java.io.*;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Класс реализаующий интерфейс работы с конфигурациями.
 */
public class Config {
    private final String file;
    private Properties prop;
    
    /**
     * Конструктор класса.
     * @param f путь к XML-файлу конфигурации.
     */
    public Config(String f) {
        file = f;
        prop = new Properties();
    }
    
    /**
     * Вспомогательный метод используется для закрытия потоков.
     * @param closeable ссылка на поток.
     */
    private static void closeQuietly(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException ex) {
                // ignore
            }
        }
    }
    
    /**
     * Метод для загрузки конфигураций из XML-файла.
     */
    public void loadFromXML() {
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(file);
            prop.loadFromXML(fis);
        } catch (IOException ex) {
            Logger.getLogger(Config.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeQuietly(fis);
        }
    }
    
    /**
     * Метод для сохранения конфигураций в XML-файл.
     */
    public void saveToXML() {
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file);
            prop.storeToXML(fos, "SyncDirs config");
        } catch (IOException ex) {
            Logger.getLogger(Config.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeQuietly(fos);
        }
    }
    
    /**
     * Метод для установки/изменения параметра в конфигурации.
     * @param key ключ.
     * @param value значение.
     */
    public void setProperty(String key, String value) {
        prop.setProperty(key, value);
    }
    
    /**
     * Метод для получения значения параметра по ключу.
     * @param key ключ.
     * @return значение параметра.
     */
    public String getProperty(String key) {
        return prop.getProperty(key);
    }
    
    /**
     * Метод для выводя на экран список всех ключей их их значений.
     */
    public void printAll() {
        Enumeration keys = prop.keys();
        while (keys.hasMoreElements()) {
            String key = (String)keys.nextElement();
            String value = (String)prop.get(key);
            System.out.println(key + ": " + value);
        }
    }

}
