public class Anime extends MediaEntry {
    private int numOfEps;

    public Anime(String title, Status status, int rating, String review, int numOfEps){
        super(title, status, rating, review);
        this.numOfEps = numOfEps;
    }

    public int getNumEps(){
        return numOfEps;
    }
}