package Controller;

import java.net.URL;
import java.util.ResourceBundle;

import CustomExceptions.NotFoundException;
import Model.Book;
import Model.LibraryManager;
import Model.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;


public class HomeController implements Initializable{

    @FXML
    private Button loginButton;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button signupButton;

    @FXML
    private TextField usernameField;

    @FXML
    private ListView<Book> topRatedList;

    private ObservableList<Book> bookObservableList;

    @FXML
    void Login(MouseEvent event) {
        String username = usernameField.getText().strip();
        String password = passwordField.getText().strip();

        try{
            User u = LibraryManager.login(username, password);
            LibraryManager.setLoggedUser(u);
            
            if (u.getIsAdmin() == true) {
                GeneralController.loadPage("/View/admin_template.fxml");
            }
            else {
                GeneralController.loadPage("/View/user_template.fxml");
            }
        }
        catch (NotFoundException e) {
            Alert a = new Alert(AlertType.ERROR);
                a.setContentText(e.getMessage());
                a.setOnCloseRequest(s -> {
                    GeneralController.loadPage("/View/home.fxml");
                });
                a.showAndWait();
        }
    }

    @FXML
    void openRegisterPage(MouseEvent event) {
        GeneralController.loadPage("/View/register_form.fxml");
    }

    
    public HomeController(){
        bookObservableList = FXCollections.observableArrayList();    
        
        bookObservableList.addAll(
            LibraryManager.top5books()
        );
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        
        //For my top 5 book list
        topRatedList.setItems(bookObservableList);
        topRatedList.setCellFactory(bookListView -> new BookCellSimple());
    }



}
