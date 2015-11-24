package javagc;

public class Test {
    static String version;
    static {
        version = "1.0";
    }
    String s;
    int i;
    double d;
    {
        s = "Инициализация переменных";
        i = 123;
        d = 0.123;
    }
    Test() {
        //System.out.println("Вызывается конструктор без параметров");
    }
    Test(String s) {
        this();
        this.s = s;
        //System.out.println("Вызывается конструктор N1");
    }
    Test(double d) {
        this("Проверка конструктора");
        this.d = d;
        //System.out.println("Вызывается конструктор N2");
    }
    public void printVars() {
        System.out.println(s);
    }
}
