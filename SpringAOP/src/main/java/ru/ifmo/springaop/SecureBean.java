/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.ifmo.springaop;

/**
 *
 * @author anton
 */
public class SecureBean {
    public String writeSecureMessage() {
        String msg = "Секретное сообщение!";
        return msg;
    }   
}
