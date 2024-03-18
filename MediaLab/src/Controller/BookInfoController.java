package Controller;

import java.net.URL;
import java.util.ResourceBundle;

import CustomExceptions.BorrowLimitException;
import CustomExceptions.NoCopiesException;
import CustomExceptions.NotFoundException;
import Model.Book;
import Model.Category;
import Model.LibraryManager;
import Model.Book.Reviews;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
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
        try{
            LibraryManager.getLoggedUser().borrowBook(saveBook);
            GeneralController.loadTemplatePage("/View/borrows.fxml");
        }
        catch (BorrowLimitException | NoCopiesException e) {
            Alert a = new Alert(AlertType.ERROR);
            a.setContentText(e.getMessage());
            a.showAndWait();
        }
    }


    public BookInfoController(){
        reviewsObservableList = FXCollections.observableArrayList();    
        reviewsObservableList.addAll(saveBook.getReviews());   
    }
    
    
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        
        title.setText(saveBook.getTitle());
        author.setText(saveBook.getAuthor());
        publisher.setText(saveBook.getPublisher());
        isbn.setText(saveBook.getISBN());
        releaseYear.setText(String.valueOf(saveBook.getRelease_year()));
        copies.setText(String.valueOf(saveBook.getCopies()));
        rating.setText(String.valueOf(saveBook.getAvgRating()) + "  (Rated by " + saveBook.getRatings().size() + " Users)");
        numOfReviews.setText("Rated by " + saveBook.getReviews().size() + " Users");
        try { 
        Category cat = LibraryManager.findBookCategory(saveBook);
        category.setText(cat.getCategory());
        }
        catch(NotFoundException e) {
            //this will never happen, all books have categories
        }

        reviewsList.setItems(reviewsObservableList);
        reviewsList.setCellFactory(bookListView -> new ReviewCell());

    }

    public static void setSaveBook(Book saveBook) {
        BookInfoController.saveBook = saveBook;
    }

    public static Book getSaveBook() {
        return saveBook;
    }

}