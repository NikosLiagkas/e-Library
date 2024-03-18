package Controller;

import CustomExceptions.DateException;
import CustomExceptions.NegativeCopiesException;
import CustomExceptions.NotFoundException;
import Model.Book;
import Model.Category;
import Model.LibraryManager;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class AdminCreateBookController {

    @FXML
    private TextField authorField;

    @FXML
    private TextField categoryField;

    @FXML
    private TextField copiesField;

    @FXML
    private Button createBookButton;

    @FXML
    private TextField isbnField;

    @FXML
    private TextField publisherField;

    @FXML
    private TextField titleField;

    @FXML
    private TextField yearField;

    @FXML
    void CreateBook(MouseEvent event) {
        String title = titleField.getText().strip();
        String author = authorField.getText().strip();
        String publisher = publisherField.getText().strip();
        String isbn = isbnField.getText().strip();
        String releaseYear = yearField.getText().strip();
        String copies = copiesField.getText().strip();
        String category = categoryField.getText().strip();
        
        


        if (title.isEmpty() || author.isEmpty() || publisher.isEmpty() || isbn.isEmpty() || releaseYear.isEmpty() || copies.isEmpty() || category.isEmpty()) {
            Alert a = new Alert(AlertType.ERROR);
                a.setContentText("Must fill in all fields.");
                a.showAndWait();
                return;
        }
        else{

            try{
                int intCopies = Integer.parseInt(copies);
                int intYear = Integer.parseInt(releaseYear);

                if (intYear > java.time.LocalDate.now().getYear()) {
                    throw new DateException("Invalid Year. Please enter a valid integer that does not represent a future year.");
                }

                if (intCopies < 0) {
                    throw new NegativeCopiesException();
                }

                for (Book b : LibraryManager.allBooks) {
                    if (b.getISBN().equals(isbn)){
                        Alert a = new Alert(AlertType.ERROR);
                        a.setContentText("ISBN already taken. Please enter a unique ISBN.");
                        a.showAndWait();
                        return;    
                    }
                }

                Category cat = LibraryManager.findCategory(category);

                
                Book b = new Book(title, author, publisher, isbn, intYear, intCopies);
                
                LibraryManager.allBooks.add(b);
                cat.addBookToCategory(isbn);

                Alert a = new Alert(AlertType.INFORMATION);
                a.setContentText("Book created successfully.");
                a.setOnCloseRequest(s -> {
                    GeneralController.loadTemplatePage("/View/admin_all_books.fxml");
                });
                a.showAndWait();

            }
            catch (NumberFormatException e) {
                Alert a = new Alert(AlertType.ERROR);
                a.setContentText("Invalid Integer. Please enter a valid integer in the year and/or copies field.");
                a.showAndWait();
            }
            catch (NegativeCopiesException | DateException e) {
                Alert a = new Alert(AlertType.ERROR);
                a.setContentText(e.getMessage());
                a.showAndWait();
            }
            catch (NotFoundException e) {
                Alert a = new Alert(AlertType.ERROR);
                a.setContentText("Invalid Category. Please enter an existing category.");
                a.showAndWait();
            }
        }
        
    }

}