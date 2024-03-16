package Controller;

import java.io.IOException;

import Model.Category;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

public class AdminCellCategories extends ListCell<Category>{
    private FXMLLoader mLLoader;

    @FXML
    private HBox Hbox;

    @FXML
    private Label catTitle;

    @FXML
    private Button deleteButton;

    @FXML
    private Button submitButton;

    @FXML
    void changeCatName(MouseEvent event) {

    }

    @FXML
    void deleteCategory(MouseEvent event) {

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
