package ru.ifmo.springaop.regexppc;

import org.springframework.aop.Advisor;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.JdkRegexpMethodPointcut;

import ru.ifmo.springaop.staticpc.SimpleAdvice;

/**
 * Пример среза, где сравнение имени метода осуществляется с помощью регулярного
 * выражения.
 */
public class RegexpPointcutExample {

    public static void main(String[] args) {
        RegexpBean target = new RegexpBean();
        
        // create the advisor
        JdkRegexpMethodPointcut pc = new JdkRegexpMethodPointcut();
        pc.setPattern(".*foo.*");
        Advisor advisor = new DefaultPointcutAdvisor(pc, new SimpleAdvice());
        
        // create the proxy
        ProxyFactory pf = new ProxyFactory();
        pf.setTarget(target);
        pf.addAdvisor(advisor);
        RegexpBean proxy = (RegexpBean)pf.getProxy();
        
        proxy.foo1();
        proxy.foo2();
        proxy.bar();
    }
}
