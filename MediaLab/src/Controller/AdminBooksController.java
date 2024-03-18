

package Controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import Model.Book;
import Model.Category;
import Model.LibraryManager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class AdminBooksController implements Initializable{

    @FXML
    private Button createButton;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private VBox vbox;

    @FXML
    void openCreateBook(MouseEvent event) {
        GeneralController.loadTemplatePage("/View/admin_create_book.fxml");
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        
        vbox.prefWidthProperty().bind(scrollPane.widthProperty());
        vbox.prefHeightProperty().bind(scrollPane.heightProperty());

        for (Category c : LibraryManager.allCategories) {
            createCatBookList(c);
        }

    }

    private void createCatBookList(Category category) {   
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/admin_books_by_category.fxml"));
            AnchorPane anchorPane = loader.load();
            AdminBooksByCategoryController controller = loader.getController();
            List<Book> catBooks = LibraryManager.findCatBooks(category.getCatBooksISBN());
            controller.setBookListData(catBooks, category.getCategory());

            vbox.getChildren().add(anchorPane); // Add AnchorPane to the VBox
        }
        catch(Exception e) {
            e.printStackTrace();
        }

    }
}
