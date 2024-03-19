package Controller;


import Model.LibraryManager;
import Model.User;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class SignUpController {

    @FXML
    private Button backButton;

    @FXML
    private PasswordField confirmPasswordField;

    @FXML
    private TextField emailField;

    @FXML
    private TextField firstNameField;

    @FXML
    private TextField idNumberField;

    @FXML
    private TextField lastNameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button signupButton;

    @FXML
    private TextField usernameField;

    @FXML
    void SignUp(MouseEvent event) {
        String email = emailField.getText().strip();
        String FirstName = firstNameField.getText().strip();
        String LastName = lastNameField.getText().strip();
        String username = usernameField.getText().strip();
        String password = passwordField.getText().strip();
        String confirmPassword = confirmPasswordField.getText().strip();
        String IDnum = idNumberField.getText().strip();

        
        if (email.isEmpty() || FirstName.isEmpty() || LastName.isEmpty() || username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty() || IDnum.isEmpty()) {
            Alert a = new Alert(AlertType.ERROR);
                a.setContentText("Must fill in all fields.");
                a.showAndWait();
                return;
        }
        else {
            if (!password.equals(confirmPassword)) {
                Alert a = new Alert(AlertType.ERROR);
                a.setContentText("Password and Confirm Password must be the same.");
                a.showAndWait();
                return;
            }
            for (User u : LibraryManager.allUsers){
                if (u.getUsername().equals(username)){
                    Alert a = new Alert(AlertType.ERROR);
                    a.setContentText("Username already taken. Please choose another one.");
                    a.showAndWait();
                    return;
                }
            }
            for (User u : LibraryManager.allUsers){
                if (u.getIDnumber().equals(IDnum)){
                    Alert a = new Alert(AlertType.ERROR);
                    a.setContentText("ID number already taken. Please enter a unique ID number.");
                    a.showAndWait();
                    return;
                }
            }
            for (User u : LibraryManager.allUsers){
                if (u.getEmail().equals(email)){
                    Alert a = new Alert(AlertType.ERROR);
                    a.setContentText("Email already taken. Please enter a unique Email.");
                    a.showAndWait();
                    return;
                }
            }

            User u = new User(username, password, FirstName, LastName, IDnum, email);
            LibraryManager.allUsers.add(u);
            Alert a = new Alert(AlertType.INFORMATION);
            a.setContentText("Sign up was succesful. You can now login.");
            a.setOnCloseRequest(s -> {
                GeneralController.loadPage("/View/home.fxml");
            });
            a.showAndWait();
        }
    }

    @FXML
    void openLoginPage(MouseEvent event) {
        GeneralController.loadPage("/View/home.fxml");
    }
  
}
