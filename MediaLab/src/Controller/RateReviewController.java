package Controller;

import java.net.URL;
import java.util.ResourceBundle;

import CustomExceptions.NotFoundException;
import Model.Book;
import Model.Category;
import Model.LibraryManager;
import Model.User;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;

public class RateReviewController implements Initializable{

    private static Book saveBook;

    @FXML
    private Label category;

    @FXML
    private Label author;

    @FXML
    private Label isBorrowActive;

    @FXML
    private Label isbn;

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
    private Label title;


    @FXML
    void submitRating(MouseEvent event) {
        User user = LibraryManager.getLoggedUser();
        ToggleButton chosenButton = (ToggleButton) rating_group.getSelectedToggle();

        if (chosenButton == null) {
            Alert a = new Alert(AlertType.ERROR);
            a.setContentText("Must choose a number to submit Rating.");
            a.showAndWait();
        }

        switch (chosenButton.getText()) {
            case "1" :
                user.rateBook(saveBook, 1);
                Alert a = new Alert(AlertType.INFORMATION);
                a.setContentText("Rating submitted succesfully.");
                a.setOnCloseRequest(s -> {
                    GeneralController.loadTemplatePage("/View/book_rate_review.fxml");
                });
                a.showAndWait();
                break;
            case "2" :
                user.rateBook(saveBook, 2);
                Alert b = new Alert(AlertType.INFORMATION);
                b.setContentText("Rating submitted succesfully.");
                b.setOnCloseRequest(s -> {
                    GeneralController.loadTemplatePage("/View/book_rate_review.fxml");
                });
                b.showAndWait();
                break;
            case "3" :
                user.rateBook(saveBook, 3);
                Alert c = new Alert(AlertType.INFORMATION);
                c.setContentText("Rating submitted succesfully.");
                c.setOnCloseRequest(s -> {
                    GeneralController.loadTemplatePage("/View/book_rate_review.fxml");
                });
                c.showAndWait();
                break;
            case "4" :
                user.rateBook(saveBook, 4);
                Alert d = new Alert(AlertType.INFORMATION);
                d.setContentText("Rating submitted succesfully.");
                d.setOnCloseRequest(s -> {
                    GeneralController.loadTemplatePage("/View/book_rate_review.fxml");
                });
                d.showAndWait();
                break;
            case "5" :
                user.rateBook(saveBook, 5);
                Alert f = new Alert(AlertType.INFORMATION);
                f.setContentText("Rating submitted succesfully.");
                f.setOnCloseRequest(s -> {
                    GeneralController.loadTemplatePage("/View/book_rate_review.fxml");
                });
                f.showAndWait();
                break;
            default:
                Alert g = new Alert(AlertType.INFORMATION);
                g.setContentText("Must choose a number to submit Rating.");
                g.showAndWait();
                break;
        }
    }

    @FXML
    void submitReview(MouseEvent event) {
        User user = LibraryManager.getLoggedUser();
        String review = reviewField.getText();
        
        if (!review.isEmpty()) {
            user.reviewBook(saveBook, review);
            Alert a = new Alert(AlertType.INFORMATION);
                a.setContentText("Review submitted succesfully.");
                a.setOnCloseRequest(s -> {
                    GeneralController.loadTemplatePage("/View/book_rate_review.fxml");
                });
                a.showAndWait();
        }
        
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {

        title.setText(saveBook.getTitle());
        author.setText(saveBook.getAuthor());
        publisher.setText(saveBook.getPublisher());
        isbn.setText(saveBook.getISBN());
        releaseYear.setText(String.valueOf(saveBook.getRelease_year()));
        rating.setText(String.valueOf(saveBook.getAvgRating()) + "  (Rated by " + saveBook.getRatings().size() + " Users)");
        
        try { 
        Category cat = LibraryManager.findBookCategory(saveBook);
        category.setText(cat.getCategory());
        }
        catch(NotFoundException e) {
            //this will never happen, all books have categories
        }

    }



    public static void setSaveBook(Book saveBook) {
        RateReviewController.saveBook = saveBook;
    }

    public static Book getSaveBook() {
        return saveBook;
    }

}