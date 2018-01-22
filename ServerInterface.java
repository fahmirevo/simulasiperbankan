import java.rmi.*;
import java.rmi.server.*;
import java.util.*;

public interface ServerInterface extends Remote{
    public void serveQueue() throws RemoteException;
    public void pushCommand(String media, String name, String action, int money, String key) throws RemoteException;
}
