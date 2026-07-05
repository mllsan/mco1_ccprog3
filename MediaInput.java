import java.util.Scanner;

public class MediaInput {
    private Scanner input = new Scanner(System.in);

    private Status statusChosen() {
        System.out.println("Select Status:");
        System.out.println("[1] Planned");
        System.out.println("[2] In Progress");
        System.out.println("[3] Completed");
        System.out.print(">> ");
        int choice = Integer.parseInt(input.nextLine());

        switch (choice) {
            case 1: return Status.PLANNED;
            case 2: return Status.INPROGRESS;
            case 3: return Status.COMPLETED;
            default:
                System.out.println("Invalid selection, relocating to PLANNED");
                return Status.PLANNED;
        }
    }

    public Anime createAnimeReview() {
        System.out.println("\n--- Add New Anime ---");
        
        System.out.print("Enter Title: ");
        String title = input.nextLine();

        Status status = statusChosen();

        System.out.print("Enter Number of Episodes: ");
        int numOfEps = Integer.parseInt(input.nextLine());

        int rating = 0;
        String review = "N/A";

        if (status != Status.PLANNED) {
            System.out.print("Enter Rating (1-5): ");
            rating = Integer.parseInt(input.nextLine());

            System.out.print("Enter Review: ");
            review = input.nextLine();
        }

        return new Anime(title, status, rating, review, numOfEps);
    }

    public Movie createMovieReview() {
        System.out.println("\n--- Add New Movie ---");
        
        System.out.print("Enter Title: ");
        String title = input.nextLine();

        Status status = statusChosen();

        System.out.print("Enter Duration (in minutes): ");
        int duration = Integer.parseInt(input.nextLine());

        int rating = 0;
        String review = "N/A";

        if (status != Status.PLANNED) {
            System.out.print("Enter Rating (1-5): ");
            rating = Integer.parseInt(input.nextLine());

            System.out.print("Enter Review: ");
            review = input.nextLine();
        }

        return new Movie(title, status, rating, review, duration);
    }

    public Album createAlbumReview() {
        System.out.println("\n--- Add New Album ---");
        
        System.out.print("Enter Title: ");
        String title = input.nextLine();

        Status status = statusChosen();

        System.out.print("Enter Artist Name: ");
        String artist = input.nextLine();

        int rating = 0;
        String review = "N/A";

        if (status != Status.PLANNED) {
            System.out.print("Enter Rating (1-5): ");
            rating = Integer.parseInt(input.nextLine());

            System.out.print("Enter Review: ");
            review = input.nextLine();
        }

        return new Album(title, status, rating, review, artist);
    }
}
