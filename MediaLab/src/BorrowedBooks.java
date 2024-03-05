import java.io.Serializable;
import java.time.LocalDate;

public class BorrowedBooks implements Serializable{
    
    private User user;
    private Book borrowedBook;
    private LocalDate borrowDate;
    private LocalDate returnDate;
    private boolean activeBorrow;


    public BorrowedBooks(User user, Book book){
        this.user = user;
        this.borrowedBook = book;
        this.borrowDate = java.time.LocalDate.now();
        this.returnDate = borrowDate.plusDays(5);
        this.activeBorrow = true;
    }


    public void setBorrowedBook(Book borrowedBook) {
        this.borrowedBook = borrowedBook;
    }

    public Book getBorrowedBook() {
        return borrowedBook;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
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
