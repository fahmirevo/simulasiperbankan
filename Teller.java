import java.util.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Teller extends TimerTask{

    static ServerInterface server;

    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry(null);
            server = (ServerInterface) registry.lookup("RemoteServer");

            Timer time = new Timer();
            Teller teller = new Teller();
            time.schedule(teller, 0, 5000);
            System.out.println("Teller ready !!");
        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }

    public void run(){
        try{
            server.serveQueue();
        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }
}
