package Controller;

import java.net.URL;
import java.util.ResourceBundle;

import CustomExceptions.NotFoundException;
import CustomExceptions.NotUniqueException;
import Model.Admin;
import Model.Category;
import Model.LibraryManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class AdminCategoriesController implements Initializable {
    
    @FXML
    private ListView<Category> allCategoriesList;

    private ObservableList<Category> categoryObservableList;


    @FXML
    private TextField categoryField;

    @FXML
    private Button createButton;

    @FXML
    void createCategory(MouseEvent event) {
        String catName = categoryField.getText();
        
        if (catName.isEmpty()) {
            return;
        }
        else {
            try{
                Admin admin = LibraryManager.getAdmin(LibraryManager.loggedUser);
                admin.createCategory(catName);
                Alert a = new Alert(AlertType.INFORMATION);
                a.setContentText("Category has been added succesfully.");
                a.setOnCloseRequest(s -> {
                    GeneralController.loadTemplatePage("/View/admin_all_categories.fxml");
                });
                a.showAndWait();
            }
            catch (NotUniqueException e) {
                Alert a = new Alert(AlertType.ERROR);
                a.setContentText(e.getMessage());
                a.setOnCloseRequest(s -> {
                    GeneralController.loadTemplatePage("/View/admin_all_categories.fxml");
                });
                a.showAndWait();
            }
            catch (NotFoundException e) {
                //will not happen
            }
        }
    }

    public AdminCategoriesController() {
        categoryObservableList = FXCollections.observableArrayList();

        categoryObservableList.addAll(
            LibraryManager.allCategories
        );

    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        allCategoriesList.setItems(categoryObservableList);
        allCategoriesList.setCellFactory(bookListView -> new AdminCellCategories());

    }

}
