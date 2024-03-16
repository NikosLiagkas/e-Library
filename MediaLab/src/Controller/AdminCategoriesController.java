package Controller;

import java.net.URL;
import java.util.ResourceBundle;

import Model.Category;
import Model.LibraryManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
