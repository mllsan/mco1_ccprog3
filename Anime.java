/**
 * Represents an anime entry in the media library.
 *
 * Relationships:
 * - Inherits from the MediaEntry superclass.
 * - Adds the number of episodes specific to anime.
 */
public class Anime extends MediaEntry {
    private int numOfEps;

    /**
     * Constructor for the Anime class.
     * Initializes the common MediaEntry attributes and
     * the anime-specific episode count.
     *
     * @param title the title of the anime
     * @param status the current status
     * @param rating the user's rating
     * @param review the user's review
     * @param numOfEps the total number of episodes
     */
    public Anime(String title, Status status, int rating, String review, int numOfEps){
        super(title, status, rating, review);
        this.numOfEps = numOfEps;
    }

    /**
     * Returns the media type of this entry.
     *
     * @return "Anime"
     */
    public String getMediaType(){
        return "Anime";
    }

    /**
     * Returns the number of episodes.
     *
     * @return the total number of episodes
     */
    public int getNumEps(){
        return numOfEps;
    }

    /**
     * Updates the number of episodes.
     *
     * @param numOfEps the new number of episodes
     */
    public void setNumEps(int numOfEps){
        this.numOfEps = numOfEps;
    }
}
