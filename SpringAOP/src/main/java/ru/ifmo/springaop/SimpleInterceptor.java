/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.ifmo.springaop;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 *
 * @author anton
 */
public class SimpleInterceptor implements MethodInterceptor {

    @Override
    public Object invoke(MethodInvocation mi) throws Throwable {
        Object returnValue = mi.proceed();
        returnValue = "Accepted message: '" + returnValue + "'";
        return returnValue;
    }
    
}
