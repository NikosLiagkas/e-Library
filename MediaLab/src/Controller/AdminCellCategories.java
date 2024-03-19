package Controller;

import java.io.IOException;

import CustomExceptions.NotFoundException;
import CustomExceptions.NotUniqueException;
import Model.Admin;
import Model.Category;
import Model.LibraryManager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

public class AdminCellCategories extends ListCell<Category>{
    private FXMLLoader mLLoader;

    @FXML
    private HBox Hbox;

    @FXML
    private Label catTitle;

    @FXML
    private TextField changeCatNameField;

    @FXML
    private Button deleteButton;

    @FXML
    private Button submitButton;

    @FXML
    void changeCatName(MouseEvent event) {
        String newCatName = changeCatNameField.getText();

        if (newCatName.isEmpty()) {
            return;
        }
        else {
            try{
                Admin admin = LibraryManager.getAdmin(LibraryManager.loggedUser);
                admin.changeCategoryName(getItem(), newCatName);
                Alert a = new Alert(AlertType.INFORMATION);
                a.setContentText("Category Name changed succesfully.");
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

    @FXML
    void deleteCategory(MouseEvent event) {
        try{
            Admin admin = LibraryManager.getAdmin(LibraryManager.loggedUser);
            admin.deleteCategory(getItem());
            Alert a = new Alert(AlertType.INFORMATION);
            a.setContentText("Category succesfully deleted.");
            a.setOnCloseRequest(s -> {
                GeneralController.loadTemplatePage("/View/admin_all_categories.fxml");
            });
            a.showAndWait();
        }
        catch (NotFoundException e) {
            //will never happen
        }
    }

    @Override
    protected void updateItem(Category category, boolean empty) {
        super.updateItem(category, empty);

        if(empty || category == null) {

            setText(null);
            setGraphic(null);

        } else {
            if (mLLoader == null) {
                mLLoader = new FXMLLoader(getClass().getResource("/View/admin_cell_categories.fxml"));
                mLLoader.setController(this);

                try {
                    mLLoader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
            Hbox.prefWidthProperty().bind(getListView().widthProperty());

            catTitle.setText(category.getCategory());         

            setText(null);
            setGraphic(Hbox);

        }

    }
}
