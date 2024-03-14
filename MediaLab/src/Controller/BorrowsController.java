package Controller;

import Model.Book;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

public class BorrowsController {

    @FXML
    private ListView<Book> activeBorrowsList;

    private ObservableList<Book> activeObservableList;

    @FXML
    private ListView<Book> borrowHistoryList;

    private ObservableList<Book> historyObservableList;

}

