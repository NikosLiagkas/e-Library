package Controller;

import java.net.URL;
import java.util.ResourceBundle;

import Model.Book;
import Model.LibraryManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

public class BorrowsController implements Initializable{

    @FXML
    private ListView<Book> activeBorrowsList;

    private ObservableList<Book> activeObservableList;

    @FXML
    private ListView<Book> borrowHistoryList;

    private ObservableList<Book> historyObservableList;



    public BorrowsController(){
        activeObservableList = FXCollections.observableArrayList();
        historyObservableList = FXCollections.observableArrayList();    
       
        activeObservableList.addAll(
            LibraryManager.allBooks
        );

        historyObservableList.addAll(
            LibraryManager.allBooks
        );
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        activeBorrowsList.setItems(activeObservableList);
        activeBorrowsList.setCellFactory(bookListView -> new BookCellRateReview());

        borrowHistoryList.setItems(historyObservableList);
        borrowHistoryList.setCellFactory(bookListView -> new BookCellRateReview());
    }
}

