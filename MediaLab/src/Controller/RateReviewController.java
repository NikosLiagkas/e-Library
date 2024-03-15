package Controller;

import java.net.URL;
import java.util.ResourceBundle;

import Model.Book;
import Model.LibraryManager;
import Model.User;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;

public class RateReviewController implements Initializable{

    private static Book saveBook;

    @FXML
    private Label Category;

    @FXML
    private Label author;

    @FXML
    private ToggleButton five;

    @FXML
    private ToggleButton four;

    @FXML
    private Label isBorrowActive;

    @FXML
    private Label isbn;

    @FXML
    private Label numOfRatings;

    @FXML
    private ToggleButton one;

    @FXML
    private Label publisher;

    @FXML
    private Label rating;

    @FXML
    private ToggleGroup rating_group;

    @FXML
    private Label releaseYear;

    @FXML
    private TextArea reviewField;

    @FXML
    private Button subRatingButton;

    @FXML
    private Button subReviewButton;

    @FXML
    private ToggleButton three;

    @FXML
    private Label title;

    @FXML
    private ToggleButton two;

    @FXML
    void submitRating(MouseEvent event) {

    }

    @FXML
    void submitReview(MouseEvent event) {

    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        
        title.setText(saveBook.getTitle());
        author.setText(saveBook.getAuthor());
        publisher.setText(saveBook.getPublisher());
        isbn.setText(saveBook.getISBN());
        releaseYear.setText(String.valueOf(saveBook.getRelease_year()));
        rating.setText(String.valueOf((saveBook.getAvgRating())));

    }



    public static void setSaveBook(Book saveBook) {
        RateReviewController.saveBook = saveBook;
    }

    public static Book getSaveBook() {
        return saveBook;
    }

}