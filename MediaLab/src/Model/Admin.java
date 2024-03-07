package Model;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class Admin extends User {
       
    public Admin(String username, String password, String FirstName, String LastName, String IDnumber, String email){
        super(username, password, FirstName, LastName, IDnumber, email);
    }

    //create a book
    public void createBook(String title, String author, String publisher, String ISBN, int release_year, 
    String language, int copies, Category category){
        Book b = new Book(title, author, publisher, ISBN, release_year, language, copies);
        LibraryManager.getAllBooks().add(b);

        List<Category> c = LibraryManager.getAllCategories();
        for (Category categories : c){
            if (categories.equals(category)) {
                categories.getCatBooks().add(b);
            }
        }

    }

    //delete a book
    public void deleteBook(Book delBook){
        for (Book b : LibraryManager.allBooks){
            if (delBook.equals(b)){LibraryManager.allBooks.remove(b);}
        }

        for (BorrowedBooks bb : LibraryManager.allBorrowedBooks){
            if (delBook.equals(bb.getBorrowedBook())){LibraryManager.allBorrowedBooks.remove(bb);}
        }
        
        //might be unnecessary
        for (User user : LibraryManager.allUsers){
            for (BorrowedBooks userbb : user.getBorrowedBooks()){
                if (delBook.equals(userbb.getBorrowedBook())){user.getBorrowedBooks().remove(userbb);}
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

    public void changeBookISBN(Book book, String newISBN){
        book.setISBN(newISBN);
    }

    public void changeBookReleaseYear(Book book, int newReleaseYear){
        book.setRelease_year(newReleaseYear);
    }

    public void changeBookLanguage(Book book, String newLanguage){
        book.setLanguage(newLanguage);
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
        for (Book b : delCategory.getCatBooks()){
            deleteBook(b);
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



    //modification of user info 
    public String changeUserName(User user, String newUsername){
        for (User u : LibraryManager.allUsers){
            if (u.getUsername().equals(newUsername)){
                return ("This username is already taken. Please choose another one.");
            }
            else {continue;}
        }
        user.setUsername(newUsername);
        return ("Username succesfully changed.");
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

    public void changeUserIDnumber(User user, String newIDnumber){
        user.setIDnumber(newIDnumber);
    }
    public void changeUserEmail(User user, String newEmail){
        user.setEmail(newEmail);
    }

    //deletion of a user
    public void deleteUser(User delUser){
        for (BorrowedBooks bb : LibraryManager.allBorrowedBooks){
            if (bb.getUser().equals(delUser)){
                LibraryManager.allBorrowedBooks.remove(bb);
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
    public void TerminateBorrow(User user, Book borrowedBook){
        List<BorrowedBooks> activeBorrows = seeAllActiveBorrows();
        for (BorrowedBooks bb : activeBorrows){
            if (bb.getUser().equals(user) && bb.getBorrowedBook().equals(borrowedBook)){
                bb.setActiveBorrow(false);
                int c = borrowedBook.getCopies() + 1;
                borrowedBook.setCopies(c);
            }
        }

    }

}    