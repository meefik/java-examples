package geometryapp;

/**
 *
 * @author anton
 * @version 1.0
 */
public class GeometryApp {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            if (args[0].equals("circle")) {
                Circle c = new Circle(Double.valueOf(args[1]));
                System.out.println("Периметр круга: "+c.perimeter());
                System.out.println("Площадь круга: "+c.area());
            }        
        }
        catch(ArrayIndexOutOfBoundsException e) {
            System.out.println("Ошибочные входные параметры!");
        }
    }
}
