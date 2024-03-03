import java.util.ArrayList;
import java.util.List;

public class Admin extends User{
       
    public Admin(String username, String password, String FirstName, String LastName, String IDnumber, String email){
        super(username, password, FirstName, LastName, IDnumber, email);
    }

    //create a book
    public void createBook(String title, String author, String publisher, String ISBN, int release_year, 
    String language, int copies, Category category){
        Book b = new Book(title, author, publisher, ISBN, release_year, language, copies);
        App.getAllBooks().add(b);

        List<Category> c = App.getAllCategories();
        for (Category categories : c){
            if (categories.equals(category)) {
                categories.getCatBooks().add(b);
            }
        }

    }

    //delete a book

    //modification of a book

    //creation, deletion and modification of a category
    public void createCategory(String category){
        Category c = new Category(category);
        App.getAllCategories().add(c);
    }
    //modification of user info

    //deletion of a user

    //see all active borrows
    public List<BorrowedBooks> seeAllActiveBorrows;{
        
    }

    //terminate borrows

}    