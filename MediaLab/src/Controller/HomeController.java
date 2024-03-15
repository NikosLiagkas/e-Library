package Controller;

import javafx.event.EventHandler;
import java.net.URL;
import java.util.ResourceBundle;

import Model.Book;
import Model.LibraryManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;


public class HomeController implements Initializable{

    @FXML
    private Button loginButton;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button signupButton;

    @FXML
    private ListView<Book> topRatedList;

    private ObservableList<Book> bookObservableList;

    
    public HomeController(){
        bookObservableList = FXCollections.observableArrayList();    
        
        bookObservableList.addAll(
            LibraryManager.allBooks
        );
    }

    @FXML
    private TextField usernameField;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        
        signupButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                GeneralController.loadPage("/View/register_form.fxml");
            }
        });

        //For my top 5 book list
        topRatedList.setItems(bookObservableList);
        topRatedList.setCellFactory(bookListView -> new BookCellSimple());



    }



}
