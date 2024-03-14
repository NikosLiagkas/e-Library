package Controller;

import java.net.URL;
import java.util.ResourceBundle;

import Model.Book;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

public class UserHomepageController implements Initializable{

    @FXML
    private ListView<Book> allBooksList;

    private ObservableList<Book> bookObservableList;

     public UserHomepageController(){
        bookObservableList = FXCollections.observableArrayList();    
        
        bookObservableList.addAll(
            new Book("book1", "Nik", "Liagito", "1234", 1936, "greek", 5),
            new Book("book2", "null", "null", "null", 0, "null", 0)
        );
    }
     @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        allBooksList.setItems(bookObservableList);
        allBooksList.setCellFactory(bookListView -> new BookCellBorrow());
    }

}
