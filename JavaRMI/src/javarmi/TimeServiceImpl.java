package javarmi;

import java.rmi.*;
import java.util.*;
import java.rmi.server.*;

/**
 * Реализация интерфейса TimeService
 *
 * @author Anton Skshidlevsky
 */
public class TimeServiceImpl extends UnicastRemoteObject implements TimeService {

    public TimeServiceImpl() throws RemoteException {
        super();
    }

    @Override
    public Date getRemoteTime() throws RemoteException {
        return new Date();
    }
}
