import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        MediaInput media = new MediaInput();
        User activeAccount = null;

        while (activeAccount == null) {
            System.out.println("[1] Login to Existing Account");
            System.out.println("[2] Create New Account");
            System.out.print(">> ");
            
            int loginChoice = Integer.parseInt(scanner.nextLine());

            if (loginChoice == 1) {
                System.out.print("Enter Username: ");
                String username = scanner.nextLine();
                
                activeAccount = SaveData.loadAccount(username);
                
            } 
            else if (loginChoice == 2) {
                System.out.print("Create a New Username: ");
                String newUsername = scanner.nextLine();
                
                activeAccount = new User(newUsername);
                System.out.println("User '" + newUsername + "' Created Successfully!");
                
                SaveData.saveAccount(activeAccount);
            } 
            else {
                System.out.println("Error: Invalid choice\n");
            }
        }

        boolean running = true;
        while (running) {
            System.out.println("\n===================================");
            System.out.println("     MAIN MENU - " + activeAccount.getUsername().toUpperCase());
            System.out.println("===================================");
            System.out.println("[1] View My Library");
            System.out.println("[2] Add Media Reviews");
            System.out.println("[3] View Library Summary");
            System.out.println("[4] Save Progress");
            System.out.println("[0] Save and Exit");
            System.out.print(">> ");
            
            int mainChoice = Integer.parseInt(scanner.nextLine());

            switch (mainChoice) {
                case 1:
                    activeAccount.getLibrary().displayEntries();
                    break;
                    
                case 2:
                    System.out.println("Choose an operation:");
                    System.out.println("[1] Add Anime");
                    System.out.println("[2] Add Movie");
                    System.out.println("[3] Add Album");
                    System.out.println("[4] Edit Existing Media");
                    System.out.print(">> ");
                    int mediaChoice = Integer.parseInt(scanner.nextLine());
                    
                    if (mediaChoice == 1) {
                        Anime anime = media.createAnimeReview();
                        activeAccount.getLibrary().addEntry(anime);
                    } 
                    else if (mediaChoice == 2) {
                        Movie movie = media.createMovieReview();
                        activeAccount.getLibrary().addEntry(movie);
                    } 
                    else if (mediaChoice == 3) {
                        Album album = media.createAlbumReview();
                        activeAccount.getLibrary().addEntry(album);
                    } 
                    else {
                        System.out.println("Error: Invalid choice");
                    }
                    break;
                    
                case 3:
                    activeAccount.getLibrary().displaySummary();
                    break;
                    
                case 4:
                    SaveData.saveAccount(activeAccount);
                    break;
                    
                case 0:
                    SaveData.saveAccount(activeAccount);
                    System.out.println("Exiting Application, Goodbye!");
                    running = false;
                    break;
                    
                default:
                    System.out.println("Error: Invalid Choice");
            }
        }
        scanner.close();
    }
}