import java.lang.Runnable;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.*;
import java.rmi.Remote;
import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import java.text.MessageFormat;

public class ServerApplication extends Application implements ServerInterface {

    static LoggerController loggerController;
    private static LinkedList<Transaction> queue = new LinkedList<Transaction>();
    private static ArrayList<Card> dataBase = new ArrayList<Card>();

    public static void main(String[] args) {
        try {
            ServerInterface obj = new ServerApplication();
            ServerInterface server = (ServerInterface) UnicastRemoteObject.exportObject(obj, 0);
            Registry registry = LocateRegistry.getRegistry();

            registry.bind("RemoteServer", server);
            System.out.println("Server ready");
        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }

        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("./LoggerScene.fxml"));
            Parent root = loader.load();
            loggerController = (LoggerController)loader.getController();

            Scene scene = new Scene(root);
            scene.setRoot(root);
            primaryStage.setTitle("Server Application");
            primaryStage.setScene(scene);
            primaryStage.show();
            initDataBase();

        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void initDataBase(){
        Card adit = new PlatinumCard("Adit");
        Card budi = new GoldCard("Budi");
        Card caca = new GoldCard("Caca");
        Card diana = new SilverCard("Diana");
        Card ennisa = new SilverCard("Ennisa");

        dataBase.add(adit);
        dataBase.add(budi);
        dataBase.add(caca);
        dataBase.add(diana);
        dataBase.add(ennisa);

        setStatus();
    }

    public void appendQueueStatus(){
        MessageFormat fmt = new MessageFormat("Queue : {0}\n");
        Object[] arg = {Integer.toString(queue.size())};

        loggerController.appendQueueStatus(fmt.format(arg));
    }

    public void setStatus(){
        MessageFormat fmt = new MessageFormat("Adit\t\t:{0}\nBudi\t\t:{1}\nCaca\t\t:{2}\nDiana\t:{3}\nEnnisa\t:{4}\n");
        Object[] args = {1,2,3,4,5};

        for (int i = 0; i < 5; i++){
            args[i] = dataBase.get(i).getMoney();
        }

        loggerController.setStatus(fmt.format(args));
    }

    public void printLog(String s){
        loggerController.appendLogger(s + "\n");
    }

    public void serveQueue() throws RemoteException{
        try{
            Transaction transaction = queue.pop();
            serveNow(transaction);
            appendQueueStatus();
        } catch (Exception e){
            // Do nothing
        }
    }

    public Card getAccount(String name){
        for(int i = 0; i < 5; i++){
            if(dataBase.get(i).getName().equals(name)){
                return dataBase.get(i);
            }
        }

        return null;
    }

    public void serveNow(Transaction transaction){
        String msg;

        if(transaction.isValid()){
            transaction.serve();
            msg = transaction.getSuccessMessage();
        }else{
            msg = transaction.getError();
        }

        printLog(msg);
        Platform.runLater(() -> {
            setStatus();
        });
    }

    public void pushCommand(String media, String name, String action, int money, String key) throws RemoteException{
        Card acc = getAccount(name);
        Transaction transaction;
        if(media.equals("atm")){
            transaction = new Transaction(acc, action, money, key);
            serveNow(transaction);
        }else{
            boolean force = true;
            transaction = new Transaction(acc, action, money, force);
            queue.add(transaction);
            appendQueueStatus();
        }
    }
}
