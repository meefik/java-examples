package javacollections;

public class Shape {
    private String name;
    Shape(String n) {
        name = n;
    }
    @Override
    public String toString() {
        return name;
    }
}
