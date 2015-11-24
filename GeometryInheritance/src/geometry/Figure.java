package geometry;

public class Figure {

    protected double areaVal;
    protected double perimeterVal;

    public Figure() {
        areaVal = 0;
        perimeterVal = 0;
    }

    public final double getArea() {
        return(areaVal);
    }

    public double getPerimeter() {
        return(perimeterVal);
    }
    
    public void printInfo() {
        System.out.println("Площадь: "+ getArea() + "\tПериметр: " + getPerimeter());
    }
    
    static void printInfo(Figure f) {
        f.printInfo();
    }

}
