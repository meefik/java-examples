package javasockets;

/**
 * Программа реализует серверную и клиентскую части 
 * на примере работы echo-сервиса
 * 
 * @author Anton Skshidlevsky
 */
public class JavaSockets {

    /**
     * @param args массив входных параметров, требуется указать два параметра:
     * адрес хоста и порт.
     */
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Wrong number of arguments!");
            return;
        }
        String host = args[0];
        int port = Integer.valueOf(args[1]);
        Thread s = new Server(port);
        // запуск потока экземпляра класса Server
        s.start();
        Thread c = new Client(host, port);
        // запуск потока экземпляра класса Client
        c.start();
    }
}
