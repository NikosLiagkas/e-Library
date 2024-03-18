package Controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import Model.Book;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

public class SearchResultsController implements Initializable{

    private static List<Book> saveSearchList;

    @FXML
    private ListView<Book> searchResultsList;

    private ObservableList<Book> bookObservableList;

    public SearchResultsController(){
        bookObservableList = FXCollections.observableArrayList();    
        
        bookObservableList.addAll(
            saveSearchList
        );
    }
     @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        searchResultsList.setItems(bookObservableList);
        searchResultsList.setCellFactory(bookListView -> new BookCellBorrow());
    }

    public static void setSaveSearchList(List<Book> saveSearchList) {
        SearchResultsController.saveSearchList = saveSearchList;
    }

    public static List<Book> getSaveSearchList() {
        return saveSearchList;
    }

}