public abstract class MediaEntry implements java.io.Serializable {
    private String title;
    private Status status;
    private int rating;
    private String review;

    public MediaEntry(String title, Status status, int rating, String review){
        this.title = title;
        this.status = status;

        if(status == Status.COMPLETED) {
            this.rating = rating;
            this.review = review;
        } else {
            this rating = -1;
            this.review = "";
        }
    }

    public abstract String getMediaType();

    public String getTitle(){
        return title;
    }

    public void setTitle(String title) {
        if (title == null || title.isBlank()) {
            System.out.println("Title cannot be empty.");
        } else {
            this.title = title;
        }
    }

    public Status getStatus(){
        return status;
    }

    public void setStatus(Status status){
        this.status = status;

        if (status != Status.COMPLETED) {
            rating = -1;
            review = "";
        }
    }

    public int getRating(){
        return rating;
    }

    public void setRating(int rating){
        if (status == Status.COMPLETED) {
            if (rating < 1 || rating > 10) 
                System.out.println("Rating must be between 1 and 10");
            else
                this.rating = rating;
        } else {
            System.out.println("Only completed entries can be rated.");
            this.rating = -1;
        }
    }

    public String getReview(){
        return review;
    }

    public void setReview(String review){
        if (status == Status.COMPLETED)
            this.review = review;
        else {
            System.out.println("Only completed entries can be reviewed.");
            this.review = "";
        }
    }
}
