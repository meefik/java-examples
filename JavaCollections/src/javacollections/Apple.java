package javacollections;

public class Apple {
    private static long counter;
    private final long id = counter++;
    public long getID(){
        return id;
    }
}
