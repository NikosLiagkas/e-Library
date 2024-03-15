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

public class SearchResultsController implements Initializable{

    @FXML
    private ListView<Book> searchResultsList;

    private ObservableList<Book> bookObservableList;

    public SearchResultsController(){
        bookObservableList = FXCollections.observableArrayList();    
        
        bookObservableList.addAll(
            LibraryManager.allBooks
        );
    }
     @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        searchResultsList.setItems(bookObservableList);
        searchResultsList.setCellFactory(bookListView -> new BookCellBorrow());
    }

}