package Model;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import CustomExceptions.NotUniqueException;

/**
 * Represents a category in the system. 
 * Categories are used to classify books into different groups.
 */
public class Category implements Serializable{
    private String category;
    private List<String> catBooksISBN;

    /**
     * Constructs a new Category object with the specified category name.
     * At first a new Category has no book ISBNs in it.
     * 
     * @param category The name of the category.
     */
    public Category(String category){
        this.category = category;
        this.catBooksISBN = new ArrayList<>();
    }

    /**
     * Sets the name of the category.
     * 
     * @param category The new name for the category.
     * @throws NotUniqueException If the specified category name is already used by another category.
     */
    public void setCategory(String category) throws NotUniqueException{
        for (Category c : LibraryManager.allCategories){
            if(c.getCategory().equals(category)) {
                throw new NotUniqueException("Category name already used. Please enter a different category name.");
            }
        }
        this.category = category;
    }

    /**
     * Adds a book's ISBN to the list of books in this category.
     * 
     * @param ISBN The ISBN of the book to add to the category.
     */
    public void addBookToCategory(String ISBN) {
        this.catBooksISBN.add(ISBN);
    } 

    /**
     * Gets the name of the category.
     * 
     * @return The name of the category.
     */
    public String getCategory() {
        return category;
    }

    /**
     * Sets the list of book ISBNs associated with this category.
     * 
     * @param catBooks The list of book ISBNs.
     */
    public void setCatBooksISBN(List<String> catBooks) {
        this.catBooksISBN = catBooks;
    }

    /**
     * Gets the list of book ISBNs associated with this category.
     * 
     * @return The list of book ISBNs.
     */
    public List<String> getCatBooksISBN() {
        return catBooksISBN;
    }
    
}
