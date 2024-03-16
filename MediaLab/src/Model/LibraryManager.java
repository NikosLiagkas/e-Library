package Model;

import java.util.List;
import java.io.File;

public class LibraryManager {
    public static List<Admin> allAdmins;
    public static List<Book> allBooks;
    public static List<BorrowedBooks> allBorrowedBooks;
    public static List<User> allUsers;
    public static List<Category> allCategories;
    public static User loggedUser = new Admin("TheAdmin", "admin1234", "John", "Orfan", "AM987654", "admin@gmail.com");

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

    public static Book findBook(String isbn) {
        for (Book book : allBooks) {
            if (book.getISBN().equals(isbn)) {
                return book;
            }
        }
        return null;
    }

    public static User findUser(String username) {
        for (User user : allUsers) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    public static Category findCategory(String catName) {
        for (Category cat : allCategories) {
            if (cat.getCategory().equals(catName)) {
                return cat;
            }
        }
        return null;
    }

    //to get admin instance from logged user
    public static Admin getAdmin(User loggedUser) {
        if (loggedUser.getIsAdmin() == true) {
            for (Admin admin : allAdmins) {
                if ( loggedUser.getUsername().equals(admin.getUsername()) && loggedUser.getIDnumber().equals(admin.getIDnumber()) ) {
                    return admin;
                }
            }
        }
        return null;
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
