package Controller;

import java.net.URL;
import java.util.ResourceBundle;

import Model.User;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class AdminModifyUserController implements Initializable {

    private static User saveUser;

    @FXML
    private Label email;

    @FXML
    private TextField emailField;

    @FXML
    private Label firstname;

    @FXML
    private TextField firstnameField;

    @FXML
    private TextField idNumField;

    @FXML
    private Label idnumber;

    @FXML
    private Label lastname;

    @FXML
    private TextField lastnameField;

    @FXML
    private Label password;

    @FXML
    private TextField passwordField;

    @FXML
    private Button submitButton;

    @FXML
    private Label username;

    @FXML
    private TextField usernameField;

    @FXML
    void submitUserChanges(MouseEvent event) {

    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        username.setText(saveUser.getUsername());
        firstname.setText(saveUser.getFirstName());
        lastname.setText(saveUser.getLastName());
        password.setText(saveUser.getPassword());
        email.setText(saveUser.getEmail());
        idnumber.setText(saveUser.getIDnumber());
    }

    public static void setSaveUser(User saveUser) {
        AdminModifyUserController.saveUser = saveUser;
    }

    public static User getSaveUser() {
        return saveUser;
    }

}