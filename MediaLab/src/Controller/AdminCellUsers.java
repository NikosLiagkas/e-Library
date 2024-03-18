package Controller;

import java.io.IOException;

import CustomExceptions.NotFoundException;
import Model.Admin;
import Model.LibraryManager;
import Model.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

public class AdminCellUsers extends ListCell<User> {
    private FXMLLoader mLLoader;

    @FXML
    private HBox Hbox;

    @FXML
    private Button deleteButton;

    @FXML
    private Label firstname;

    @FXML
    private Label idNum;

    @FXML
    private Label lastname;

    @FXML
    private Button modifyButton;

    @FXML
    private Label username;

    @FXML
    void deleteUser(MouseEvent event) {
        try{
            Admin admin = LibraryManager.getAdmin(LibraryManager.loggedUser);
            admin.deleteUser(getItem());
            Alert a = new Alert(AlertType.INFORMATION);
            a.setContentText(" User succesfully deleted.");
            a.setOnCloseRequest(s -> {
                GeneralController.loadTemplatePage("/View/admin_all_users.fxml");
            });
            a.showAndWait();
        }
        catch (NotFoundException e) {
            //will never happen
        }
    }

    @FXML
    void openModifyUser(MouseEvent event) {
        AdminModifyUserController.setSaveUser(getItem());
        GeneralController.loadTemplatePage("/View/admin_modify_user.fxml");
    }

    @Override
    protected void updateItem(User user, boolean empty) {
        super.updateItem(user, empty);

        if(empty || user == null) {

            setText(null);
            setGraphic(null);

        } else {
            if (mLLoader == null) {
                mLLoader = new FXMLLoader(getClass().getResource("/View/admin_cell_users.fxml"));
                mLLoader.setController(this);

                try {
                    mLLoader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
            Hbox.prefWidthProperty().bind(getListView().widthProperty());

            username.setText(user.getUsername());
            firstname.setText(user.getFirstName());
            lastname.setText(user.getLastName());
            idNum.setText(user.getIDnumber());


            setText(null);
            setGraphic(Hbox);

        }

    }
}
