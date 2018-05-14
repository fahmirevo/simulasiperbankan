import java.util.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Teller extends TimerTask{

    static ServerInterface server;

    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry(null);
            server = (ServerInterface) registry.lookup("RemoteServer");
            int n_tellers = 1;
            int tmp;
            Timer time;
            Teller teller;

            if(args.length == 1){
                tmp = Integer.parseInt(args[0]);
                if(tmp > 0){
                    n_tellers = tmp;
                }
            }

            for (int i = 0; i < n_tellers; i++) {
                time = new Timer();
                teller = new Teller();
                time.schedule(teller, 0, 5000);
                System.out.println("Teller" + i + " ready !!");
                Thread.sleep(1500);
            }

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
