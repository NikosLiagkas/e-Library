package Model;
import java.util.ArrayList;
import java.util.List;

import CustomExceptions.NotFoundException;
import CustomExceptions.NotUniqueException;
import Model.Book.Ratings;
import Model.Book.Reviews;


public class Admin extends User {
       
    public Admin(String username, String password, String FirstName, String LastName, String IDnumber, String email){
        super(username, password, FirstName, LastName, IDnumber, email);
        super.setIsAdmin(true);
    }

    //create a book
    public void createBook(String title, String author, String publisher, String ISBN, int release_year, 
    String language, int copies, Category category){
        Book b = new Book(title, author, publisher, ISBN, release_year, copies);
        LibraryManager.getAllBooks().add(b);

        List<Category> c = LibraryManager.getAllCategories();
        for (Category categories : c){
            if (categories.equals(category)) {
                categories.getCatBooksISBN().add(b.getISBN());
            }
        }

    }

    //delete a book
    public void deleteBook(Book delBook){
        for (Book b : LibraryManager.allBooks){
            if (delBook.equals(b)) {
                LibraryManager.allBooks.remove(b);
            }
        }

        for (BorrowedBooks bb : LibraryManager.allBorrowedBooks){
            if (delBook.getISBN().equals(bb.getBookISBN())) {
                LibraryManager.allBorrowedBooks.remove(bb);
            }
        }
        
        //might be unnecessary
        for (User user : LibraryManager.allUsers){
            for (BorrowedBooks userbb : user.getBorrowedBooks()){
                if (delBook.getISBN().equals(userbb.getBookISBN())) {
                    List<BorrowedBooks> bb = user.getBorrowedBooks();
                    bb.remove(userbb);
                    user.setBorrowedBooks(bb);
                }
            }
        }
    }

    //modification of a book
    public void changeBookName(Book book, String newBookName){
        book.setTitle(newBookName);
    }

    public void changeBookAuthor(Book book, String newAuthor){
        book.setAuthor(newAuthor);
    }

    public void changeBookPublisher(Book book, String newPublisher){
        book.setPublisher(newPublisher);
    }

    public void changeBookISBN(Book book, String newISBN) throws NotUniqueException{
        book.setISBN(newISBN);

        for (BorrowedBooks bb : LibraryManager.allBorrowedBooks){
            if (bb.getBookISBN().equals(book.getISBN())){
                bb.setBookISBN(newISBN);
            }
        }

        for (Category c : LibraryManager.allCategories) {
            if (c.getCatBooksISBN().contains(book.getISBN())){
                c.getCatBooksISBN().remove(book.getISBN());
                c.getCatBooksISBN().add(newISBN);
            }
        }

        for (User user : LibraryManager.allUsers){
            for (BorrowedBooks bb : user.getBorrowedBooks()) {
                if (bb.getBookISBN().equals(book.getISBN())){
                    bb.setBookISBN(newISBN);
                }
            }
        }
    }

    public void changeBookReleaseYear(Book book, int newReleaseYear){
        book.setRelease_year(newReleaseYear);
    }

    public void changeBookCopies(Book book, int newCopies){
        book.setCopies(newCopies);
    }

    //creation, deletion and modification of a category
    public void createCategory(String category){
        Category c = new Category(category);
        LibraryManager.getAllCategories().add(c);
    }

    public void deleteCategory(Category delCategory){
        for (String s : delCategory.getCatBooksISBN()){
            try{
                Book b = LibraryManager.findBook(s);
                deleteBook(b);
            }
            catch (NotFoundException n) {}
        }

        for (Category c : LibraryManager.allCategories){
            if (c.equals(delCategory)){
                LibraryManager.allCategories.remove(c);
            }
        }
    }

    public void changeCategoryName(Category category, String newCategoryName){
        category.setCategory(newCategoryName);
    }



    //modification of user info, also modify in all users, ratings, reviews, borr books, user borr books
    public void changeUserName(User user, String newUsername) throws NotUniqueException{
        user.setUsername(newUsername);
        
        for (BorrowedBooks bb : user.getBorrowedBooks()) {
            bb.setUsername(newUsername);
        }

        for (Book b : LibraryManager.allBooks){
            for (Ratings r : b.getRatings()){
                if (r.getUsername().equals(user.getUsername())){
                    r.setUsername(newUsername);
                }
            }
        }

        for (Book b : LibraryManager.allBooks){
            for (Reviews r : b.getReviews()){
                if (r.getUsername().equals(user.getUsername())){
                    r.setUsername(newUsername);
                }
            }
        }

        for (BorrowedBooks b : LibraryManager.allBorrowedBooks) {
            if (b.getUsername().equals(user.getUsername())) {
                b.setUsername(newUsername);
            }
        }

        
        
    }

    public void changeUserPassword(User user, String newPassword){
        user.setPassword(newPassword);
    }
    public void changeUserFirstName(User user, String newFirstName){
        user.setFirstName(newFirstName);
    }

    public void changeUserLastName(User user, String newLastName){
        user.setLastName(newLastName);
    }

    public void changeUserIDnumber(User user, String newIDnumber) throws NotUniqueException{
        user.setIDnumber(newIDnumber);    
    }
    
    public void changeUserEmail(User user, String newEmail) throws NotUniqueException{
        user.setEmail(newEmail);  
    }

    //deletion of a user
    public void deleteUser(User delUser) {
        for (BorrowedBooks bb : LibraryManager.allBorrowedBooks){
            if (bb.getUsername().equals(delUser.getUsername())){
                try {
                    // terminate borrow so the copies will be fixed
                    TerminateBorrow(delUser.getUsername(), LibraryManager.findBook(bb.getBookISBN()));
                    LibraryManager.allBorrowedBooks.remove(bb);
                }
                catch(NotFoundException n) {
                    // book not found - does not exist in the Library - don't need to change copies
                    LibraryManager.allBorrowedBooks.remove(bb);
                }
                
            }
        }

        for (User user : LibraryManager.allUsers){
            if (user.equals(delUser)){
                LibraryManager.allUsers.remove(user);
            }
        }
    }

    //see all active borrows
    public List<BorrowedBooks> seeAllActiveBorrows(){
        List<BorrowedBooks> ActiveBorrowedBooks;
        ActiveBorrowedBooks = new ArrayList<>();
        for (BorrowedBooks bb : LibraryManager.getAllBorrowedBooks()){
            if  (bb.getActiveBorrow()==true){
                ActiveBorrowedBooks.add(bb);
            }
        }
        return ActiveBorrowedBooks;
    }
      
    //terminate borrows
    public void TerminateBorrow(String username, Book borrowedBook){
        List<BorrowedBooks> activeBorrows = seeAllActiveBorrows();
        for (BorrowedBooks bb : activeBorrows){
            if (bb.getUsername().equals(username) && bb.getBookISBN().equals(borrowedBook.getISBN())){
                bb.setActiveBorrow(false);
                int c = borrowedBook.getCopies() + 1;
                borrowedBook.setCopies(c);
            }
        }

    }

}    