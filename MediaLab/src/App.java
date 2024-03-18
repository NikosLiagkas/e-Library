
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
import CustomExceptions.BorrowLimitException;
import CustomExceptions.NoCopiesException;
import Model.Admin;
import Model.Book;
import Model.BorrowedBooks;
import Model.Category;
import Model.LibraryManager;
import Model.User;
import Model.Book.Reviews;

public class App extends Application{

    

    @Override
    public void init() {
        LibraryManager.retrieveData();

    }

    @Override
    public void start(Stage primaryStage) {
        //System.out.println(LibraryManager.allBorrowedBooks);
        for (Admin a : LibraryManager.allAdmins) {
            System.out.println(a.getUsername());
        }
        GeneralController.setStage(primaryStage);
        //GeneralController.loadPage("/View/admin_template.fxml");
        GeneralController.loadPage("/View/home.fxml");
        
        //data();
        
        
        

    }

    @Override
    public void stop() {
        LibraryManager.saveData();
    }

    public static void main(String[] args) {
        launch(args);
    }


    public void data() {
        LibraryManager.allAdmins = new ArrayList<>();
        LibraryManager.allBooks = new ArrayList<>();
        LibraryManager.allBorrowedBooks = new ArrayList<>();
        LibraryManager.allCategories = new ArrayList<>();
        LibraryManager.allUsers = new ArrayList<>();

        Book b1 = new Book("book1", "Nik", "Liagito", "1234", 1936, 5);
        Book b2 = new Book("book2", "John", "Portys", "8945", 2000,  2);
        Book b3 = new Book("book3nc", "John", "Nocopy", "0000", 2010,  0);
        Book b4 = new Book("book4", "Nik", "Liagito", "1111", 2005, 5);
        User user = new User("Nikolios", "123456", "Nikos", "Liagas", "AM123456", "nikos@gmail.com");
        User user2 = new User("animark", "123456", "Mark", "Mpourdakos", "AMEA1234", "markos@gmail.com");
        Admin admin = new Admin("a", "a", "John", "Orfan", "AM987654", "admin@gmail.com");
        Category category1 = new Category("Cat1");
        Category category2 = new Category("Cat2");
        category1.addBookToCategory("1234");
        category2.addBookToCategory("8945");
        category1.addBookToCategory("0000");
        category1.addBookToCategory("1111");
        try {

            user.borrowBook(b1);
            user.reviewBook(b1, "STYLESHEET_CASPIAN");
        }
        catch(Exception e) {}
        BorrowedBooks bb = new BorrowedBooks("Nikolios", "1234");

        LibraryManager.allBooks.add(b1);
        LibraryManager.allBooks.add(b2);
        LibraryManager.allBooks.add(b3);
        LibraryManager.allBooks.add(b4);
        LibraryManager.allUsers.add(user);
        LibraryManager.allUsers.add(user2);
        LibraryManager.allAdmins.add(admin);
        LibraryManager.allCategories.add(category1);
        LibraryManager.allCategories.add(category2);
        LibraryManager.allBorrowedBooks.add(bb);

        System.out.println(b1.getReviews());

        Platform.exit();
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