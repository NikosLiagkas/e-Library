package Controller;

import java.io.IOException;

import Model.Book;
import Model.Book.Reviews;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.HBox;

public class ReviewCell extends ListCell<Reviews> {
    private FXMLLoader mLLoader;

    @FXML
    private HBox Hbox;

    @FXML
    private Label review;

    @FXML
    private Label user;


    @Override
    protected void updateItem(Reviews rev, boolean empty) {
        super.updateItem(rev, empty);

        if(empty || rev == null) {

            setText(null);
            setGraphic(null);

        } else {
            if (mLLoader == null) {
                mLLoader = new FXMLLoader(getClass().getResource("/View/cell_review.fxml"));
                mLLoader.setController(this);

                try {
                    mLLoader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
            Hbox.prefWidthProperty().bind(getListView().widthProperty());
            //user.setText(rev.getUsername());
            //review.setText(rev.getReview());

            setText(null);
            setGraphic(Hbox);

        }

    }

}
