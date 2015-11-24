package javacalc;

public class MyCalc {
    private double x;
    private double y;
    MyCalc() {
        //System.out.println("Вызывается конструктор без параметров");
    }    
    MyCalc(double x, double y) {
        this();
        this.x = x;
        this.y = y;
    }
    public double calcSum() {
        return x+y;
    }
    public double calcDiff() {
        return x-y;
    }
    public double calcMult() {
        return x*y;
    }
    public double calcDiv() {
        return x/y;
    }
    public double calcPow() {
        return Math.pow(x,y);
    }
}
