public class Album extends MediaEntry {
    private String artist;

    public Album(String title, Status status, int rating, String review, String artist){
        super(title, status, rating, review);
        this.artist = artist;
    }

    public String getMediaType(){
        return "Album";
    }

    public String getArtist(){
        return artist;
    }

    public void setArtist(String artist){
        this.artist = artist;
    }
}
