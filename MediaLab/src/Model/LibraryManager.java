package Model;

import java.util.List;

public class LibraryManager {
    public static List<Admin> allAdmins;
    public static List<Book> allBooks;
    public static List<BorrowedBooks> allBorrowedBooks;
    public static List<User> allUsers;
    public static List<Category> allCategories;
    public static User loggedUser;


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
