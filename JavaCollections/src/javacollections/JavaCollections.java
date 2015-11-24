package javacollections;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* Базовые интерфейсы и классы
* Интерфейс Collection предназначен для хранения однородных элементов.
* Его расширяют интерфейсы:
*   List – список, представляющий собой неупорядоченную коллекцию, в которой допустимы
*       дублирующие значения
*       ArrayList – динамический массив с произвольной вставкой и удалением элементов
*       LinkedList – связанный список, можно вставлять элементы только вначало и конец,
*           каждый элемент ссылается на предыдущий и на последующий
*   Set – множество, не может хранить дублирующих элементов
*   Queue – очередь
* Интерфейс Map предназначен для создания коллекций, хранящих пары «ключ-значение».
* Его реализуют классы:
*   HashMap
*   IdentityHashMap
*   LinkedHashMap
*/
/* Итератор - объект, обеспечивающий перемещение по последовательности объектов
 * с выбором каждого объекта этой последовательности, при этом не надо заботиться 
 * о лежащей в ее основе структуре.
 * Возможности:
 *  - запросить у контейнера итератор вызовом метода iterator()
 *  - получить следующие (или первый) элемент вызовом метода next()
 *  - проверить, остались ли еще объекты в последовательности методом hasNext()
 *  - удалить из последовательности последний элемент, возвращаемый итератором, методом remove()
 * Iterator - позволяет перемещаться вперед по последовательности.
 * ListIterator - разновидность Iterator, позволябщее совершать двусторонние перемещения
 *  по последовательности.
 */
 
public class JavaCollections {
    // универсальный метод работы с коллекцией не зависящий от её типа
    public static void display(Iterator<Apple> it) {
        while(it.hasNext()) {
            Apple a = it.next();
            System.out.println("a.getID(): "+a.getID());
        }
    }
    public static void main(String[] args) {
        // ArrayList ограничен для хранения объектов класса Apple (типизация)
        // добавить в ArrayList другие объекты, помимо Apple, нельзя
        // ArrayList унаследован от интерфейса Collection
        ArrayList<Apple> apples = new ArrayList<Apple>();
        for(int i = 0; i < 3; i++)
            // добавить элемент в ArrayList
            apples.add(new Apple());
        for(int i = 0; i < apples.size(); i++)
            // приведение к типу должно применяться, если коллекция не типизирована
            System.out.println(((Apple)apples.get(i)).getID());
        System.out.println("----------------------------------------");
        for(Apple c: apples)
            // если коллекция типизирована, то приведение выполняется автоматически
            System.out.println(c.getID());
        System.out.println("----------------------------------------");
        // использование итератора
        Iterator<Apple> it = apples.iterator();
        display(it);
        System.out.println("----------------------------------------");
        // удаление элемента
        it = apples.iterator();
        for(int i = 0; i < 2; i++) {
            it.next();
            it.remove();
        }
        System.out.println(apples.size());
        System.out.println("----------------------------------------");
        // работа с картами (Map)
        Map<String,Shape> shapeMap = new HashMap<String,Shape>();
        shapeMap.put("треугольник", new Shape("triangle"));
        shapeMap.put("круг", new Shape("circle"));
        shapeMap.put("квадрат", new Shape("square"));
        System.out.println(shapeMap);
        Shape circle = shapeMap.get("круг");
        System.out.println(circle);
        // проверка присутствия ключа или значения
        System.out.println(shapeMap.containsKey("круг"));
        System.out.println(shapeMap.containsValue(circle));
    }
}
