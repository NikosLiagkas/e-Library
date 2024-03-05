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

public class App extends Application{

    public static List<Admin> allAdmins;
    public static List<Book> allBooks;
    public static List<BorrowedBooks> allBorrowedBooks;
    public static List<User> allUsers;
    public static List<Category> allCategories;
    public static User loggedUser;

    @Override
    public void init() {}

    @Override
    public void start(Stage primaryStage) {
        
        File a = new File("src/medialab/admin.ser");
        Admin x = new Admin("liagito", "123", "Nikos", "Liagas", "AM12345", "nikos@gmail.com");
        Admin y = new Admin("l", "3", "Nis", "Lis", "12345", "kos@gmail.com");
        allAdmins = new ArrayList<>();
        allAdmins.add(x);
        allAdmins.add(y);
        Data.serialize(a, allAdmins);
        List<Admin> ad = Data.deserialize(a);
        System.out.println(ad);
        Platform.exit();
    }

    @Override
    public void stop() {}

    public static void main(String[] args) {
        launch(args);
    }

    public static void setAllAdmins(List<Admin> allAdmins) {
        App.allAdmins = allAdmins;
    }

    public static List<Admin> getAllAdmins() {
        return allAdmins;
    }

    public static void setAllBooks(List<Book> allBooks) {
        App.allBooks = allBooks;
    }

    public static List<Book> getAllBooks() {
        return allBooks;
    }

    public static void setAllBorrowedBooks(List<BorrowedBooks> allBorrowedBooks) {
        App.allBorrowedBooks = allBorrowedBooks;
    }

    public static List<BorrowedBooks> getAllBorrowedBooks() {
        return allBorrowedBooks;
    }

    public static void setAllCategories(List<Category> allCategories) {
        App.allCategories = allCategories;
    }

    public static List<Category> getAllCategories() {
        return allCategories;
    }

    public static void setAllUsers(List<User> allUsers) {
        App.allUsers = allUsers;
    }

    public static List<User> getAllUsers() {
        return allUsers;
    }

    public static void setLoggedUser(User loggedUser) {
        App.loggedUser = loggedUser;
    }

    public static User getLoggedUser() {
        return loggedUser;
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