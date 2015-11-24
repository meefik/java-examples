/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.ifmo.springaop;

import java.lang.reflect.Method;
import org.springframework.aop.MethodBeforeAdvice;

/**
 *
 * @author anton
 */
public class BeforeAdvice implements MethodBeforeAdvice {

    private SecurityManager securityManager;

    public BeforeAdvice() {
        this.securityManager = new SecurityManager();
    }

    @Override
    public void before(Method method, Object[] args, Object target)
            throws Throwable {
        UserInfo user = securityManager.getLoggedOnUser();
        if (user == null) {
            throw new SecurityException(
                    "You must login before attempting to invoke the method: "
                    + method.getName());
        } else {
            System.out.println("Logged user: " + user.getUsername());
        }
    }
}
