package Controller;

import java.net.URL;
import java.util.ResourceBundle;

import CustomExceptions.NotFoundException;
import CustomExceptions.NotUniqueException;
import Model.Admin;
import Model.LibraryManager;
import Model.User;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
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
        String Username = usernameField.getText().strip();
        String Password = passwordField.getText().strip();
        String FirstName = firstnameField.getText().strip();
        String LastName = lastnameField.getText().strip();
        String Email = emailField.getText().strip();
        String IDnum = idNumField.getText().strip();

    Admin admin = null;

        try {
            admin = LibraryManager.getAdmin(LibraryManager.loggedUser);
        }
        catch (NotFoundException e) {
            //this will not happen, but i handle it to prevent NullPointerException in case of malfuction
            Alert a = new Alert(AlertType.ERROR);
            a.setContentText("Something went wrong.");
            a.showAndWait();
            return;
        }
        
        if (!Username.isEmpty()) {
            
            try {
                admin.changeUserName(saveUser, Username);
            } 
            catch (NotUniqueException e) {
                Alert a = new Alert(AlertType.ERROR);
                a.setContentText(e.getMessage());
                a.showAndWait();
            }
        }

        if (!Password.isEmpty()) {
            admin.changeUserPassword(saveUser, Password);
        }

        if (!FirstName.isEmpty()) {
            admin.changeUserFirstName(saveUser, FirstName);
        }

        if (!LastName.isEmpty()) {
            admin.changeUserLastName(saveUser, LastName);
        }

        if (!Email.isEmpty()) {
            
            try {
                admin.changeUserEmail(saveUser, Email);
            } 
            catch (NotUniqueException e) {
                Alert a = new Alert(AlertType.ERROR);
                a.setContentText(e.getMessage());
                a.showAndWait();
            }
        }

        if (!IDnum.isEmpty()) {
            
            try {
                admin.changeUserIDnumber(saveUser, IDnum);
            } 
            catch (NotUniqueException e) {
                Alert a = new Alert(AlertType.ERROR);
                a.setContentText(e.getMessage());
                a.showAndWait();
            }
        }

        GeneralController.loadTemplatePage("/View/admin_modify_user.fxml");
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