package Model;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import CustomExceptions.DateException;
import CustomExceptions.InvalidRatingException;
import CustomExceptions.NegativeCopiesException;
import CustomExceptions.NotUniqueException;

public class Book implements Serializable{

    private String title;
    private String author;
    private String publisher;
    private String ISBN;
    private int release_year;
    private int copies;
    private double avgRating;
    private List<Reviews> reviews;
    private List<Ratings> ratings;

    public Book(String title, String author, String publisher, String ISBN, int release_year, int copies){
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.ISBN = ISBN;
        this.release_year = release_year;
        this.copies = copies;
        this.avgRating = 0;
        this.reviews = new ArrayList<>();
        this.ratings = new ArrayList<>();    
    }

    public void change_avgRating(){
        
        if (this.ratings.isEmpty()){
            this.avgRating = 0;
        }
        else {
            int ratingSum = 0;
            for (Ratings r : this.ratings){
                ratingSum += r.rating;
            }
            double sum = (double) ratingSum;
            double s = (double) ratings.size();
            double avg = sum / s;
            
            this.avgRating = Math.round(avg * 100.0) / 100.0;
        }
    }  
    
    public void setTitle(String title) {
        this.title = title;
    }
 
    public String getTitle() {
        return title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getAuthor() {
        return author;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setISBN(String iSBN) throws NotUniqueException {
        for (Book b : LibraryManager.allBooks){
           if (b.getISBN().equals(iSBN)){
                throw new NotUniqueException("ISBN already taken. Please enter a unique ISBN.");
            }
        }
        ISBN = iSBN;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setRelease_year(int release_year) throws DateException {
        if (release_year > java.time.LocalDate.now().getYear()) {
            throw new DateException("Invalid Year. Please enter an integer that does not represent a future year.");
        }
        this.release_year = release_year;
    }

    public int getRelease_year() {
        return release_year;
    }

    public void setCopies(int copies) throws NegativeCopiesException {
        if (copies < 0) {
            throw new NegativeCopiesException();
        }
        this.copies = copies;
    }

    public int getCopies() {
        return copies;
    }

    public void setAvgRating(double avgRating) {
        this.avgRating = avgRating;
    }

    public double getAvgRating() {
        return avgRating;
    }

    public void setRatings(List<Ratings> ratings) {
        this.ratings = ratings;
    }

    public List<Ratings> getRatings() {
        return ratings;
    }

    public void setReviews(List<Reviews> reviews) {
        this.reviews = reviews;
    }

    public List<Reviews> getReviews() {
        return reviews;
    }

    public class Ratings implements Serializable{
        private String username;
        private int rating;
    
        public Ratings(String username, int rating){
            this.username = username;
            this.rating = rating;

        }

        public void setRating(int rating) {
            this.rating = rating;
        }

        public int getRating() {
            return rating;
        }

        public void setUsername(String username) {
            this.username = username;
        }
        public String getUsername() {
            return username;
        }
    }

    public class Reviews implements Serializable{
        private String username;
        private String review;
    
        public Reviews(String username, String review){
            this.username = username;
            this.review = review;
        }

        public void setReview(String review) {
            this.review = review;
        }

        public String getReview() {
            return review;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getUsername() {
            return username;
        }
    }
    

    public void createRating(String username, int rating){
        
        try {
            for (Ratings r : this.ratings){
                if (r.username.equals(username)){
                    if (rating>0 && rating<6){
                        r.setRating(rating);
                        change_avgRating();
                        return;
                    }      
                    else {
                        throw new InvalidRatingException();
                    }          
                }
            }
            if (rating>0 && rating<6){
                Ratings rate = new Ratings(username, rating);
                this.ratings.add(rate);
                change_avgRating();
            }
        }
        catch(InvalidRatingException i) {
            i.printStackTrace();
            //this will never happen, because i have tonggle group in frontend
        }
    }

    public void createReview(String username, String review){
        Reviews r = new Reviews(username, review);
        this.reviews.add(r);
    }

}
