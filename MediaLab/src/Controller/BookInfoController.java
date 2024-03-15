package Controller;

import java.net.URL;
import java.util.ResourceBundle;

import Model.Book;
import Model.LibraryManager;
import Model.User;
import Model.Book.Reviews;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;

public class BookInfoController implements Initializable{

    private static Book saveBook;

    @FXML
    private Label author;

    @FXML
    private Button borrowButton;

    @FXML
    private Label category;

    @FXML
    private Label copies;

    @FXML
    private Label isbn;

    @FXML
    private Label numOfRatings;

    @FXML
    private Label numOfReviews;

    @FXML
    private Label publisher;

    @FXML
    private Label rating;

    @FXML
    private Label releaseYear;

    @FXML
    private ListView<Reviews> reviewsList;

    private ObservableList<Reviews> reviewsObservableList;

    @FXML
    private Label title;

     @FXML
    void borrowBook(MouseEvent event) {

    }


    public BookInfoController(){
        reviewsObservableList = FXCollections.observableArrayList();    
        
        //new b.Reviews(LibraryManager.loggedUser, "Holy Shit Amazing Book")
        //reviewsObservableList.add(
            //new saveBook.Reviews(LibraryManager.loggedUser, "Holy Shit Amazing Book")
        //);
    }

     @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        
        User user = LibraryManager.loggedUser;
        title.setText(saveBook.getTitle());
        author.setText(saveBook.getAuthor());
        publisher.setText(saveBook.getPublisher());
        isbn.setText(saveBook.getISBN());
        releaseYear.setText(String.valueOf(saveBook.getRelease_year()));
        copies.setText(String.valueOf(saveBook.getCopies()));
        rating.setText(String.valueOf((saveBook.getAvgRating())));
        //num of ratings, num of reviews, .... check wahat else is left

    }

    public static void setSaveBook(Book saveBook) {
        BookInfoController.saveBook = saveBook;
    }

    public static Book getSaveBook() {
        return saveBook;
    }

}