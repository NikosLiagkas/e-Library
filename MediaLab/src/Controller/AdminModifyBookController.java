package Controller;

import java.net.URL;
import java.util.ResourceBundle;

import CustomExceptions.DateException;
import CustomExceptions.NegativeCopiesException;
import CustomExceptions.NotFoundException;
import CustomExceptions.NotUniqueException;
import Model.Admin;
import Model.Book;
import Model.LibraryManager;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
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
        
        String title = titleField.getText().strip();
        String author = authorField.getText().strip();
        String publisher = publisherField.getText().strip();
        String isbn = isbnField.getText().strip();
        String releaseYear = releaseYearField.getText().strip();
        String copies = copiesField.getText().strip();
        String category = categoryField.getText().strip();

        Admin admin = null;

        try {
            admin = LibraryManager.getAdmin(LibraryManager.loggedUser);
        }
        catch (NotFoundException e) {
            //this will not happen, but i handle it to prevent NullPointerException in case of malfuction
            Alert a = new Alert(AlertType.ERROR);
            a.setContentText("Something went wrong.");
            a.showAndWait();
            return;
        }

        if (!title.isEmpty()) {
            admin.changeBookName(saveBook, title);
        }

        if (!author.isEmpty()) {
            admin.changeBookAuthor(saveBook, author);
        }

        if (!publisher.isEmpty()) {
            admin.changeBookPublisher(saveBook, publisher);
        }

        if (!isbn.isEmpty()) {
            try {
                admin.changeBookISBN(saveBook, isbn);
            }
            catch (NotUniqueException e) {
                Alert a = new Alert(AlertType.ERROR);
                a.setContentText(e.getMessage());
                a.showAndWait();
            }
        }

        if (!releaseYear.isEmpty()) {
            
            try {
                int intYear = Integer.parseInt(releaseYear);
                admin.changeBookReleaseYear(saveBook, intYear);
            } 
            catch (DateException e) {
                Alert a = new Alert(AlertType.ERROR);
                a.setContentText("Invalid Year. Please enter a valid integer that does not represent a future year.");
                a.showAndWait();
            }
            catch (NumberFormatException e) {
                Alert a = new Alert(AlertType.ERROR);
                a.setContentText("Invalid Integer. Please enter a valid integer in the year field.");
                a.showAndWait();
            }
        }

        if (!copies.isEmpty()) {
            
            try {
                int intCopies = Integer.parseInt(copies);
                admin.changeBookCopies(saveBook, intCopies);
            } 
            catch (NegativeCopiesException e) {
                Alert a = new Alert(AlertType.ERROR);
                a.setContentText(e.getMessage());
                a.showAndWait();
            }
            catch (NumberFormatException e) {
                Alert a = new Alert(AlertType.ERROR);
                a.setContentText("Invalid Integer. Please enter a valid integer in the copies field.");
                a.showAndWait();
            }
        }

        if (!category.isEmpty()) {
            try {
                admin.changeBookCategory(saveBook, category);
            } 
            catch (NotFoundException e) {
                Alert a = new Alert(AlertType.ERROR);
                a.setContentText("Invalid Category. Please enter an existing category.");
                a.showAndWait();           
            }
            
        }
        GeneralController.loadTemplatePage("/View/admin_modify_book.fxml");

    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        try{
            title.setText(saveBook.getTitle());
            author.setText(saveBook.getAuthor());
            publisher.setText(saveBook.getPublisher());
            isbn.setText(saveBook.getISBN());
            releaseYear.setText(String.valueOf(saveBook.getRelease_year()));
            copies.setText(String.valueOf(saveBook.getCopies()));
            category.setText(LibraryManager.findBookCategory(saveBook).getCategory());
        }
        catch (NotFoundException e) {
            //will not happen
        }
    }    
    
    public static void setSaveBook(Book saveBook) {
        AdminModifyBookController.saveBook = saveBook;
    }

    public static Book getSaveBook() {
        return saveBook;
    }
}

