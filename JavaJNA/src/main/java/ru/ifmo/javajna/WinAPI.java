package ru.ifmo.javajna;

import au.com.bytecode.opencsv.CSVWriter;
import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Pointer;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Пример работы с функциями Windows API, используя библиотеку Java JNA.
 */
public class WinAPI {

    public static final int PROCESS_QUERY_INFORMATION = 0x0400;
    public static final String OUTPUT_FILE = "output.csv";

    /**
     * Интерфейс для функций библиотеки psapi.dll
     */
    private interface Psapi extends Library {

        Psapi INSTANCE = (Psapi) Native.loadLibrary("Psapi", Psapi.class);

        boolean EnumProcesses(int[] pProcessIds, int cb, int[] pBytesReturned);

        int GetProcessImageFileNameW(Pointer hProcess, char[] lpImageFileName, int nSize);
    }

    /**
     * Интерфейс для функций библиотеки kernel32.dll
     */
    private interface Kernel32 extends Library {

        Kernel32 INSTANCE = (Kernel32) Native.loadLibrary("kernel32", Kernel32.class);

        Pointer OpenProcess(int dwDesiredAccess, boolean bInheritHandle, int dwProcessId);

        boolean CloseHandle(Pointer hObject);
    }

    /**
     * Получение идентификаторов всех процессов в системе.
     * http://msdn.microsoft.com/en-us/library/windows/desktop/ms682629(v=vs.85).aspx
     * @param pProcessIds массив идентификаторов
     * @param cb размер массива в байтах
     * @param pBytesReturned
     * @return возвращает истину в случае успещного завершения
     */
    public static boolean EnumProcesses(int[] pProcessIds, int cb, int[] pBytesReturned) {
        return Psapi.INSTANCE.EnumProcesses(pProcessIds, cb, pBytesReturned);
    }

    /**
     * Получение имени выполняемого файла для указанного процесса.
     * http://msdn.microsoft.com/en-us/library/windows/desktop/ms683217(v=vs.85).aspx
     * @param hProcess указатель на процесс, можно получить с помощью функции OpenProcess
     * @param lpImageFileName полный путь к выполняемому файлу процесса
     * @param nSize длина возвращенного пути в символах
     * @return возвращает длину строки, записанную в lpImageFileName
     */
    public static int GetProcessImageFileNameW(Pointer hProcess, char[] lpImageFileName, int nSize) {
        return Psapi.INSTANCE.GetProcessImageFileNameW(hProcess, lpImageFileName, nSize);
    }

    /**
     * Открывает существующий локальный процесс.
     * http://msdn.microsoft.com/en-us/library/windows/desktop/ms684320(v=vs.85).aspx
     * @param dwDesiredAccess флаг доступа к процессу, задает права доступа
     * @param bInheritHandle наследование указателя на процесс
     * @param dwProcessId идентификатор процесса
     * @return возвращает указатель на процесс
     */
    public static Pointer OpenProcess(int dwDesiredAccess, boolean bInheritHandle, int dwProcessId) {
        return Kernel32.INSTANCE.OpenProcess(dwDesiredAccess, bInheritHandle, dwProcessId);
    }

    /**
     * Закрывает указатель на процесс.
     * http://msdn.microsoft.com/en-us/library/windows/desktop/ms724211(v=vs.85).aspx
     * @param hObject указатель на процесс
     * @return возвращает истину в случае успещного завершения
     */
    public static boolean CloseHandle(Pointer hObject) {
        return Kernel32.INSTANCE.CloseHandle(hObject);
    }

    /**
     * Метод сохраняет коллекцию в CSV-файл, используя библиотеку OpenCSV.
     * @param entries коллекция массивов строк
     * @throws IOException 
     */
    public static void WriteCSV(List<String[]> entries) throws IOException {
        CSVWriter writer = new CSVWriter(new FileWriter(OUTPUT_FILE), ';');
        writer.writeAll(entries);
        writer.close();
    }

    /**
     * Получает список запущенных процессов в системе и сохраняет его в CSV-файл.
     * Формат строки в файле: ID процесса; имя процесса; полный путь к файлу
     * @param args
     * @throws IOException 
     */
    public static void main(String[] args) throws IOException {
        int maxProc = 1024;
        int[] processlist = new int[maxProc];
        int[] dummylist = new int[maxProc];
        // Получить список идентификаторов запущенных процессов
        EnumProcesses(processlist, maxProc, dummylist);
        List<String[]> list = new ArrayList();
        for (int pid : processlist) {
            // Открыть процесс для получения информации
            Pointer ph = OpenProcess(PROCESS_QUERY_INFORMATION, false, pid);
            if (ph != null) {
                char[] filename = new char[256];
                // Получить полный путь исполняемого файла
                GetProcessImageFileNameW(ph, filename, 256);
                // Закрыть процесс
                CloseHandle(ph);
                String fullPath = Native.toString(filename);
                if (fullPath.length() > 0) {
                    int index = fullPath.lastIndexOf("\\");
                    String fileName = fullPath.substring(index + 1);
                    String[] line = {String.valueOf(pid), fileName, fullPath};
                    list.add(line);
                }
            }
        }
        // Записать результат в файл
        WriteCSV(list);
    }
}
