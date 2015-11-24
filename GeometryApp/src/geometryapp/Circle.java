package geometryapp;

public class Circle {
    
    public double r = 0;
    
    public Circle(double r) {
        this.r = r;
    }

    public double area() {
        return(Math.PI*r*r);
    }
    
    public double perimeter() {
        return(2*Math.PI*r);
    }

}
