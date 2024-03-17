package Controller;

import java.io.IOException;

import CustomExceptions.BorrowLimitException;
import CustomExceptions.NoCopiesException;
import Model.Book;
import Model.LibraryManager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
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

    @FXML
    void borrowBook(MouseEvent event) {
        try{
            LibraryManager.getLoggedUser().borrowBook(getItem());
            GeneralController.loadTemplatePage("/View/borrows.fxml");
        }
        catch (BorrowLimitException | NoCopiesException e) {
            Alert a = new Alert(AlertType.ERROR);
            a.setContentText(e.getMessage());
            a.showAndWait();
        }
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
            rating.setText(String.valueOf(book.getAvgRating()+ "  (Rated by " + book.getRatings().size() + " Users)"));

            

            setText(null);
            setGraphic(Hbox);

        }

    }

}
