package Model;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import CustomExceptions.NotUniqueException;

public class Category implements Serializable{
    private String category;
    private List<String> catBooksISBN;

    public Category(String category){
        this.category = category;
        this.catBooksISBN = new ArrayList<>();
    }

    public void setCategory(String category) throws NotUniqueException{
        for (Category c : LibraryManager.allCategories){
            if(c.getCategory().equals(category)) {
                throw new NotUniqueException("Category name already used. Please enter a different category name.");
            }
        }
        this.category = category;
    }

    public void addBookToCategory(String ISBN) {
        this.catBooksISBN.add(ISBN);
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
