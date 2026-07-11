import java.util.Scanner;

/**
 * Handles user input for creating and editing media entries.
 *
 * Relationships:
 * - Uses a Scanner object to receive user input.
 * - Interacts with the Library to manage media entries.
 * - Creates and modifies MediaEntry objects and its subclasses.
 * - Used by Main to process user input for media entries.
 */
public class MediaInput {
    private Scanner input = new Scanner(System.in);

    /**
     * Prompts the user to select a status for a media entry.
     *
     * @return the Status selected by the user
     */
    private Status statusChosen(){
        System.out.println("Select Status:");
        System.out.println("[1] Planned");
        System.out.println("[2] In Progress");
        System.out.println("[3] Completed");
        System.out.print(">> ");
        int choice = InputChecker.getValidInput(input,1,3);

        switch (choice){
            case 1: return Status.PLANNED;
            case 2: return Status.INPROGRESS;
            case 3: return Status.COMPLETED;
            default:
                System.out.println("Invalid selection, relocating to PLANNED");
                return Status.PLANNED;
        }
    }

    /**
     * Creates a new media entry based on the user's selected media type.
     * Prompts the user to enter the required information.
     *
     * @param mediaChoice the selected media type
     * @param library the user's library
     * @return the created MediaEntry
     */
    public MediaEntry createEntry(int mediaChoice, Library library){
        int rating = -1, numOfEps = 0, duration = 0;
        String review = "", artist = "";
        MediaEntry result = null;

        System.out.print("Enter Title: ");
        String title = input.nextLine();

        if (library.getEntry(title) != null) {
            System.out.println("Error: An Entry with the Title '" + title + "' Already Exists.");
        } else {
            Status status = statusChosen();

            if (mediaChoice == 1){
                System.out.print("Enter Number of Episodes: ");
                numOfEps = Integer.parseInt(input.nextLine());
            } else if (mediaChoice == 2){
                System.out.print("Enter Duration (minutes): ");
                duration = Integer.parseInt(input.nextLine());
            } else if (mediaChoice == 3){
                System.out.print("Enter Artist Name: ");
                artist = input.nextLine();
            }

            if (status == Status.COMPLETED){
                System.out.print("Enter Rating (1-10): ");
                rating = InputChecker.getValidInput(input,1,10);

                System.out.print("Enter Review: ");
                review = input.nextLine();
            }

            if (mediaChoice == 1){
                result = new Anime(title, status, rating, review, numOfEps);
            } else if (mediaChoice == 2){
                result = new Movie(title, status, rating, review, duration);

            } else if (mediaChoice == 3){
                result = new Album(title, status, rating, review, artist);
            }
        }

        return result;
    }

    /**
     * Allows the user to edit the attributes of an existing media entry.
     * Prompts the user to enter the updated information of the 
     * attributes they wish to edit.
     *
     * @param entry the MediaEntry to edit
     * @param library the user's library
     */
    public void editEntry(MediaEntry entry, Library library){
        int choice; 

        do {
            System.out.println("\n--- Editing " + entry.getTitle() + " ---");
            System.out.println("[1] Edit Title");
            System.out.println("[2] Edit Status");
            System.out.println("[3] Edit Rating");
            System.out.println("[4] Edit Review");

            if (entry.getMediaType().equals("Anime")) 
                System.out.println("[5] Edit Number of Episodes");
            else if (entry.getMediaType().equals("Movie"))
                System.out.println("[5] Edit Duration");
            else if (entry.getMediaType().equals("Album"))
                System.out.println("[5] Edit Artist Name");

            System.out.println("[6] Finish & Go Back");
            System.out.print(">> ");
            choice = InputChecker.getValidInput(input,1,6);
            System.out.println();

            switch (choice){
                case 1:
                    System.out.print("Enter New Title: ");
                    String newTitle = input.nextLine();

                    if (library.getEntry(newTitle) != null) {
                        System.out.println("An Entry with that Title Already Exists.");
                    } else {
                        entry.setTitle(newTitle);
                        System.out.println("Title Updated!");
                    }
                    break;
                case 2:
                    Status newStatus = statusChosen();
                    entry.setStatus(newStatus);
                    System.out.print("Enter Rating (1-10): ");
                    entry.setRating(InputChecker.getValidInput(input, 1, 10));

                    System.out.print("Enter Review: ");
                    entry.setReview(input.nextLine());
                    System.out.println("Status Updated!");
                    break;
                case 3:
                    if (entry.getStatus() != Status.COMPLETED){
                        System.out.println("Only Completed Entries can be Rated.");
                    } else {
                        System.out.print("Enter New Rating (1-10): ");
                        entry.setRating(InputChecker.getValidInput(input,1,10));
                        System.out.println("Rating Updated!");
                    }
                    break;
                case 4:
                    if (entry.getStatus() != Status.COMPLETED){
                        System.out.println("Only Completed Entries can be Reviewed.");
                    } else {
                        System.out.print("Enter New Review: ");
                        entry.setReview(input.nextLine());
                        System.out.println("Review Updated!");
                    }
                    break;
                case 5:
                    if (entry instanceof Anime) {
                        System.out.print("Enter New Number of Episodes: ");
                        ((Anime) entry).setNumEps(Integer.parseInt(input.nextLine()));
                        System.out.println("Episodes Updated!");
                    } else if (entry instanceof Movie) {
                        System.out.print("Enter New Duration (minutes): ");
                        ((Movie) entry).setDuration(Integer.parseInt(input.nextLine()));
                        System.out.println("Duration Updated!");
                    } else if (entry instanceof Album) {
                        System.out.print("Enter New Artist Name: ");
                        ((Album) entry).setArtist(input.nextLine());
                        System.out.println("Artist Updated!");
                    }
                    break;
                case 6:
                    System.out.println("Returning to Main Menu . . .");
                    break;
                default:
                    System.out.println("Error: Invalid Option");
                }
        } while (choice != 6);
    }
}
