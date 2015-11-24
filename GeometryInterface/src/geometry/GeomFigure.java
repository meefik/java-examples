package geometry;

public class GeomFigure implements Figure {

    protected double areaVal;
    protected double perimeterVal;

    public GeomFigure() {
        areaVal = 0;
        perimeterVal = 0;
    }

    @Override
    public final double getArea() {
        return(areaVal);
    }

    @Override
    public double getPerimeter() {
        return(perimeterVal);
    }
    
    public void printInfo() {
        System.out.println("Площадь: "+ getArea() + "\tПериметр: " + getPerimeter());
    }
    
    static void printInfo(GeomFigure f) {
        f.printInfo();
    }

}

