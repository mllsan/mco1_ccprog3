/**
 * Represents an album entry in the media library.
 *
 * Relationships:
 * - Inherits from the MediaEntry superclass.
 * - Adds the artist attribute that is unique to albums.
 */
public class Album extends MediaEntry {
    private String artist;

    /**
     * Constructor for the Album class.
     * Initializes the common MediaEntry attributes and the
     * album-specific artist attribute.
     *
     * @param title the title of the album
     * @param status the current status of the album
     * @param rating the user's rating
     * @param review the user's review
     * @param artist the artist of the album
     */
    public Album(String title, Status status, int rating, String review, String artist){
        // Initializes the inherited MediaEntry attributes.
        super(title, status, rating, review);
        this.artist = artist;
    }

    /**
     * Returns the media type.
     *
     * @return "Album"
     */
    public String getMediaType(){
        return "Album";
    }

    /**
     * Returns the artist of the album.
     *
     * @return the artist name
     */
    public String getArtist(){
        return artist;
    }

    /**
     * Updates the artist of the album.
     *
     * @param artist the new artist name
     */
    public void setArtist(String artist){
        this.artist = artist;
    }
}
