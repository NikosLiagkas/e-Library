package Model;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import CustomExceptions.BorrowLimitException;
import CustomExceptions.NegativeCopiesException;
import CustomExceptions.NoCopiesException;
import CustomExceptions.NotUniqueException;

public class User implements Serializable{
    private String username;
    private String password;
    private String FirstName;
    private String LastName;
    private String IDnumber;
    private String email;
    private boolean isAdmin;

    public User(String username, String password, String FirstName, String LastName, String IDnumber, String email){
        this.username = username;
        this.password = password;
        this.FirstName = FirstName;
        this.LastName = LastName;
        this.IDnumber = IDnumber;
        this.email = email;
        this.isAdmin = false;
    }


    // borrow book - fix copies - create borrow
    public void borrowBook(Book book) throws NoCopiesException, BorrowLimitException{
        
        int count = 0;
        for (BorrowedBooks b : LibraryManager.getUsersBorrowedBooks(this.username)){
            if (b.getActiveBorrow()==true) {
                count++;
            }
        }
        if (count < 2){
            if (book.getCopies() > 0){
                BorrowedBooks b = new BorrowedBooks(this.username, book.getISBN());
                LibraryManager.allBorrowedBooks.add(b);

                try {
                    book.setCopies(book.getCopies() - 1);
                } catch (NegativeCopiesException e) {
                    // this will never happen , we checked if there are available copies above
                }
            }
            else {
                throw new NoCopiesException(); 
            }
        }
        else {
            throw new BorrowLimitException();
        }
    }


    // Search Book and Combined Searches
    public List<Book> searchBookByTitle(String search){
        List<Book> matches = new ArrayList<>();
        for (Book b : LibraryManager.allBooks){
            if (b.getTitle().toLowerCase().contains(search.toLowerCase())){
                matches.add(b);
            }           
        }
        return (matches);
    }

    public List<Book> searchBookByAuthor(String search){
        List<Book> matches = new ArrayList<>();
        for (Book b : LibraryManager.allBooks){
            if (b.getAuthor().toLowerCase().contains(search.toLowerCase())){
                matches.add(b);
            }           
        }
        return (matches);
    }

    public List<Book> searchBookByYear(int search){
        List<Book> matches = new ArrayList<>();
        for (Book b : LibraryManager.allBooks){
            if (b.getRelease_year() == search){
                matches.add(b);
            }           
        }
        return (matches);
    }

    public List<Book> CombSearchTitleAuthor(String title, String author){
        List<Book> matches = searchBookByTitle(title);
        List<Book> list1 = searchBookByAuthor(author);
        matches.retainAll(list1);
        return (matches);     
    }
    
    public List<Book> CombSearchTitleYear(String title, int year){
        List<Book> matches = searchBookByTitle(title);
        List<Book> list1 = searchBookByYear(year);
        matches.retainAll(list1);
        return (matches);     
    }

    public List<Book> CombSearchAuthorYear(String author, int year){
        List<Book> matches = searchBookByAuthor(author);
        List<Book> list1 = searchBookByYear(year);
        matches.retainAll(list1);
        return (matches);     
    }

    public List<Book> CombSearchTitleAuthorYear(String title, String author, int year){
        List<Book> matches = searchBookByTitle(title);
        List<Book> list1 = searchBookByAuthor(author);
        List<Book> list2 = searchBookByYear(year);
        list1.retainAll(list2);
        matches.retainAll(list1);
        return (matches);     
    }

    public void rateBook(Book book, int rating){
        for (BorrowedBooks b : LibraryManager.getUsersBorrowedBooks(this.username)){
            if (b.getBookISBN().equals(book.getISBN())){
                book.createRating(this.username, rating);
            }
        }
        
    }

    public void reviewBook(Book book, String review){
        List<BorrowedBooks> myborrows = LibraryManager.getUsersBorrowedBooks(this.username);
        
        for (BorrowedBooks b : myborrows){
            if (b.getBookISBN().equals(book.getISBN())){
                book.createReview(this.username, review);
                break;
            }
        }
        
    }
    
    public void setUsername(String username) throws NotUniqueException{
        for (User u : LibraryManager.allUsers){
           if (u.getUsername().equals(username)){
                throw new NotUniqueException("Username already taken. Please choose another one.");
            }
        }
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setIDnumber(String iDnumber) throws NotUniqueException {
        for (User u : LibraryManager.allUsers){
            if (u.getIDnumber().equals(iDnumber)){
                throw new NotUniqueException("ID number already taken. Please enter a unique ID number.");
            }
        }
        IDnumber = iDnumber;
    }

    public String getIDnumber() {
        return IDnumber;
    }

    public void setEmail(String email) throws NotUniqueException { 
        for (User u : LibraryManager.allUsers){
            if (u.getEmail().equals(email)){
                throw new NotUniqueException("Email already taken. Please enter a unique Email.");
            }
        }    
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    public boolean getIsAdmin() {
        return isAdmin;
    }
}

