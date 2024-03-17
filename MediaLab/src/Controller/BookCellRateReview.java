package Controller;

import java.io.IOException;

import CustomExceptions.NotFoundException;
import Model.Book;
import Model.BorrowedBooks;
import Model.LibraryManager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

public class BookCellRateReview extends ListCell<BorrowedBooks>{
    private FXMLLoader mLLoader;

    @FXML
    private Label author;

    @FXML
    private Button bookDetailsButton;

    @FXML
    private HBox hbox;

    @FXML
    private Label isbn;

    @FXML
    private Label rating;

    @FXML
    private Button reviewButton;

    @FXML
    private Label title;

    @FXML
    private Label borrowDate;

    @FXML
    private Label returnDate;

    @FXML
    private Label isBorrowActive;
    
    @FXML
    void openBookInfo(MouseEvent event) {
        try{
            Book book = LibraryManager.findBook(getItem().getBookISBN());
            BookInfoController.setSaveBook(book);
            GeneralController.loadTemplatePage("/View/book_info.fxml");
        }
        catch (NotFoundException e) {
            Alert a = new Alert(AlertType.ERROR);
            a.setContentText(e.getMessage());
            a.setOnCloseRequest(s -> {
                GeneralController.loadTemplatePage("/View/borrows.fxml");
            });
            a.showAndWait();
        }
    }

    @FXML
    void openRateReview(MouseEvent event) {
        try {
            Book book = LibraryManager.findBook(getItem().getBookISBN());
            RateReviewController.setSaveBook(book);
            GeneralController.loadTemplatePage("/View/book_rate_review.fxml");
        }
        catch (NotFoundException e) {
            Alert a = new Alert(AlertType.ERROR);
            a.setContentText(e.getMessage());
            a.setOnCloseRequest(s -> {
                GeneralController.loadTemplatePage("/View/borrows.fxml");
            });
            a.showAndWait();
        }
    }


    @Override
    protected void updateItem(BorrowedBooks bb, boolean empty) {
        super.updateItem(bb, empty);

        if(empty || bb == null) {

            setText(null);
            setGraphic(null);

        } 
        else {
            if (mLLoader == null) {
                mLLoader = new FXMLLoader(getClass().getResource("/View/cell_book_ratereview.fxml"));
                mLLoader.setController(this);

                try {
                    mLLoader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
            
            try {
                Book book = LibraryManager.findBook(bb.getBookISBN());
                hbox.prefWidthProperty().bind(getListView().widthProperty());
                title.setText(book.getTitle());
                author.setText(book.getAuthor());
                isbn.setText(bb.getBookISBN());
                rating.setText(String.valueOf(book.getAvgRating() + "  (Rated by " + book.getRatings().size() + " Users)"));
                borrowDate.setText(String.valueOf(bb.getBorrowDate()));
                returnDate.setText(String.valueOf(bb.getReturnDate()));
                if (bb.getActiveBorrow()==true) {
                    isBorrowActive.setText("Yes");
                }
                else {
                    isBorrowActive.setText("No");
                }
            }
            catch (NotFoundException e) {
                Alert a = new Alert(AlertType.ERROR);
                a.setContentText(e.getMessage());
                a.setOnCloseRequest(s -> {
                    GeneralController.loadTemplatePage("/View/borrows.fxml");
                });
                a.showAndWait();
            }
            
            
            
            setText(null);
            setGraphic(hbox);

        }

    }

}