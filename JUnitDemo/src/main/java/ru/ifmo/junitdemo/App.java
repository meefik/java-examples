package ru.ifmo.junitdemo;

/**
 * Java Randomizer.
 * @author Anton Skshidlevsky
 */
public class App {

    /**
     * Генератор случайнох чисел.
     * @param n количество элементов
     * @param p порядок случайного числа
     * @return массив случайных чисел
     */
    public static long[] genRand(int n, int p) {
        long pow = (long) Math.pow(10, p);
        long[] arr = new long[n];
        for (int i = 0; i < n; i++) {
            arr[i] = (long) (Math.random() * pow);
        }
        return arr;
    }

    /**
     * Главный метод.
     * @param args первым аргументом задается порядок числа
     */
    public static void main(String[] args) {
        long[] arr = genRand(10, 2);
        System.out.println("Generated numbers:");
        for (long i: arr) {
            System.out.println(i);
        }
    }
}
