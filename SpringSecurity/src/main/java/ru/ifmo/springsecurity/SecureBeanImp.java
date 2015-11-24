package ru.ifmo.springsecurity;

/**
 * Реализация защищаемого бина.
 */
public class SecureBeanImp implements SecureBean {
    
    @Override
    public String getAdminMessage() {
        String msg = "Admin";
        System.out.println(msg);
        return msg;
    }
    
    @Override
    public String getUserMessage() {
        String msg = "User";
        System.out.println(msg);
        return msg;
    }
    
}
