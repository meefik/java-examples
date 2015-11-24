/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package helloworldapp;

// подключаем требуемые классы
import java.util.Date;
// или группу классов
//import java.util.*;

/**
 *
 * @author Anton
 * @version 1.0
 */
public class HelloWorldApp {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // команда вывода в консоль
        System.out.println("Hello World!");
        
        // пример создания экземпляра класса Date
        Date d = new Date();
        // и его дальнейшее использование
        System.out.println(d);
        
        Light lt1 = new Light();
        // приоритет: */ обрабатывается раньше +-
        int b = (4+2)/2;
        lt1.on(b);
        Light lt2 = new Light();
        lt2.on(7);
        System.out.println("Brightness: " + lt1.brightness + "  " +lt2.brightness);
        // присвоение (aliasing)
        lt1 = lt2;
        lt1.brightness += 2;
        System.out.println("Brightness: " + lt1.brightness + "  " +lt2.brightness);
        
        // совмещение имен во время вызова методов
        setBrightness(lt1);
        System.out.println("Brightness: " + lt1.brightness + "  " +lt2.brightness);
        
        // автоувеличение и автоуменьшение
        int i = 0;
        System.out.println("i : " + i);
        System.out.println("i++ : " + i++);
        System.out.println("++i : " + ++i);
        
        // операторы сравнения: <, >, <=, >=, ==, !=
        Integer n1 = new Integer(16);
        Integer n2 = new Integer(16);
        System.out.println("n1 == n2 : " + (n1 == n2));
        System.out.println("n1 != n2 : " + (n1 != n2));
        System.out.println("n1.equals(n2) : " + n1.equals(n2));
        
        // логические операторы И(&&), ИЛИ(||) и НЕ(!)
        System.out.println("(i <= 2) && (i > 0) : " + ((i <= 2) && (i > 0)));
        
        // ускоренное вычисление
        boolean t = test1(0) && test2(2) && test3(3);
        System.out.println("Result : " + t);
        
        // операторы + и += для String
        String s = "abc";
        s += "de" + "f";  // конкатенация
        System.out.println(s);
        
        // операторы приведения типов
        int i1 = 100;
        long l1 = (long)i;  // "расширение", явное преобразование не обязательно
        i1 = (int)l1;  // "сужение", преобразование необходимо
        double d1 = 0.3;
        // округление = усечение
        System.out.println("(int)d1 : " + (int)d1);
        // повышение: float + double = double, int + long = long
        
        // переполнение
        overflow();
        
    }
    
    static void setBrightness(Light y) {
        y.brightness = 0;
    }
    
    static boolean test1(int val) {
        System.out.println("test1 : " + val);
        return val < 1;
    }
    
    static boolean test2(int val) {
        System.out.println("test2 : " + val);
        return val < 2;
    }
    
    static boolean test3(int val) {
        System.out.println("test3 : " + val);
        return val < 3;
    }
    
    // литералы
    static void literals() {
        int i1 = 0x2f;    // шестнадцатиричное
        int i2 = 0177;    // восьмиричное (начинается с нуля)
        char c = 0xffff;  // макс. шестнадцатиричное знач. char
        byte b = 0x7f;    // макс. шестнадцатиричное знач. byte
        short s = 0x7fff; // макс. шестнадцатиричное знач. short
        long n1 = 200l;   // суффикс, обозначающий long
        long n2 = 200;
        float f = 1f;     // суффикс, обозначающий float
        double d = 1d;    // суффикс, обозначающий double
    }
    
    static void overflow() {
        int big = Integer.MAX_VALUE;
        System.out.println("big : " + big);
        int bigger = big * 4;
        System.out.println("bigger : " + bigger);
        
    }
}


