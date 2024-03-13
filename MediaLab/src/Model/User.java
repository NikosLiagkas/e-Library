package Model;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class User implements Serializable{
    private String username;
    private String password;
    private String FirstName;
    private String LastName;
    private String IDnumber;
    private String email;
    private List<BorrowedBooks> borrowedBooks;

    public User(String username, String password, String FirstName, String LastName, String IDnumber, String email){
        this.username = username;
        this.password = password;
        this.FirstName = FirstName;
        this.LastName = LastName;
        this.IDnumber = IDnumber;
        this.email = email;
        this.borrowedBooks = new ArrayList<>();
    }


    public void borrowBook(Book book){
        //try{
            int count = 0;
            for (BorrowedBooks b : this.borrowedBooks){
                if (b.getActiveBorrow()==true) {count++;}
            }
            if (count < 2){
                if (book.getCopies() > 0){
                    BorrowedBooks b = new BorrowedBooks(this, book);
                    this.borrowedBooks.add(b);
                    LibraryManager.getAllBorrowedBooks().add(b);
                    //return "You have succesfully borrowed" + book.getTitle(); Must goooooooooooooooooooooooooo
                }
                else {/*throw new NoCopiesException(); */}
            }
            else 
        {/*throw new BorrowLimitException();*/}
       /* }
        catch(NoCopiesException c){
            c.printStackTrace();
        }
        catch(BorrowLimitException b){
            b.printStackTrace();
        } */
    }


    public void rateBook(Book book, int rating){
        for (BorrowedBooks b : this.borrowedBooks){
            if (b.getBorrowedBook().getISBN().equals(book.getISBN())){
                book.createRating(this, rating);
            }
        }
        
    }

    public List<Book> searchBookByTitle(String search){
        List<Book> matches = new ArrayList<>();
        for (Book b : LibraryManager.allBooks){
            if (b.getTitle().contains(search)){
                matches.add(b);
            }           
        }
        return (matches);
    }

    public List<Book> searchBookByAuthor(String search){
        List<Book> matches = new ArrayList<>();
        for (Book b : LibraryManager.allBooks){
            if (b.getAuthor().contains(search)){
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

    public void reviewBook(Book book, String review){
        for (BorrowedBooks b : this.borrowedBooks){
            if (b.getBorrowedBook().getISBN() == book.getISBN()){
                book.createReview(this, review);
            }
        }
        
    }
    
    public void setUsername(String username) {
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

    public void setIDnumber(String iDnumber) {
        IDnumber = iDnumber;
    }

    public String getIDnumber() {
        return IDnumber;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setBorrowedBooks(List<BorrowedBooks> borrowedBooks) {
        this.borrowedBooks = borrowedBooks;
    }

    public List<BorrowedBooks> getBorrowedBooks() {
        return borrowedBooks;
    }

}
