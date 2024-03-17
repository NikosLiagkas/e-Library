
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import Controller.GeneralController;
import Model.Admin;
import Model.Book;
import Model.BorrowedBooks;
import Model.Category;
import Model.LibraryManager;
import Model.User;

public class App extends Application{

    

    @Override
    public void init() {
        LibraryManager.retrieveData();
    }

    @Override
    public void start(Stage primaryStage) {
        
        GeneralController.setStage(primaryStage);
        GeneralController.loadPage("/View/user_template.fxml");

        /*
        LibraryManager.allAdmins = new ArrayList<>();
        LibraryManager.allBooks = new ArrayList<>();
        LibraryManager.allBorrowedBooks = new ArrayList<>();
        LibraryManager.allCategories = new ArrayList<>();
        LibraryManager.allUsers = new ArrayList<>();

        Book b1 = new Book("book1", "Nik", "Liagito", "1234", 1936, 5);
        Book b2 = new Book("book2", "John", "Portys", "8945", 2000,  2);
        User user = new User("Nikolios", "123456", "Nikos", "Liagas", "AM123456", "nikos@gmail.com");
        Admin admin = new Admin("TheAdmin", "admin1234", "John", "Orfan", "AM987654", "admin@gmail.com");
        Category category1 = new Category("Cat1");
        Category category2 = new Category("Cat2");
        BorrowedBooks bb = new BorrowedBooks("Nikolios", "1234");

        LibraryManager.allBooks.add(b1);
        LibraryManager.allBooks.add(b2);
        LibraryManager.allUsers.add(user);
        LibraryManager.allAdmins.add(admin);
        LibraryManager.allCategories.add(category1);
        LibraryManager.allCategories.add(category2);
        LibraryManager.allBorrowedBooks.add(bb);

        Platform.exit();
         */

    }

    @Override
    public void stop() {
        LibraryManager.saveData();
    }

    public static void main(String[] args) {
        launch(args);
    }

}



/* 
public class App extends Application {
    @Override
    public void start(Stage primaryStage) {
        Button btn = new Button();
        btn.setText("say 'Hello World'");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event){
                System.out.println("Hello World");
            }
        });

        StackPane root = new StackPane();
        root.getChildren().add(btn);
   

Scene scene = new Scene(root, 300, 250);

        primaryStage.setTitle("Hello World");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
public static void main(String[] args) {
    launch(args);
}
}
*/