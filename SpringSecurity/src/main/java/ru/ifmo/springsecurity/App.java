package ru.ifmo.springsecurity;

import org.springframework.aop.framework.ProxyFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.security.authentication.*;
import org.springframework.security.core.*;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * Демонстрация работы Spring Security.
 */
public class App {

    private static AuthenticationManager am = new SampleAuthenticationManager();

    public static void main(String[] args) {
        String username = "demo";
        String password = "demo";
        try {
            Authentication request = new UsernamePasswordAuthenticationToken(username, password);
            Authentication result = am.authenticate(request);
            SecurityContextHolder.getContext().setAuthentication(result);
        } catch (AuthenticationException e) {
            System.out.println("Authentication failed: " + e.getMessage());
        }
        System.out.println("Successfully authenticated. Security context contains: \n"
                + SecurityContextHolder.getContext().getAuthentication());
        
        testAccess();
        
    }
    
    private static void testAccess() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("app-context.xml");

        SecureBean target = ctx.getBean("secureBean", SecureBean.class);

        // create the before advice
        BeforeAdvice advice = new BeforeAdvice();

        // get the proxy
        ProxyFactory factory = new ProxyFactory();
        factory.setTarget(target);
        factory.addAdvice(advice);
        SecureBean proxy = (SecureBean) factory.getProxy();
        
        try {
            proxy.getUserMessage();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
        try {
            proxy.getAdminMessage();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
