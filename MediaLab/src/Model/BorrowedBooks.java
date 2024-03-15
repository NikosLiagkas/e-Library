package Model;
import java.io.Serializable;
import java.time.LocalDate;

public class BorrowedBooks implements Serializable{
    
    private String username;
    private String bookISBN;
    private LocalDate borrowDate;
    private LocalDate returnDate;
    private boolean activeBorrow;


    public BorrowedBooks(String username, String ISBN){
        this.username = username;
        this.bookISBN = ISBN;
        this.borrowDate = java.time.LocalDate.now();
        this.returnDate = borrowDate.plusDays(5);
        this.activeBorrow = true;
    }


    public void setBookISBN(String bookISBN) {
        this.bookISBN = bookISBN;
    }

    public String getBookISBN() {
        return bookISBN;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setBorrowDate(LocalDate borrowDate) {
        this.borrowDate = borrowDate;
    }

    public LocalDate getBorrowDate() {
        return borrowDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setActiveBorrow(boolean activeBorrow) {
        this.activeBorrow = activeBorrow;
    }

    public boolean getActiveBorrow() {
        return activeBorrow;
    }

    
}
