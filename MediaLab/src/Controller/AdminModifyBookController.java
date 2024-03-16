package Controller;

import java.net.URL;
import java.util.ResourceBundle;

import Model.Book;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class AdminModifyBookController implements Initializable{

    private static Book saveBook;

    @FXML
    private Label author;

    @FXML
    private TextField authorField;

    @FXML
    private Label category;

    @FXML
    private TextField categoryField;

    @FXML
    private Label copies;

    @FXML
    private TextField copiesField;

    @FXML
    private Label isbn;

    @FXML
    private TextField isbnField;

    @FXML
    private Label publisher;

    @FXML
    private TextField publisherField;

    @FXML
    private Label releaseYear;

    @FXML
    private TextField releaseYearField;

    @FXML
    private Button submitButton;

    @FXML
    private Label title;

    @FXML
    private TextField titleField;

    @FXML
    void submitBookChanges(MouseEvent event) {

    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        title.setText(saveBook.getTitle());
        author.setText(saveBook.getAuthor());
        publisher.setText(saveBook.getPublisher());
        isbn.setText(saveBook.getISBN());
        releaseYear.setText(String.valueOf(saveBook.getRelease_year()));
        copies.setText(String.valueOf(saveBook.getCopies()));
        //fixcategory
    }    
    
    public static void setSaveBook(Book saveBook) {
        AdminModifyBookController.saveBook = saveBook;
    }

    public static Book getSaveBook() {
        return saveBook;
    }
}

