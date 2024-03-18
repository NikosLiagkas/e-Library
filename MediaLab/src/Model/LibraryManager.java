package Model;

import java.util.ArrayList;
import java.util.List;

import CustomExceptions.NotFoundException;


public class LibraryManager {
    public static List<Admin> allAdmins;
    public static List<Book> allBooks;
    public static List<BorrowedBooks> allBorrowedBooks;
    public static List<User> allUsers;
    public static List<Category> allCategories;
    public static User loggedUser;
    //public static User loggedUser = new User("Nikolios", "123456", "Nikos", "Liagas", "AM123456", "nikos@gmail.com");

    public static void retrieveData(){
        allAdmins = Data.deserialize("src/medialab/admin.ser");
        allBooks = Data.deserialize("src/medialab/book.ser");
        allBorrowedBooks = Data.deserialize("src/medialab/borrowedbooks.ser");
        allCategories = Data.deserialize("src/medialab/category.ser");
        allUsers = Data.deserialize("src/medialab/user.ser");
    }

    public static void saveData(){
        Data.serialize("src/medialab/admin.ser", allAdmins);
        Data.serialize("src/medialab/book.ser", allBooks);
        Data.serialize("src/medialab/borrowedbooks.ser", allBorrowedBooks);
        Data.serialize("src/medialab/category.ser", allCategories);
        Data.serialize("src/medialab/user.ser", allUsers);
    }

    public static User login(String username, String password) throws NotFoundException {
        for (User u : allUsers) {
            if (u.getUsername().equals(username) && u.getPassword().equals(password)) {
                return u;
            }
        }

        for (Admin a : allAdmins) {
            if (a.getUsername().equals(username) && a.getPassword().equals(password)) {
                return a;
            }
        }

        throw new NotFoundException("False login information. Please try again.");
        
    }

    public static Book findBook(String isbn) throws NotFoundException {
        for (Book book : allBooks) {
            if (book.getISBN().equals(isbn)) {
                return book;
            }
        }
        throw new NotFoundException("Book Not Found.");
    }

    public static User findUser(String username) throws NotFoundException {
        for (User user : allUsers) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        throw new NotFoundException("User Not Found.");
    }

    public static Category findCategory(String catName) throws NotFoundException {
        for (Category cat : allCategories) {
            if (cat.getCategory().equals(catName)) {
                return cat;
            }
        }
        throw new NotFoundException("Category Not Found.");
    }

    public static Category findBookCategory(Book book) throws NotFoundException{
            for (Category c : allCategories) {
                if (c.getCatBooksISBN().contains(book.getISBN())) {
                    return c;
                }
            } 
            throw new NotFoundException("Category Not Found");
    }


    public static List<Book> findCatBooks(List<String> catBooksISBN) {
        List<Book> catBooks = new ArrayList<>();
        for (Book b : allBooks) {
            for (String isbn : catBooksISBN) {
                if (isbn.equals(b.getISBN())) {
                    catBooks.add(b);
                }
            }
        }
        return catBooks;
    }

    //to get admin instance from logged user
    public static Admin getAdmin(User loggedUser) throws NotFoundException {
        if (loggedUser.getIsAdmin() == true) {
            for (Admin admin : allAdmins) {
                if ( loggedUser.getUsername().equals(admin.getUsername()) && loggedUser.getIDnumber().equals(admin.getIDnumber()) ) {
                    return admin;
                }
            }
        }
        throw new NotFoundException("Admin Not Found.");
    }


    public static List<Book> top5books(){
        List<Book> top5books = allBooks;
        top5books.sort((b1, b2) -> Double.compare(b1.getAvgRating(), b2.getAvgRating()) );
        top5books.reversed();

        if (top5books.size() < 5){
            return top5books;
        }
        else {
            return top5books.subList(0, 5);
        }
    }

    public static List<BorrowedBooks> getUsersBorrowedBooks(String username) {
        List<BorrowedBooks> result = new ArrayList<>();
        
        for (BorrowedBooks bb : allBorrowedBooks) {
            if (bb.getUsername().equals(username)) {
                result.add(bb);
            }
        }

        return result;
    }

    public static void setAllAdmins(List<Admin> allAdmins) {
        LibraryManager.allAdmins = allAdmins;
    }

    public static List<Admin> getAllAdmins() {
        return allAdmins;
    }

    public static void setAllBooks(List<Book> allBooks) {
        LibraryManager.allBooks = allBooks;
    }

    public static List<Book> getAllBooks() {
        return allBooks;
    }

    public static void setAllBorrowedBooks(List<BorrowedBooks> allBorrowedBooks) {
        LibraryManager.allBorrowedBooks = allBorrowedBooks;
    }

    public static List<BorrowedBooks> getAllBorrowedBooks() {
        return allBorrowedBooks;
    }

    public static void setAllCategories(List<Category> allCategories) {
        LibraryManager.allCategories = allCategories;
    }

    public static List<Category> getAllCategories() {
        return allCategories;
    }

    public static void setAllUsers(List<User> allUsers) {
        LibraryManager.allUsers = allUsers;
    }

    public static List<User> getAllUsers() {
        return allUsers;
    }

    public static void setLoggedUser(User loggedUser) {
        LibraryManager.loggedUser = loggedUser;
    }

    public static User getLoggedUser() {
        return loggedUser;
    }

}
