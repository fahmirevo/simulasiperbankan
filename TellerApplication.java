import javafx.application.Application;
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

import java.util.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class TellerApplication extends Application{

    public static ServerInterface server;
    private TellerController clientConroller;

    @Override
    public void start(Stage primaryStage) {
        try {
            Registry registry = LocateRegistry.getRegistry(null);
            server = (ServerInterface) registry.lookup("RemoteServer");
        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
        System.out.println("Client ready !!");


        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("./TellerScene.fxml"));
            Parent root = loader.load();
            clientConroller = (TellerController)loader.getController();

            Scene scene = new Scene(root);
            scene.setRoot(root);
            primaryStage.setTitle("Client Application");
            primaryStage.setScene(scene);
            primaryStage.show();

            TellerController.server = server;
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

}
