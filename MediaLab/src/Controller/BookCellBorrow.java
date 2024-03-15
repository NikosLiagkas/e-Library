package Controller;

import java.io.IOException;

import Model.Book;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

public class BookCellBorrow extends ListCell<Book> {
    private FXMLLoader mLLoader;

    @FXML
    private HBox Hbox;

    @FXML
    private Label author;

    @FXML
    private Button bookDetButton;

    @FXML
    private Button borrowButton;

    @FXML
    private Label isbn;

    @FXML
    private Label rating;

    @FXML
    private Label title;

    @FXML
    void openBookInfo(MouseEvent event) {
        BookInfoController.setSaveBook(getItem());
        GeneralController.loadTemplatePage("/View/book_info.fxml");
    }



    @Override
    protected void updateItem(Book book, boolean empty) {
        super.updateItem(book, empty);

        if(empty || book == null) {

            setText(null);
            setGraphic(null);

        } else {
            if (mLLoader == null) {
                mLLoader = new FXMLLoader(getClass().getResource("/View/cell_book_borrow.fxml"));
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
            isbn.setText(book.getISBN());
            rating.setText(String.valueOf(book.getAvgRating()));

            

            setText(null);
            setGraphic(Hbox);

        }

    }

}
