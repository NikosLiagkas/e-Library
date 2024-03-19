package Controller;

import java.util.List;

import Model.Book;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

public class AdminBooksByCategoryController {

    @FXML
    private ListView<Book> bookList;

    private ObservableList<Book> bookObservableList;

    @FXML
    private Label catName;

    public void setBookListData(List<Book> allCatBooks, String category) {
        this.catName.setText(category);

        bookObservableList = FXCollections.observableArrayList();

        bookObservableList.addAll(allCatBooks);

        bookList.setItems(bookObservableList);
        bookList.setCellFactory(booklist -> new AdminCellBooks());

        
    }



}