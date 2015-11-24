package ru.ifmo.springaop;

import java.util.Scanner;
import org.springframework.aop.framework.ProxyFactory;

/**
 * Spring AOP
 *
 */
public class App {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean isLoop = true;
        SecurityManager mgr = new SecurityManager();
        SecureBean bean = getSecureBean();
        while (isLoop) {
            System.out.print("Enter command: ");
            String cmd = sc.next();
            switch (cmd) {
                case "login":
                    System.out.print("Enter user: ");
                    String login = sc.next();
                    System.out.print("Enter password: ");
                    String password = sc.next();
                    try {
                        mgr.login(login, password);
                    } catch (SecurityException ex) {
                        System.out.println("Exception: " + ex.getMessage());
                    }
                    break;
                case "message":
                    try {
                        bean.writeSecureMessage();
                    } catch (SecurityException ex) {
                        System.out.println("Exception: " + ex.getMessage());
                    }
                    break;
                case "logout":
                    mgr.logout();
                    break;
                case "exit":
                    isLoop = false;
                    break;
            }
        }
    }

    private static SecureBean getSecureBean() {
        // create the target
        SecureBean target = new SecureBean();

        // create the before advice
        BeforeAdvice advice1 = new BeforeAdvice();
        
        // create the after advice
        AfterAdvice advice2 = new AfterAdvice();
        
        // create interceptor
        SimpleInterceptor advice3 = new SimpleInterceptor();

        // get the proxy
        ProxyFactory factory = new ProxyFactory();
        factory.setTarget(target);
        factory.addAdvice(advice1);
        factory.addAdvice(advice2);
        factory.addAdvice(advice3);
        SecureBean proxy = (SecureBean) factory.getProxy();

        return proxy;

    }
}
