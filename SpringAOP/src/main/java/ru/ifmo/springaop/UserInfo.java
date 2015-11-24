/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.ifmo.springaop;

/**
 *
 * @author anton
 */
public class UserInfo {
    private String username;
    private String password;
    
    public UserInfo(String username, String password) {
        this.username = username;
        this.password = password;
    }
    
    public String getPassword() {
        return this.password;
    }
    
    public String getUsername() {
        return this.username;
    }
}
