package javaenums;

import java.util.Arrays;
import java.util.Calendar;

enum Months {
    JANUARY, FEBRUARY, MARCH, APRIL, MAY, JUNE, 
    JULY, AUGUST, SEPTEMBER, OCTOBER, NOVEMBER, DECEMBER, NONEMBER(13);
    private int monthNumber;
    Months() {
        this.monthNumber = -1;
    }
    Months(int month) {
        this.monthNumber = month;
    }
    public Months getCurrentMonth() {
        Calendar cal = Calendar.getInstance();
        int m = cal.get(Calendar.MONTH);
        Months[] months = Months.values();
        return months[m];
    }
    public int getMonthNumber() {
        if (this.monthNumber == -1) {
            Months[] months = Months.values();
            this.monthNumber = Arrays.binarySearch(months, this)+1;
        }
        return this.monthNumber;
    }
}

public class JavaEnums {
    public static void main(String[] args) {
        // enum создает класс, унаследованный от java.lang.Enum 
        System.out.println(Months.class.getSuperclass());

        // Элементы enum Months { ... } - это статически доступные экземпляры enum-класса
        Months months = Months.JANUARY;
        if (months == Months.JANUARY) months = Months.FEBRUARY;
        System.out.println(months.name());
        
        // Получение елемента enum по строковому представлению его имени
        String name = "MARCH";
        months = Months.valueOf(name);
        System.out.println(months);
        
        // Получение всех элементов перечисления
        System.out.println(Arrays.toString(Months.values()));
        
        // Использование своих методов в enum-классе
        months = months.getCurrentMonth();
        System.out.println(months);
        
        // Использование enum через switch ... case
        switch (months) {
            case FEBRUARY: System.out.println("Февраль - первый месяц второго семестра");
                break;
            case MARCH: System.out.println("Март - второй месяц второго семестра");
                break;
            case APRIL: System.out.println("Апрель - третий месяц второго семестра");
                break;
            case MAY: System.out.println("Май - четвертый месяц второго семестра");
                break;
            default: System.out.println("Какой-то другой месяц");
        }
        
        // Использование конструктора с параметрами
        months = months.NONEMBER;
        System.out.println("Месяц "+months.name()+" номер "+months.getMonthNumber());
    }
}
