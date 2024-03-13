package Controller;

import java.io.IOException;

import Model.Book;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.HBox;

public class BookCellSimple extends ListCell<Book> {
    private FXMLLoader mLLoader;

    @FXML
    private Label ISBN;

    @FXML
    private Label author;

    @FXML
    private Label rating;

    @FXML
    private Label title;

    @FXML
    private HBox Hbox;

    @Override
    protected void updateItem(Book book, boolean empty) {
        super.updateItem(book, empty);

        if(empty || book == null) {

            setText(null);
            setGraphic(null);

        } else {
            if (mLLoader == null) {
                mLLoader = new FXMLLoader(getClass().getResource("/View/cell_book_simple.fxml"));
                mLLoader.setController(this);

                try {
                    mLLoader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
            Hbox.prefWidthProperty().bind(getListView().widthProperty());
            title.setText(book.getTitle());
            author.setText(book.getAuthor());
            ISBN.setText(book.getISBN());
            rating.setText(String.valueOf(book.getAvgRating()));

            setText(null);
            setGraphic(Hbox);

        }

    }

}
    

