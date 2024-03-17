package Controller;

import java.net.URL;
import java.util.ResourceBundle;

import Model.Book;
import Model.BorrowedBooks;
import Model.LibraryManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

public class BorrowsController implements Initializable{

    @FXML
    private ListView<BorrowedBooks> activeBorrowsList;

    @FXML
    private ListView<BorrowedBooks> borrowHistoryList;

    private ObservableList<BorrowedBooks> historyObservableList;
    private ObservableList<BorrowedBooks> activeObservableList;



    public BorrowsController(){
        activeObservableList = FXCollections.observableArrayList();
        historyObservableList = FXCollections.observableArrayList();    
        
        for (BorrowedBooks bb : LibraryManager.allBorrowedBooks) {
            if (bb.getActiveBorrow()==true) {
                activeObservableList.add(bb);
            }
            else {
                historyObservableList.add(bb);
            }
        }
    }
    

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        activeBorrowsList.setItems(activeObservableList);
        activeBorrowsList.setCellFactory(bookListView -> new BookCellRateReview());

        borrowHistoryList.setItems(historyObservableList);
        borrowHistoryList.setCellFactory(bookListView -> new BookCellRateReview());
    }
}

