package javacalc;

import java.util.Scanner;

public class JavaCalc {
    static String delims;
    static {
        // поддерживаемые арифметические операции: +, -, *, ^, /
        delims = "([+\\-*/\\^ ]+)";
    }
    public static void main(String[] args) {
        // ввод данных с клавиатуры
        Scanner in = new Scanner(System.in);
        System.out.print("Введите выражение: ");
        String expr = in.nextLine();
        in.close();

        //String expr = "2*4^3 - 4/5*2 + 5^2";
        expr = expr.replaceAll(" ", "").replaceAll(delims, " $1 ");
        String[] tokens = expr.split(" ");
        for (char i = 0; i < tokens.length; i++) {
            double x,y;
            switch (tokens[i].toCharArray()[0]) {
                case '+':
                    x = Double.parseDouble(tokens[i-1]);
                    y = Double.parseDouble(tokens[i+1]);
                    MyCalc mc1 = new MyCalc(x,y);
                    System.out.println(x+" + "+y+" = "+mc1.calcSum());
                    break;
                case '-':
                    x = Double.parseDouble(tokens[i-1]);
                    y = Double.parseDouble(tokens[i+1]);
                    MyCalc mc2 = new MyCalc(x,y);
                    System.out.println(x+" - "+y+" = "+mc2.calcDiff());
                    break;
                case '*':
                    x = Double.parseDouble(tokens[i-1]);
                    y = Double.parseDouble(tokens[i+1]);
                    MyCalc mc3 = new MyCalc(x,y);
                    System.out.println(x+" * "+y+" = "+mc3.calcMult());
                    break;
                case '^':
                    x = Double.parseDouble(tokens[i-1]);
                    y = Double.parseDouble(tokens[i+1]);
                    MyCalc mc4 = new MyCalc(x,y);
                    System.out.println(x+" ^ "+y+" = "+mc4.calcPow());
                    break;
                case '/':
                    x = Double.parseDouble(tokens[i-1]);
                    y = Double.parseDouble(tokens[i+1]);
                    MyCalc mc5 = new MyCalc(x,y);
                    System.out.println(x+" / "+y+" = "+mc5.calcDiv());
                    break;
                default:
            }
        }
    }
}
