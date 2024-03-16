package Controller;

import java.io.IOException;

import Model.Book;
import Model.BorrowedBooks;
import Model.Category;
import Model.LibraryManager;
import Model.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

public class AdminCellBorrows extends ListCell<BorrowedBooks>{
    
    private FXMLLoader mLLoader;

    @FXML
    private HBox Hbox;

    @FXML
    private Label returnDate;

    @FXML
    private Label borrowDate;

    @FXML
    private Label isbn;

    @FXML
    private Label rating;

    @FXML
    private Button terminateButton;

    @FXML
    private Label title;

    @FXML
    private Label username;

    @FXML
    void terminateBorrow(MouseEvent event) {

    }

    @Override
    protected void updateItem(BorrowedBooks bb, boolean empty) {
        super.updateItem(bb, empty);

        if(empty || bb == null) {

            setText(null);
            setGraphic(null);

        } else {
            if (mLLoader == null) {
                mLLoader = new FXMLLoader(getClass().getResource("/View/admin_cell_borrows.fxml"));
                mLLoader.setController(this);

                try {
                    mLLoader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
            Hbox.prefWidthProperty().bind(getListView().widthProperty());
            Book b = LibraryManager.findBook(bb.getBookISBN());
            User u = LibraryManager.findUser(bb.getUsername());
            title.setText(b.getTitle());         
            username.setText(u.getUsername());
            borrowDate.setText(String.valueOf(bb.getBorrowDate()));
            returnDate.setText(String.valueOf(bb.getReturnDate()));
            setText(null);
            setGraphic(Hbox);

        }

    }

}