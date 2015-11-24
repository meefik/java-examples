/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javaprg2;

import java.util.Random;

/**
 *
 * @author anton
 */
public class JavaPrg2 {

    static int result = 0;
    static void test1(int testval, int target) {
        if(testval > target)
            result = +1;
        else if(testval < target)
            result = -1;
        else
            result = 0;
    }
    
    static String test2(int testval, int target) {
        if(testval > target)
            return testval + " > " + target;
        else if(testval < target)
            return testval + " < " + target;
        else
            return testval + " = " + target;
    }
    
    static boolean contition() {
        boolean output = Math.random() < 0.9;
        System.out.print(output + ", ");
        return output;
    }

    public static void main(String[] args) {
        long t = System.currentTimeMillis();
        
        // if(логическое выражение) 
        //     команда
        // else
        //     команда
        test1(10, 5);
        System.out.println(result);
        test1(5, 10);
        System.out.println(result);
        test1(5, 5);
        System.out.println(result);
        
        // while(логическое выражение)
        //     команда
        while(contition())
            System.out.println("Inside 'while'");
        System.out.println("Exited 'while'");
        
        // do
        //     команда
        // while(логическое выражение);
        /*
         * do {
         *    System.out.println("Inside 'while'");
         * } while(contition());
         * System.out.println("Exited 'while'");
         */
        
        // for(инициализация; логическое выражение; шаг)
        //     команда
        for(char c = 0; c < 128; c++)
            if(Character.isLowerCase(c))
                System.out.println("Value " + (int)c + " symbol " + c);
        
        // запятая в цикле for
        // возможно совместное использование переменных только одного типа
        for(int i = 1, j = i + 10; i < 5; i++, j = i * 2) {
             System.out.println("i = " + i + " j = " + j);
        }
        
        // for(инициализация: массив данных)
        Random rand = new Random(123);
        float f[] = new float[10];
        for(int i = 0; i < 10; i++)
            f[i] = rand.nextFloat();
        for(float x: f)
            System.out.println(x);
        
        String str = new String("Hello World!");
        for(char c: str.toCharArray())
            System.out.print(c + " ");
        System.out.println();
        
        // return
        System.out.println(test2(10, 5));
        System.out.println(test2(5, 10));
        System.out.println(test2(5, 5));
        
        // break и continue
        int i = 0;
        while(true) {
            i++;
            int j = i*27;
            if(j == 1269) break;
            if(i % 10 != 0) continue;
            System.out.print(i + " ");
        }
        System.out.println();
        
        // аналог goto в Java
        int k = 0;
        label:
        while(true) {
            System.out.println("Outer loop");
            for(;;) {
                k++;
                System.out.println("k = " + k);
                if(k == 1) {
                    System.out.println("continue");
                    continue;
                }
                if(k == 3) {
                    System.out.println("continue from label");
                    continue label;
                }
                if(k == 5) {
                    System.out.println("break");
                    break;
                }
                if(k == 7) {
                    System.out.println("break from label");
                    break label;
                }
            }
        }
        
        // switch
        Random r = new Random(47);
        for(int j = 0; j < 100; j++) {
            int c = rand.nextInt(32) + 'а';
            System.out.print((char)c + ", " + c + ": ");
            switch(c) {
                case 'а':
                case 'е':
                case 'ё':
                case 'и':
                case 'о':
                case 'у':
                case 'ы':
                case 'э':
                case 'ю':
                case 'я':
                    System.out.println("гласная");
                    break;
                default: System.out.println("согласная");
            }
            
        }
        
        System.out.println("Execute Time: " + (System.currentTimeMillis()-t) + " ms");
    }
}
