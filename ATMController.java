import java.util.ResourceBundle;

import javafx.scene.input.MouseEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;


public class ATMController{

    public static ServerInterface server;

    @FXML
    private ComboBox<String> accountPicker;

    @FXML
    private ComboBox<Integer> moneyPicker;

    @FXML
    private PasswordField passwordField;

    @FXML
    private  Button submitButton;

    @FXML
    public void initialize(){
        accountPicker.getItems().addAll(
                "Adit",
                "Budi",
                "Caca",
                "Diana",
                "Ennisa"
                );

        moneyPicker.getItems().addAll(
                25,
                50,
                75,
                100
                );
    }

    @FXML
    private void handleSubmitButtonOnClick(MouseEvent me){
        String name = accountPicker.getValue();
        int money = moneyPicker.getValue();
        String password = passwordField.getText();
        String media = "atm";
        String act = "sub";


        try{
            server.pushCommand(media, name, act, money, password);
        }catch(Exception e){
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }
}


