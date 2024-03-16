package Controller;

import java.net.URL;
import java.util.ResourceBundle;

import Model.LibraryManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;

public class AdminTemplateController implements Initializable{

    @FXML
    private MenuItem adminInfo;

    @FXML
    private Button booksButton;

    @FXML
    private Button borrowsButton;

    @FXML
    private Button categoriesButton;

    @FXML
    private MenuItem logout;

    @FXML
    private BorderPane template;

    @FXML
    private Button usersButton;

    @FXML
    void LogOut(ActionEvent event) {
        GeneralController.setBook(null);
        GeneralController.setTemplate(null);

        LibraryManager.setLoggedUser(null);
              
        GeneralController.loadPage("/View/home.fxml");
    }

    @FXML
    void openAdminInfo(ActionEvent event) {
        GeneralController.loadTemplatePage("/View/profile_info.fxml");
    }

    @FXML
    void openManageBooks(MouseEvent event) {
        GeneralController.loadTemplatePage("/View/admin_all_books.fxml");
    }

    @FXML
    void openManageBorrows(MouseEvent event) {
        GeneralController.loadTemplatePage("/View/admin_all_borrows.fxml");
    }

    @FXML
    void openManageCategories(MouseEvent event) {
        GeneralController.loadTemplatePage("/View/admin_all_categories.fxml");
    }

    @FXML
    void openManageUsers(MouseEvent event) {
        GeneralController.loadTemplatePage("/View/admin_all_users.fxml");
    }


    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        GeneralController.setTemplate(template);
    }
}
