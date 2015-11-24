package helloworldapp;

public class Light {
    
    int brightness = 0;
    private static boolean status = false;
    
    public void on(int b) {
        status = true;
        brightness = b;
    }
    
    public void off() {
        status = false;
        brightness = 0;
    }
    
    public static boolean getStatus() {
        return(status);
    }
    
}
