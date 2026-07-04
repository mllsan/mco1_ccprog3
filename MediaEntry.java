public class MediaEntry {
    private String title;
    private Status status;
    private int rating;
    private String review;

    public MediaEntry(String title, Status status, int rating, String review){
        this.title = title;
        this.status = status;
        this.rating = rating;
        this.review = review;
    }

    public String getTitle(){
        return title;
    }

    public Status getStatus(){
        return status;
    }

    public void setStatus(Status status){
        this.status = status;
    }

    public int getRating(){
        return rating;
    }

    public void setRating(int rating){
        this.rating = rating;
    }

    public String getReview(){
        return review;
    }

    public void setReview(String review){
        this.review = review;
    }
}