package ru.ifmo.springaop.namepc;

import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.NameMatchMethodPointcutAdvisor;

import ru.ifmo.springaop.staticpc.SimpleAdvice;

/**
 * Главные класс для демонстрации работы среза на основе сопоставления 
 * имени метода.
 */
public class NamePointcutUsingAdvisor {

    public static void main(String[] args) {
        NameBean target = new NameBean();

        // create advisor
        NameMatchMethodPointcutAdvisor advisor = new NameMatchMethodPointcutAdvisor(
                new SimpleAdvice());
        advisor.addMethodName("foo");
        advisor.addMethodName("bar");

        // create the proxy
        ProxyFactory pf = new ProxyFactory();
        pf.setTarget(target);
        pf.addAdvisor(advisor);
        NameBean proxy = (NameBean) pf.getProxy();

        proxy.foo();
        proxy.foo(999);
        proxy.bar();
        proxy.yup();
    }
}