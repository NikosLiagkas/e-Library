
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;

import java.util.ArrayList;

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

        
        
        Book b1 = new Book("The Art of War", "Sun Tzu", "Dover Publications", "3245", 2002, 5);
        Book b2 = new Book("Sapiens: A Brief History of Humankind", "Yuval Noah Harari", "Harper", "1789", 2015,  7);
        Book b3 = new Book("21 Lessons for the 21st Century", "Yuval Noah Harari", "Spiegel & Grau", "5698", 2018,  3);
        Book b4 = new Book("Homo Deus: A Brief History of Tomorrow", "Yuval Noah Harari", "Harper", "7456", 2017, 2);
        Book b5 = new Book("Crime and Punishment", "Fyodor Dostoevsky", "Penguin Classics", "8234", 2003, 4);
        Book b6 = new Book("The Brothers Karamazov", "Fyodor Dostoevsky", "Vintage Classics", "9012", 1991, 6);
        Book b7 = new Book("The Picture of Dorian Gray", "Oscar Wilde", "Penguin Classics", "6354", 2003, 5);
        Book b8 = new Book("The Importance of Being Earnest", "Oscar Wilde", "Penguin Classics", "2876", 2000, 1);
        Book b9 = new Book("Lady Windermere's Fan", "Oscar Wilde", "Dover Publications", "5098", 1991, 0);
        Book b10 = new Book("The Odyssey", "Homer", "Penguin Classics", "1234", 1999, 5);
        
        LibraryManager.allBooks.add(b1);
        LibraryManager.allBooks.add(b2);
        LibraryManager.allBooks.add(b3);
        LibraryManager.allBooks.add(b4);
        LibraryManager.allBooks.add(b5);
        LibraryManager.allBooks.add(b6);
        LibraryManager.allBooks.add(b7);
        LibraryManager.allBooks.add(b8);
        LibraryManager.allBooks.add(b9);
        LibraryManager.allBooks.add(b10);


        Category sociology = new Category("Sociology");
        Category comedy = new Category("Comedy");
        Category fiction = new Category("Fiction");
        Category classics = new Category("Classics");

        sociology.addBookToCategory(b2.getISBN());
        sociology.addBookToCategory(b3.getISBN());
        sociology.addBookToCategory(b4.getISBN());
        comedy.addBookToCategory(b8.getISBN());
        comedy.addBookToCategory(b9.getISBN());
        fiction.addBookToCategory(b5.getISBN());
        fiction.addBookToCategory(b6.getISBN());
        fiction.addBookToCategory(b7.getISBN());
        classics.addBookToCategory(b1.getISBN());
        classics.addBookToCategory(b10.getISBN());

        LibraryManager.allCategories.add(sociology);
        LibraryManager.allCategories.add(comedy);
        LibraryManager.allCategories.add(fiction);
        LibraryManager.allCategories.add(classics);

        
        User user1 = new User("liagito", "1234", "Nikos", "Liagas", "AM1111", "nikos@gmail.com");
        User user2 = new User("animark", "1234", "Markos", "Mpourdakos", "AM2222", "markos@gmail.com");
        User user3 = new User("koman", "1234", "Giannis", "Komnas", "AM3333", "giannis@gmail.com");
        User user4 = new User("alexnoshma", "1234", "Alex", "Michos", "AM4444", "alex@gmail.com");
        
        LibraryManager.allUsers.add(user1);
        LibraryManager.allUsers.add(user2);
        LibraryManager.allUsers.add(user3);
        LibraryManager.allUsers.add(user4);
        


        Admin defaultAdmin = new Admin("medialab", "medialab_2024", "Default", "Admin", "AM1234", "default_admin@gmail.com");
        Admin admin = new Admin("aaa", "111", "Ethan", "Parker", "AM6789", "admin2@gmail.com");
        
        LibraryManager.allAdmins.add(defaultAdmin);
        LibraryManager.allAdmins.add(admin);
        
        
        try {

            //ratings and reviews for b1
            user1.borrowBook(b1);
            user1.rateBook(b1, 5);
            user1.reviewBook(b1, "Timeless wisdom on strategy and leadership.");

            for (BorrowedBooks bb : LibraryManager.allBorrowedBooks) {
                admin.TerminateBorrow(bb);
            }

            user3.borrowBook(b1);
            user3.rateBook(b1, 4);
            user3.reviewBook(b1, "A true classic.");

            for (BorrowedBooks bb : LibraryManager.getAllBorrowedBooks()) {
                admin.TerminateBorrow(bb);
            }

            user4.borrowBook(b1);
            user4.rateBook(b1, 4);

            for (BorrowedBooks bb : LibraryManager.getAllBorrowedBooks()) {
                admin.TerminateBorrow(bb);
            }

            //ratings and reviews for b2
            user1.borrowBook(b2);
            user1.rateBook(b2, 5);
            user1.reviewBook(b2, "Insightful journey through human history.");
            
            for (BorrowedBooks bb : LibraryManager.getAllBorrowedBooks()) {
                admin.TerminateBorrow(bb);
            }

            user2.borrowBook(b2);
            user2.rateBook(b2, 5);
            user2.reviewBook(b2, "Thought-provoking.");
            
            for (BorrowedBooks bb : LibraryManager.getAllBorrowedBooks()) {
                admin.TerminateBorrow(bb);
            }

            user3.borrowBook(b2);
            user3.rateBook(b2, 4);
            user3.reviewBook(b2, "Eye-opening examination of our collective past.");
           
            for (BorrowedBooks bb : LibraryManager.getAllBorrowedBooks()) {
                admin.TerminateBorrow(bb);
            }

            user4.borrowBook(b2);
            user4.rateBook(b2, 5);
            user4.reviewBook(b2, "Captivating.");
           
            for (BorrowedBooks bb : LibraryManager.getAllBorrowedBooks()) {
                admin.TerminateBorrow(bb);
            }


            //ratings and reviews for b3
            user2.borrowBook(b3);
            user2.rateBook(b3, 3);
            
            for (BorrowedBooks bb : LibraryManager.getAllBorrowedBooks()) {
                admin.TerminateBorrow(bb);
            }

            user3.borrowBook(b3);
            user3.rateBook(b3, 5);
            user3.reviewBook(b3, "Thought-provoking insights on contemporary issues.");
            
            for (BorrowedBooks bb : LibraryManager.getAllBorrowedBooks()) {
                admin.TerminateBorrow(bb);
            }

            user4.borrowBook(b3);
            user4.rateBook(b3, 2);
            user4.reviewBook(b3, "Somewhat repetitive.");
            
            for (BorrowedBooks bb : LibraryManager.getAllBorrowedBooks()) {
                admin.TerminateBorrow(bb);
            }
            

            //ratings and reviews for b4
            user1.borrowBook(b4);
            user1.rateBook(b4, 3);
            
            for (BorrowedBooks bb : LibraryManager.getAllBorrowedBooks()) {
                admin.TerminateBorrow(bb);
            }

            user2.borrowBook(b4);
            user2.rateBook(b4, 5);
            user2.reviewBook(b4, "Fascinating glimpse into the future.");
            
            for (BorrowedBooks bb : LibraryManager.getAllBorrowedBooks()) {
                admin.TerminateBorrow(bb);
            }

            //ratings and reviews for b5
            user1.borrowBook(b5);
            user1.rateBook(b5, 4);
            user1.reviewBook(b5, "Riveting exploration of morality.");
            
            for (BorrowedBooks bb : LibraryManager.getAllBorrowedBooks()) {
                admin.TerminateBorrow(bb);
            }

            user3.borrowBook(b5);
            user3.rateBook(b5, 1);
            user3.reviewBook(b5, "Overly complex and tedious.");
            
            for (BorrowedBooks bb : LibraryManager.getAllBorrowedBooks()) {
                admin.TerminateBorrow(bb);
            }

            user4.borrowBook(b5);
            user4.rateBook(b5, 2);
            
            for (BorrowedBooks bb : LibraryManager.getAllBorrowedBooks()) {
                admin.TerminateBorrow(bb);
            }


            //ratings and reviews for b6
            user2.borrowBook(b6);
            user2.rateBook(b6, 3);
            user2.reviewBook(b6, "Somewhat complex yet intriguing.");
            
            for (BorrowedBooks bb : LibraryManager.getAllBorrowedBooks()) {
                admin.TerminateBorrow(bb);
            }


            //ratings and reviews for b7
            user1.borrowBook(b7);
            user1.rateBook(b7, 3);
            user1.reviewBook(b7, "Slightly underwhelming.");
            
            for (BorrowedBooks bb : LibraryManager.getAllBorrowedBooks()) {
                admin.TerminateBorrow(bb);
            }

            user4.borrowBook(b7);
            user4.rateBook(b7, 5);
            user4.reviewBook(b7, "My favourite ever book!");
           
            for (BorrowedBooks bb : LibraryManager.getAllBorrowedBooks()) {
                admin.TerminateBorrow(bb);
            }


            //ratings and reviews for b8
            user3.borrowBook(b8);
            user3.rateBook(b8, 2);
            user3.reviewBook(b8, "Dull and utterly disappointing read");
           
            for (BorrowedBooks bb : LibraryManager.getAllBorrowedBooks()) {
                admin.TerminateBorrow(bb);
            }

            //No ratings and reviews for b9

            //ratings and reviews for b10
            user1.borrowBook(b10);
            user1.rateBook(b10, 4);
            user1.reviewBook(b10, "Gripping epic of ancient times.");
            
            for (BorrowedBooks bb : LibraryManager.getAllBorrowedBooks()) {
                admin.TerminateBorrow(bb);
            }

            user2.borrowBook(b10);
            user2.rateBook(b10, 4);
            user2.reviewBook(b10, "Timeless journey, rich storytelling.");
           
            for (BorrowedBooks bb : LibraryManager.getAllBorrowedBooks()) {
                admin.TerminateBorrow(bb);
            }

            user3.borrowBook(b10);
            user3.rateBook(b10, 4);
            user3.reviewBook(b10, "Legendary adventure, beautifully narrated.");
            
            for (BorrowedBooks bb : LibraryManager.getAllBorrowedBooks()) {
                admin.TerminateBorrow(bb);
            }

            user4.borrowBook(b10);
            user4.rateBook(b10, 5);
            user4.reviewBook(b10, "Classic masterpiece, captivating odyssey.");
            
            for (BorrowedBooks bb : LibraryManager.getAllBorrowedBooks()) {
                admin.TerminateBorrow(bb);
            }



            // active borrows
            user1.borrowBook(b2);
            user1.borrowBook(b6);

            user2.borrowBook(b2);
            
            user3.borrowBook(b7);
            user3.borrowBook(b10);

            user4.borrowBook(b5);
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        Platform.exit();
    }
}



