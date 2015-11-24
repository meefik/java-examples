package javagenerics;

import java.io.Serializable;

/**
 * Класс для хранения информации о файле или каталоге.
 * 
 * Подключенный интерфейс Serializable готоворит о том, что данный класс
 * может быть преобразован в набор байт и в последствии востановлен.
 */
public class FileInfo<P, T, D> implements Serializable {
    private P fPath;
    private T fTime;
    private D fType;
    /* Модификатор transient применяется в сериализованных классах,
     * чтобы исключить какое-то поле из сериализации. Например:
     * 
     * transient private String str = "Эта переменная не сириализауется!";
     */
    
    /**
     * Конструктор класса
     * @param fPath путь к файлу (каталогу)
     * @param fTime время модификации файла
     * @param fType тип файла
     */
    FileInfo(P fPath, T fTime, D fType) {
        this.fPath = fPath;
        this.fTime = fTime;
        this.fType = fType;
    }
    
    /**
     * Метод возвращает путь к файлу (каталогу).
     * @return путь к файлу
     */
    public P getPath() {
        return fPath;
    }
    
    /**
     * Метод возвращает время последней модификации файла.
     * @return время последней модификации
     */
    public T getModifiedTime() {
        return fTime;
    }
    
    /**
     * Метод может быть использован для сравнения вдух экземпляров класса, 
     * например,в функции сортировки Collections.sort() при испльзовании 
     * собственного обработчика класса Comparator.
     * @param f экземпляр данного класса, с которым следует провести сравнение
     * @return положительное значение, если текущий экземпляр класса больше 
     * переданно во входном параметре; отрицательное значение, если экземпляр 
     * класса меньше переданно во входном параметре.
     */
    public long compareTo(FileInfo<P, T, D> f) {
        return (Long)this.fTime - (Long)f.fTime;
    }
    
    /**
     * Метод возвращает тип файла, например, является он директорией или нет.
     * @return тип файла
     */
    public D isDirectory() {
        return fType;
    }
    
    /**
     * Метод испльзуется в паре с методом equals для установления равенства 
     * или неравенства двух экземпляров данного класса.
     * @return хеш-код для одноначной (уникальной) идентификации объекта.
     */
    @Override
    public int hashCode(){
        int hash = 37;
        hash = hash*17 + (fPath == null ? 0 : fPath.hashCode());
        hash = hash*17 + (fType == null ? 0 : fType.hashCode());
        hash = hash*17 + (fTime == null ? 0 : fTime.hashCode());
        return hash;
    }

    /**
     * Метод испльзуется в паре с методом hashCode для установления равенства 
     * или неравенства двух экземпляров данного класса.
     * @param obj экземпляр класса, который нужно сравнить с текущим.
     * @return возвращает true, если данный экземпляр класса эквивалентен 
     * экземпляру переданному во входном параметре.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final FileInfo<P, T, D> other = (FileInfo<P, T, D>) obj;
        if (this.fPath != other.fPath && (this.fPath == null || !this.fPath.equals(other.fPath))) {
            return false;
        }
        if (this.fType != other.fType && (this.fType == null || !this.fType.equals(other.fType))) {
            return false;
        }
        if (this.fTime != other.fTime && fType.equals(false) && 
           (this.fTime == null || !this.fTime.equals(other.fTime))) {
            return false;
        }
       return true;
    }
    
}
