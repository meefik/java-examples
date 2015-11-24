package ru.ifmo.springdi;

/**
 * Усовершенствованный Hello World. Текст сообщения может задаваться из
 * командной строки.
 */
public class HelloWorldWithCommandLine {

    /**
     * @param args
     */
    public static void main(String[] args) {

        if (args.length > 0) {
            System.out.println(args[0]);
        } else {
            System.out.println("Hello World!");
        }

    }
}
