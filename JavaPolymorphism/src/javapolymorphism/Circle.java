package javapolymorphism;

public class Circle extends Shape {
    private double radius;
    Circle(double r) {
        radius = r;
    }
    @Override
    public void draw() {
        System.out.println("Circle.draw(), radius = "+radius);
    }
    @Override
    public void erase() {
        System.out.println("Circle.erase()");
    }
    // переопределение закрытых методов не выполняется
    public void f() {
        System.out.println("Circle.f()");
    }
}
