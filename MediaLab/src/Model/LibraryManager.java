package Model;

import java.util.List;

import CustomExceptions.NotFoundException;

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
