package ru.ifmo.springaop.namepc;

import org.springframework.aop.Advisor;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.NameMatchMethodPointcut;

import ru.ifmo.springaop.staticpc.SimpleAdvice;

/**
 * Пример среза, где сревнение имени метода осуществляется просто по его имени.
 */
public class NamePointcutExample {

    public static void main(String[] args) {
        NameBean target = new NameBean();

        // create advisor
        NameMatchMethodPointcut pc = new NameMatchMethodPointcut();
        pc.addMethodName("foo");
        pc.addMethodName("bar");
        Advisor advisor = new DefaultPointcutAdvisor(pc, new SimpleAdvice());
        
        // create the proxy
        ProxyFactory pf = new ProxyFactory();
        pf.setTarget(target);
        pf.addAdvisor(advisor);
        NameBean proxy = (NameBean)pf.getProxy();
        
        proxy.foo();
        proxy.foo(999);
        proxy.bar();
        proxy.yup();
    }
}