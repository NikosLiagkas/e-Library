import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Book implements Serializable{

    private String title;
    private String author;
    private String publisher;
    private String ISBN;
    private int release_year;
    private String language;
    private int copies;
    private double avgRating;
    private List<Reviews> reviews;
    private List<Ratings> ratings;

    public Book(String title, String author, String publisher, String ISBN, int release_year, 
                String language, int copies){
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.ISBN = ISBN;
        this.release_year = release_year;
        this.language = language;
        this.copies = copies;
        this.avgRating = 0;
        this.reviews = new ArrayList<>();
        this.ratings = new ArrayList<>();    
    }

    public void change_avgRating(){
        if (ratings.isEmpty()){
            this.avgRating = 0;
        }
        else {
            int ratingSum = 0;
            for (Ratings r : this.ratings){
                ratingSum += r.rating;
            }
            this.avgRating = ratingSum / ratings.size();
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

    public void setISBN(String iSBN) {
        ISBN = iSBN;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setRelease_year(int release_year) {
        this.release_year = release_year;
    }

    public int getRelease_year() {
        return release_year;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getLanguage() {
        return language;
    }

    public void setCopies(int copies) {
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

    public class Ratings {
        private User user;
        private int rating;
    
        public Ratings(User user, int rating){
            this.user = user;
            this.rating = rating;
        }
    }

    public class Reviews {
        private User user;
        private String review;
    
        public Reviews(User user, String review){
            this.user = user;
            this.review = review;
        }
    }
    

    public void createRating(User user, int rating){
        if (rating>0 && rating<6){
            Ratings r = new Ratings(user, rating);
            this.ratings.add(r);
        }
    }

    public void createReview(User user, String review){
        Reviews r = new Reviews(user, review);
        this.reviews.add(r);
    }

}
