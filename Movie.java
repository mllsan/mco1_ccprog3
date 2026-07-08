/**
 * Represents a movie entry in the media library.
 *
 * Relationships:
 * - Inherits from the MediaEntry superclass.
 * - Adds the movie duration attribute.
 */
public class Movie extends MediaEntry {
    private int duration;

    /**
     * Constructor for the Movie class.
     * Initializes the common MediaEntry attributes and
     * the movie-specific duration.
     *
     * @param title the movie title
     * @param status the current status
     * @param rating the user's rating
     * @param review the user's review
     * @param duration the movie duration in minutes
     */
    public Movie(String title, Status status, int rating, String review, int duration){
        super(title, status, rating, review);
        this.duration = duration;
    }

    /**
     * Returns the media type of this entry.
     *
     * @return "Movie"
     */
    public String getMediaType(){
        return "Movie";
    }

    /**
     * Returns the duration of the movie.
     *
     * @return the duration in minutes
     */
    public int getDuration(){
        return duration;
    }

    /**
     * Updates the duration of the movie.
     *
     * @param duration the new duration in minutes
     */
    public void setDuration(int duration) {
        this.duration = duration;
    }
}
