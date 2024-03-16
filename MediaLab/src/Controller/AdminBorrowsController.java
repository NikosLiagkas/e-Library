package Controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import Model.Admin;
import Model.BorrowedBooks;
import Model.Category;
import Model.LibraryManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

public class AdminBorrowsController implements Initializable{

    @FXML
    private ListView<BorrowedBooks> allBorrowsList;

    private ObservableList<BorrowedBooks> borrBookObservableList;

    public AdminBorrowsController() {
        borrBookObservableList = FXCollections.observableArrayList();

        List<BorrowedBooks> ActiveBorrowedBooks;
        Admin admin = LibraryManager.getAdmin(LibraryManager.loggedUser);
        
        ActiveBorrowedBooks = admin.seeAllActiveBorrows();

        borrBookObservableList.addAll(
            ActiveBorrowedBooks
        );

    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        allBorrowsList.setItems(borrBookObservableList);
        allBorrowsList.setCellFactory(bookListView -> new AdminCellBorrows());

    }


}