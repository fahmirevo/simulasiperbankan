import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

import javafx.scene.input.MouseEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;


public class TellerController{

    public static ServerInterface server;

    @FXML
    private ComboBox<String> accountPicker;

    @FXML
    private ComboBox<String> actionPicker;

    @FXML
    private TextField moneyField;

    @FXML Button submitButton;

    @FXML
    public void initialize(){
        accountPicker.getItems().addAll(
                "Adit",
                "Budi",
                "Caca",
                "Diana",
                "Ennisa"
                );

        actionPicker.getItems().addAll(
                "deposit uang",
                "ambil uang"
                );
    }

    @FXML
    private void handleSubmitButtonOnClick(MouseEvent me){
        String name = accountPicker.getValue();
        String action = actionPicker.getValue();
        String media = "teller";
        String act;
        String key = "";
        int money;

        try{
            money = Integer.parseInt(moneyField.getText());
        }catch(Exception e){
            return;
        }

        if(action.equals("deposit uang")){
            act = "add";
        }else{
            act = "sub";
        }

        try{
            server.pushCommand(media, name, act, money, key);
        }catch(Exception e){
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }
}

