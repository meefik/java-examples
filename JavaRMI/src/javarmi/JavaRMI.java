package javarmi;

import java.rmi.*;
import java.rmi.registry.*;
import java.util.Date;

/**
 * Пример использование RMI - удаленного вызова методов
 *
 * @author Anton Skshidlevsky
 */
public class JavaRMI {

    /**
     * Приложение реализует серверную и клиентскую части RMI. Без параметров
     * запускается сервер, с одним параметров в виде адреса сервера запускается
     * клиент.
     *
     * @param args массив входных параметров
     */
    public static void main(String[] args) throws Exception {
        if (args.length == 0) {
            // инициализация сервера
            System.out.println("Initializing server...");
            // создание удаленного объекта
            TimeService service = new TimeServiceImpl();
            // регистрация в реестре по порту 1099 (порт по умолчанию)
            Registry reg = LocateRegistry.createRegistry(1099);
            // задание имени сервиса
            String serviceName = "TimeService";
            // регистрация удаленного объекта TimeService в реестре rmiregistry
            reg.rebind(serviceName, service);
        } else {
            // инициализация клиента
            System.out.println("Starting client... ");
            // создание строки, содержащей URL удаленного объекта
            String objectName = "rmi://" + args[0] + "/TimeService";
            // соединение с реестром RMI и получение удаленной ссылки
            // на удаленный объект
            TimeService ts = (TimeService) Naming.lookup(objectName);
            // вызов удаленного метода для получения времени с сервера
            Date d = ts.getRemoteTime();
            System.out.println("Remote time: " + d.toString());
        }

    }
}
