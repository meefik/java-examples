package geometry;

public class Circle extends Figure {

    public final double r;

    public Circle(double r) {
        super();
        this.r = r;
        areaVal = Math.PI*r*r;
    }

/* Переопределение не будет работать для методов,
 * объявленных с использованием ключевого слова final
    @Override
    public double getArea() {
        return(2*Math.PI*r);
    }
*/    
    @Override
    public double getPerimeter() {
        return(2*Math.PI*r);
    }
    
    public long getAreaRound() {
        return(Math.round(super.getArea()));
    }

    public void printInfo(String text) {
        System.out.println("Круг: " + text);
        super.printInfo();
    }
    
  // Восходящее преобразование типов
    public void upcasting() {
        double nr = r+1;
        Circle c = new Circle(nr);
        System.out.println("Новый радиус: " + nr);
        Figure.printInfo(c);
    }

}
