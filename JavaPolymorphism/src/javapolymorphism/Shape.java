package javapolymorphism;

public class Shape {
    Shape() {
       System.out.println("Shape() конструктор");
       // вызов метода производного класса из конструктора
       // может столкнуться с проблемой инициализации
       draw();
    }
    public void draw() {}
    public void erase() {}
    public void info() { System.out.println(this.getClass()); }
    // закрытый метод f()
    private void f() {}
}
