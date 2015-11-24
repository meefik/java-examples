package javapolymorphism;

public class JavaPolymorphism {
    public static void main(String[] args) {
        Shape[] s = new Shape[7];
        for (int i = 0; i < s.length; i++) {
            switch((int)Math.round(Math.random()*2)) {
                default:
                case 0: 
                    s[i] = new Circle(Math.random());
                    break;
                case 1:
                    s[i] = new Triangle();
                    break;
                case 2:
                    s[i] = new Square();
                    break;
            }
        }
        for (Shape shp: s) {
            System.out.println("-------------------------------------");
            shp.info();
            shp.draw();
        }
    }
}
