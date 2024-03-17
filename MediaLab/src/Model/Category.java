package Model;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Category implements Serializable{
    private String category;
    private List<String> catBooksISBN;

    public Category(String category){
        this.category = category;
        this.catBooksISBN = new ArrayList<>();
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCategory() {
        return category;
    }

    public void setCatBooksISBN(List<String> catBooks) {
        this.catBooksISBN = catBooks;
    }

    public List<String> getCatBooksISBN() {
        return catBooksISBN;
    }
    
}
