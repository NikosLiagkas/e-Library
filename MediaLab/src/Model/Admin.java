package Model;
import java.util.ArrayList;
import java.util.List;

import CustomExceptions.DateException;
import CustomExceptions.NegativeCopiesException;
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

        // it is checked by controller that this category exixts when createBook is called
        List<Category> c = LibraryManager.getAllCategories();
        for (Category categories : c){
            if (categories.equals(category)) {
                categories.getCatBooksISBN().add(b.getISBN());
            }
        }

    }

    //delete a book and all its borrows, active or inactive
    public void deleteBook(Book delBook){
        List<BorrowedBooks> del = new ArrayList<>();
        for (BorrowedBooks bb : LibraryManager.allBorrowedBooks){
            if (delBook.getISBN().equals(bb.getBookISBN())) {
                del.add(bb);
            }
        }

        for (BorrowedBooks bb : del) {
            LibraryManager.allBorrowedBooks.remove(bb);
        }

        LibraryManager.allBooks.remove(delBook); 
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

    // change ISBN in book - in Borrows active or inactive - in category
    public void changeBookISBN(Book book, String newISBN) throws NotUniqueException{
        String previousISBN = book.getISBN();
        
        // if incorrect ISBN this throws exception
        book.setISBN(newISBN);

        for (BorrowedBooks bb : LibraryManager.allBorrowedBooks){
            if (bb.getBookISBN().equals(previousISBN)){
                bb.setBookISBN(newISBN);
            }
        }

        for (Category c : LibraryManager.allCategories) {
            if (c.getCatBooksISBN().contains(previousISBN)){
                c.getCatBooksISBN().remove(previousISBN);
                c.getCatBooksISBN().add(newISBN);
            }
        }
    }

    public void changeBookReleaseYear(Book book, int newReleaseYear) throws DateException {
        book.setRelease_year(newReleaseYear);
    }

    public void changeBookCopies(Book book, int newCopies) throws NegativeCopiesException{
        book.setCopies(newCopies);
    }

    public void changeBookCategory(Book book, String newCat) throws NotFoundException {
        
        Category newCategory = LibraryManager.findCategory(newCat);
        Category oldCategory = LibraryManager.findBookCategory(book);
            
        oldCategory.getCatBooksISBN().remove(book.getISBN());
        newCategory.addBookToCategory(book.getISBN());
        
    }

    //creation, deletion and modification of a category
    public void createCategory(String category) throws NotUniqueException{
        for (Category c : LibraryManager.allCategories){
            if(c.getCategory().equals(category)) {
                throw new NotUniqueException("Category name already used. Please enter a different category name.");
            }
        }
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

        LibraryManager.allCategories.remove(delCategory);     
    }

    public void changeCategoryName(Category category, String newCategoryName) throws NotUniqueException{
        category.setCategory(newCategoryName);
    }

    //modification of user info, also modify in all users, ratings, reviews, borr books
    public void changeUserName(User user, String newUsername) throws NotUniqueException{
        // if incorrect username this throws exception
        String previousUsername = user.getUsername();
        user.setUsername(newUsername);

        for (Book b : LibraryManager.allBooks){
            for (Ratings r : b.getRatings()){
                if (r.getUsername().equals(previousUsername)){
                    r.setUsername(newUsername);
                }
            }
        }

        for (Book b : LibraryManager.allBooks){
            for (Reviews r : b.getReviews()){
                if (r.getUsername().equals(previousUsername)){
                    r.setUsername(newUsername);
                }
            }
        }

        for (BorrowedBooks b : LibraryManager.allBorrowedBooks) {
            if (b.getUsername().equals(previousUsername)) {
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

    //deletion of a user - his borrows active or inactive
    public void deleteUser(User delUser) {
        List<BorrowedBooks> del = new ArrayList<>();
        for (BorrowedBooks bb : LibraryManager.allBorrowedBooks){
            if (bb.getUsername().equals(delUser.getUsername())){
                // terminate borrow so the copies will be fixed
                del.add(bb);
            }
        }

        for (BorrowedBooks bb : del) {
            TerminateBorrow(bb);
            LibraryManager.allBorrowedBooks.remove(bb);
        }

        List<User> del2 = new ArrayList<>();
        for (User user : LibraryManager.allUsers){
            if (user.equals(delUser)){
                del2.add(user);
            }
        }

        for (User user : del2) {
            LibraryManager.allUsers.remove(user);
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
    public void TerminateBorrow(BorrowedBooks borrowedBook) {
        List<BorrowedBooks> activeBorrows = seeAllActiveBorrows();
        for (BorrowedBooks bb : activeBorrows){
            if (bb.equals(borrowedBook)){
                try {
                    bb.setActiveBorrow(false);
                    Book b = LibraryManager.findBook(bb.getBookISBN());
                    int c = b.getCopies() + 1;
                    
                    b.setCopies(c);
                }
                catch (NegativeCopiesException e) {
                    e.printStackTrace();
                    //it will never be thrown
                }
                catch (NotFoundException e) {
                    //The book has been deleted so we romove it from all borrbooks
                    LibraryManager.allBorrowedBooks.remove(borrowedBook);
                }
                break;
            }
        }

    }

}    