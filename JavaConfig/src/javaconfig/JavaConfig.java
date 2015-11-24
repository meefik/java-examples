package javaconfig;

/**
 * Работа с конфигурациями.
 * Сохранение и загрузка параметров из XML файлов.
 * 
 * @author Anton Skshidlevksy
 */
public class JavaConfig {

    /**
     * Главный метод приложения.
     * При запуске приложения с двумя параметрами, указанные в них ключ и
     * значение сохраняются в XML-файл. Если приложение запущено без параметров, 
     * то отображается список сохраненных параметров, загруженных из файла.
     * @param args массив входных параметров. Первый параметр указывает ключ 
     * параметра, второй параметр указывает значение параметра. Во входных 
     * параметрах можно указывать неограниченное количетво пар ключ/значение.
     */
    public static void main(String[] args) {
        Config conf = new Config("config.xml");
        if (args.length > 0 && args.length % 2 == 0) {
            for (int i = 0; i < args.length; i=i+2) {
                conf.setProperty(args[i], args[i+1]);
                System.out.println("Key \'"+args[i]+"\' and value \'"+
                        args[i+1]+"\' saved!");
            }
            conf.saveToXML();
        } else {
            conf.loadFromXML();
            conf.printAll();
        }
    }
}
