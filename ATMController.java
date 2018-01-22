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
    private TextField moneyField;

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
    }

    @FXML
    private void handleSubmitButtonOnClick(MouseEvent me){
        String name = accountPicker.getValue();
        String password = passwordField.getText();
        String media = "atm";
        String act = "sub";
        int money;
        try{
            money = Integer.parseInt(moneyField.getText());
        }catch(Exception e){
            return;
        }

        try{
            server.pushCommand(media, name, act, money, password);
        }catch(Exception e){
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }
}


