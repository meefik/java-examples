/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.ifmo.springaop;

/**
 *
 * @author anton
 */
public class SecurityManager {
    
    private static ThreadLocal<UserInfo> threadLocal = new ThreadLocal<>();
    
    public void login(String username, String password) {
        threadLocal.set(new UserInfo(username, password));
    }
    
    public void logout() {
        threadLocal.set(null);
    }
    
    public UserInfo getLoggedOnUser() {
        return threadLocal.get();
    }
    
}
