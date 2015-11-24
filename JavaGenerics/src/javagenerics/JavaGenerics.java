package javagenerics;

import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Обобщенные типы и сериализация в Java.
 * 
 * @author Anton Skshidlevksy
 */
public class JavaGenerics {

    /**
     * Метод осуществляет рекурсивный поиск файлов и каталогов начиная с
     * указанного в параметре path.
     * @param path текущая директория сканирования.
     * @param s коллекция, содержащая список всех найденных файлов и каталогов.
     */
    private static void scanDir(String path, Set<FileInfo> s) {
        File root = new File(path);
        File[] list = root.listFiles();
        if (list == null) return;
        for (File f : list) {
            boolean isDirectory = f.isDirectory();
            long lastModified = f.lastModified();
            String fPath = f.getPath();
            if (isDirectory) {
                scanDir(fPath, s);
            }
            s.add(new FileInfo(fPath, lastModified, isDirectory));
        }
    }
    
    /**
     * Метод осуществляет поиск файлов и каталогов начиная с указанного 
     * в параметре root. Метод использует новые возможности Java 7.
     * @param root корневая директория сканирования.
     * @param s коллекция, содержащая список всех найденных файлов и каталогов.
     */
    private static void scanDirNew(final String root, final Set<FileInfo> s) {
        try {
            Path start = Paths.get(root);
            Files.walkFileTree(start, new SimpleFileVisitor<Path>() {
                private void writePath(String path, long time, boolean isDir) throws IOException {
                    s.add(new FileInfo(path, time, isDir));
                }

                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs)
                        throws IOException {
                    writePath(file.toString(), attrs.lastModifiedTime().to(TimeUnit.SECONDS), false);
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult postVisitDirectory(Path dir,
                        IOException exc) throws IOException {
                    writePath(dir.toString(), 0, true);
                    return FileVisitResult.CONTINUE;
                }
            });
        } catch (IOException ex) {
            Logger.getLogger(JavaGenerics.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Вспомогательный метод используется для закрытия потоков.
     * @param closeable ссылка на поток.
     */
    private static void closeQuietly(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException ex) {
                // ignore
            }
        }
    }
    
    /**
     * Метод используется чтения из файла сериализованной коллекции.
     * @param f файл для чтения.
     * @return коллекция.
     */
    private static Set<FileInfo> loadFromBynaryFile(File f) {
        Set<FileInfo> s = null;
        FileInputStream fs = null;
        ObjectInputStream os = null;
        try {
            if (!f.exists()) return null;
            fs = new FileInputStream(f);
            os = new ObjectInputStream(fs);
            s = (Set<FileInfo>)os.readObject();
        } catch (IOException ex) {
            Logger.getLogger(JavaGenerics.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(JavaGenerics.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeQuietly(os);
            closeQuietly(fs);
        }
        return s;
    }
    
    /**
     * Метод используется для сериализации (сохранения в файл) коллекции.
     * @param s коллекция, которую следуют сериализовать.
     * @param f файл для сохранения.
     * @return возвращает true в случае удачной сериализации.
     */
    private static boolean saveToBinaryFile(Set<FileInfo> s, File f) {
        boolean result = false;
        FileOutputStream fs = null;
        ObjectOutputStream os = null;
        try {
            fs = new FileOutputStream(f);
            os = new ObjectOutputStream(fs);
            os.writeObject(s);
            result = true;
        } catch (IOException ex) {
            Logger.getLogger(JavaGenerics.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeQuietly(os);
            closeQuietly(fs);
         }
        return result;
    }
    
    /**
     * Главный метод приложения. Если файл с сохраненной коллекцией существует,
     * то загружает её в память, если нет, то запускает поиск и сохраняет список
     * найденных файлов в виде коллекции в файл.
     * @param args массив входных параметров, требуется указать два параметра:
     * начальный (корневой) каталог для поиска и путь к файлу для сохранения
     * коллекции со списком файлов.
     */
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Wrong number of arguments!");
            return;
        }
        
        Set<FileInfo> list;
        
        File f = new File(args[1]);
        if (f.exists()) {
            // Загрузить коллекцию из файла
            list = loadFromBynaryFile(f);
        } else {
            list = new HashSet<>();
            // Рекурсивный поиск
            scanDir(args[0], list);
            // Поиск с использованием новых возможностей Java 7
            //scanDirNew(args[0], list);
        }
       
        // Вывести на экран содержимое коллекции
        for (FileInfo fi: list) {
            System.out.println(fi.getPath()+" "+fi.getModifiedTime()+" "+fi.isDirectory());
        }
        
        // Сохранить коллекцию на диск используя 
        saveToBinaryFile(list, f);
    }
}
