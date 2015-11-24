package ru.ifmo.springscheduler;

/**
 * Реализуем интерфейс SimpleService.
 */
public class SimpleServiceImpl implements SimpleService {

    private String msg;
    
    public SimpleServiceImpl(String msg) {
        this.msg = msg;
    }
    public void updateMethod() {
        System.out.println(msg);
    }
    
}
