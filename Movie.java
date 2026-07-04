public class Movie extends MediaEntry {
    private int duration;

    public Movie(String title, Status status, int rating, String review, int duration){
        super(title, status, rating, review);
        this.duration = duration;
    }

    public int getDuration(){
        return duration;
    }
}