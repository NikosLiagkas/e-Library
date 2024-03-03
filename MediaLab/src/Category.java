import java.util.ArrayList;
import java.util.List;

public class Category {
    private String category;
    private List<Book> catBooks;

    public Category(String category){
        this.category = category;
        this.catBooks = new ArrayList<>();
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCategory() {
        return category;
    }

    public void setCatBooks(List<Book> catBooks) {
        this.catBooks = catBooks;
    }

    public List<Book> getCatBooks() {
        return catBooks;
    }
    
}
