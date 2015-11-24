package javasockets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Класс реализует Client Socket.
 * Класс создан на основе класса Thread и для работы с ним необходимо перегрузить
 * метод run(), который выполняется при запуске потока.
 */
public class Client extends Thread {
    
    private String host;
    private int port;
    
    public Client(String host, int port) {
        this.host = host;
        this.port = port;
    }
    
    @Override
    public void run() {
        Socket fromserver = null;
        BufferedReader in = null;
        PrintWriter out = null;
        Scanner stdin = null;
        try {
            System.out.println("Connecting to " + host + ":" + port);

            fromserver = new Socket(host, port);
            in = new BufferedReader(new InputStreamReader(fromserver.getInputStream()));
            out = new PrintWriter(fromserver.getOutputStream(), true);
            stdin = new Scanner(System.in);
            String fuser, fserver;
            while ((fuser = stdin.nextLine()) != null) {
                out.println(fuser);
                fserver = in.readLine();
                System.out.println(fserver);
                if (fuser.equalsIgnoreCase("exit")) {
                    break;
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                out.close();
                in.close();
                stdin.close();
                fromserver.close();
            } catch (IOException ex) {
                // ignore
            }
        }
    }
}
