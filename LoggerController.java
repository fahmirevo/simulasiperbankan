import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;


public class LoggerController{

    @FXML
    private TextArea logger;

    @FXML
    private TextArea queueStatus;

    @FXML
    private Label status;

    public void appendQueueStatus(String text){
        queueStatus.appendText(text);
    }

    public void appendLogger(String text){
        logger.appendText(text);
    }

    public void setStatus(String text){
        status.setText(text);
    }
}
