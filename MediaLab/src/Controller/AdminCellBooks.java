package Controller;

import java.io.IOException;

import CustomExceptions.NotFoundException;
import Model.Admin;
import Model.Book;
import Model.LibraryManager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

public class AdminCellBooks extends ListCell<Book> {

    private FXMLLoader mLLoader;

    @FXML
    private HBox Hbox;

    @FXML
    private Label author;

    @FXML
    private Button deleteButton;

    @FXML
    private Label isbn;

    @FXML
    private Button modifyBookButton;

    @FXML
    private Label rating;

    @FXML
    private Label title;

    @FXML
    void deleteBook(MouseEvent event) {
        try{
            Admin admin = LibraryManager.getAdmin(LibraryManager.loggedUser);
            admin.deleteBook(getItem());
            Alert a = new Alert(AlertType.INFORMATION);
            a.setContentText("Book succesfully deleted.");
            a.setOnCloseRequest(s -> {
                GeneralController.loadTemplatePage("/View/admin_all_books.fxml");
            });
            a.showAndWait();
        }
        catch (NotFoundException e) {
            //will never happen
        }
    }

    @FXML
    void openModifyBook(MouseEvent event) {
        AdminModifyBookController.setSaveBook(getItem());
        GeneralController.loadTemplatePage("/View/admin_modify_book.fxml");
    }

    @Override
    protected void updateItem(Book b, boolean empty) {
        super.updateItem(b, empty);

        if(empty || b == null) {

            setText(null);
            setGraphic(null);

        } else {
            if (mLLoader == null) {
                mLLoader = new FXMLLoader(getClass().getResource("/View/admin_cell_books.fxml"));
                mLLoader.setController(this);

                try {
                    mLLoader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
            Hbox.prefWidthProperty().bind(getListView().widthProperty());

            title.setText(b.getTitle());         
            author.setText(b.getAuthor());
            isbn.setText(b.getISBN());
            rating.setText(String.valueOf(b.getAvgRating()));
            
            setText(null);
            setGraphic(Hbox);

        }

    }

}
