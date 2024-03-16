package Controller;

import java.net.URL;
import java.util.ResourceBundle;

import Model.LibraryManager;
import Model.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

public class AdminUsersController implements Initializable{

    @FXML
    private ListView<User> allUsersList;

private ObservableList<User> userObservableList;

    public AdminUsersController() {
        userObservableList = FXCollections.observableArrayList();

        userObservableList.addAll(
            LibraryManager.allUsers
        );

    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        allUsersList.setItems(userObservableList);
        allUsersList.setCellFactory(bookListView -> new AdminCellUsers());

    }

}