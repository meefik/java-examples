package testapp;

import geometry.Circle;

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
                // c.r = 20;  // не будет работать из-за использования слова final
                System.out.println("Радиус круга: "+c.r);
                System.out.println("Периметр круга: "+c.getPerimeter());
                System.out.println("Площадь круга: "+c.getArea());
                System.out.println("Площадь круга (округление): "+c.getAreaRound());
                c.printInfo("Вызов мотода printInfo класса Circle и суперкласса Figure");
                c.upcasting();
            }        
        }
        catch(ArrayIndexOutOfBoundsException e) {
            System.out.println("Ошибочные входные параметры!");
        }
    }
}
