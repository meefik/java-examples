package javaexceptions;

// собственное исключение
class SimpleException extends Exception {
    public SimpleException() {}
}
class MyException extends Exception {
    public MyException() {}
    // определения конструктора с параметрами
    public MyException(String msg) { super(msg); }
}

public class JavaExceptions {
    // сообщаем об исключениях, возбуждаемых методом, 
    // с помощью ключевого слова throws
    public static void f() throws SimpleException {
        if (Math.random() > 0.5) {
            System.out.println("Возбуждаем SimpleException из f()");
            throw new SimpleException();
        }
    }
    public static void g() throws MyException {
        throw new MyException("Возбуждаем SimpleException из g()");
    }
    public static void h() throws Exception {
        try {
            g();
        } catch(Exception e) {
            System.out.println("Перехват исключения g() в h()");
            e.printStackTrace(System.out);
            // повторное возбуждение исключения
            throw e;
        }
    }
    public static void r() {
        throw new RuntimeException("Возбуждено из r()");
    }
    public static void main(String[] args) {
        // Throwable - корневой класс иерархии исключений (включает подклассы Exception and Error)
        // Exception - базовый класс для практически всех классов 
        //             программно восстановимых исключений
        // Error - базовых класс для неисправимых ошибок, типа OutOfMemoryError,
        //         InternalError или StackOverflowError
        // Вызов исключения:
        // throw new NullPointerException("без паники, это просто проверка исключений :)");

        // Перехват исключений:
        try {
            f();
            g();
        } catch(SimpleException e) {
            e.printStackTrace(System.err);
        } catch(MyException e) {
            e.printStackTrace(System.err);
        }
        try {
            throw new Exception("Мое исключение");
        } catch(Exception e) {
            System.out.println("Перехвачено");
            System.out.println("getMessage(): " + e.getMessage());
            System.out.println("getLocalizedMessage(): " + e.getLocalizedMessage());
            System.out.println("toString: " + e);
            System.out.println("printStackTrace(): ");
            e.printStackTrace(System.out);
        }
        // Повторное возбуждение исключения
        try {
            h();
        } catch(Exception e) {
            System.out.println("main: printStackTrace()");
            e.printStackTrace(System.out);
        }
        // h; // нельзя вызвать без обработчика исключений
        // Исключения, унаследованные от RuntimeException, перехватываются автоматически
        r();
        // Завершение с помощью finally
        String t = null;
        try {
            long l = t.length();
            System.out.println("Результат: " + l);
        } catch (NullPointerException e) {
            System.out.println("Исключение null-ссылка:");
            e.printStackTrace(System.out);
        } finally {
            // finally выполняется всегда
            t = "текст";
            System.out.println("Исправленный результат: " + t);
        }
            
    }
}
