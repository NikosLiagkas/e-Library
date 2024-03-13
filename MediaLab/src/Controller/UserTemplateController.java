package Controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;

public class UserTemplateController implements Initializable{

    @FXML
    private BorderPane template;

    @FXML
    private Button borrowsButton;

    @FXML
    private Button homeButton;

    @FXML
    private MenuItem logout;

    @FXML
    private MenuItem profInfo;

    @FXML
    private Button searchBookButton;


   @Override
    public void initialize(URL arg0, ResourceBundle arg1) {

        GeneralController.setTemplate(template);
        
        searchBookButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                GeneralController.loadTemplatePage("/View/search_book.fxml");
            }
        });

        borrowsButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                GeneralController.loadTemplatePage("/View/borrows.fxml");
            }
        });

        homeButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                GeneralController.loadTemplatePage("/View/all_books.fxml");
            }
        });

        logout.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
               // GeneralController.setBook(null);
                //GeneralController.setTemplate(null);

                //fix logged user !!!!!
              
                GeneralController.loadPage("/View/home.fxml");
            }
        });

        profInfo.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                GeneralController.loadPage("/View/profile_info.fxml");
            }
        });

    }   
}
