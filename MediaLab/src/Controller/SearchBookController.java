package Controller;

import java.util.List;

import CustomExceptions.DateException;
import Model.Book;
import Model.LibraryManager;
import Model.User;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class SearchBookController {

    @FXML
    private TextField authorField;

    @FXML
    private Button searchButton;

    @FXML
    private TextField titleField;

    @FXML
    private TextField yearField;

    @FXML
    void search(MouseEvent event) {
        String title = titleField.getText().strip();
        String author = authorField.getText().strip();
        String year = yearField.getText().strip();

        User u = LibraryManager.loggedUser;

        if (title.isEmpty() && author.isEmpty() && year.isEmpty()){
            return;
        }

        if (!year.isEmpty()) {
            try {
                int intYear = Integer.parseInt(year);

                if (intYear > java.time.LocalDate.now().getYear()) {
                    throw new DateException("Invalid Year. Please enter an integer that does not represent a future year.");
                }

                if (title.isEmpty() && author.isEmpty()){
                    List<Book> result = u.searchBookByYear(intYear);
                    SearchResultsController.setSaveSearchList(result);
                    GeneralController.loadTemplatePage("/View/search_results.fxml");
                }

                if (!title.isEmpty() && author.isEmpty()){
                    List<Book> result = u.CombSearchTitleYear(title, intYear);
                    SearchResultsController.setSaveSearchList(result);
                    GeneralController.loadTemplatePage("/View/search_results.fxml");
                }

                if (title.isEmpty() && !author.isEmpty()){
                    List<Book> result = u.CombSearchAuthorYear(author, intYear);
                    SearchResultsController.setSaveSearchList(result);
                    GeneralController.loadTemplatePage("/View/search_results.fxml");
                }

                if (!title.isEmpty() && !author.isEmpty()){
                    List<Book> result = u.CombSearchTitleAuthorYear(title, author, intYear);
                    SearchResultsController.setSaveSearchList(result);
                    GeneralController.loadTemplatePage("/View/search_results.fxml");
                }

            }
            catch (NumberFormatException e) {
                Alert a = new Alert(AlertType.ERROR);
                a.setContentText("Invalid Integer in Year field. Please enter a valid integer in the year field.");
                a.showAndWait();
            }
            catch (DateException e) {
                Alert a = new Alert(AlertType.ERROR);
                a.setContentText(e.getMessage());
                a.showAndWait();
            }
        }
        else {
            if (!title.isEmpty() && author.isEmpty()){
                List<Book> result = u.searchBookByTitle(title);
                SearchResultsController.setSaveSearchList(result);
                GeneralController.loadTemplatePage("/View/search_results.fxml");
            }

            if (title.isEmpty() && !author.isEmpty()){
                List<Book> result = u.searchBookByAuthor(author);
                SearchResultsController.setSaveSearchList(result);
                GeneralController.loadTemplatePage("/View/search_results.fxml");
            }

            if (!title.isEmpty() && !author.isEmpty()){
                List<Book> result = u.CombSearchTitleAuthor(title, author);
                SearchResultsController.setSaveSearchList(result);
                GeneralController.loadTemplatePage("/View/search_results.fxml");
            }
        }
    
    }

    
}