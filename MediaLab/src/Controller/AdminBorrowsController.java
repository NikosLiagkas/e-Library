package Controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import CustomExceptions.NotFoundException;
import Model.Admin;
import Model.BorrowedBooks;
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

    

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        borrBookObservableList = FXCollections.observableArrayList();

        List<BorrowedBooks> ActiveBorrowedBooks;
        try {
            Admin admin = LibraryManager.getAdmin(LibraryManager.loggedUser);
        
            ActiveBorrowedBooks = admin.seeAllActiveBorrows();

            

            borrBookObservableList.addAll(
                ActiveBorrowedBooks
            );

        }
        catch (NotFoundException e) {
            
        }

        allBorrowsList.setItems(borrBookObservableList);
        allBorrowsList.setCellFactory(bookListView -> new AdminCellBorrows());

    }


}