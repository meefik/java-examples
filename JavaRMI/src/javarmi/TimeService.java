package javarmi;

import java.rmi.*;
import java.util.Date;

/**
 * TimeService представляет собой интерфейс для удаленного объекта (сервера)
 *
 * @author Anton Skshidlevsky
 */
public interface TimeService extends Remote {

    /**
     * Полуление текущее время с сервера.
     * Если планируется использовать собственные классы в качестве
     * передаваемых между клиентом и сервером, то они должны быть
     * обязательно сериализованы, т.е. должны реализовать интерфейс
     * java.io.Serializable
     *
     * @return текст сообщения
     * @throws RemoteException
     */
    public Date getRemoteTime() throws RemoteException;
}
