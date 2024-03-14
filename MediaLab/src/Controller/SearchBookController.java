package Controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class SearchBookController implements Initializable{

    @FXML
    private TextField authorField;

    @FXML
    private Button searchButton;

    @FXML
    private TextField titleField;

    @FXML
    private TextField yearField;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        
        searchButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                GeneralController.loadTemplatePage("/View/search_results.fxml");
            }
        });
    }
}