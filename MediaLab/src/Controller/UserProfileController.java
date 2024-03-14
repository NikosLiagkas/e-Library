package Controller;

import java.net.URL;
import java.util.ResourceBundle;

import Model.LibraryManager;
import Model.User;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class UserProfileController implements Initializable{

    @FXML
    private Label email;

    @FXML
    private Label firstName;

    @FXML
    private Label idNum;

    @FXML
    private Label lastName;

    @FXML
    private Label username;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        
        User user = LibraryManager.loggedUser;
        firstName.setText(user.getFirstName());
        lastName.setText(user.getLastName());
        username.setText(user.getUsername());
        email.setText(user.getEmail());
        idNum.setText(user.getIDnumber());


    }



}



