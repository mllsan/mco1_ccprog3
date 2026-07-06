import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        MediaInput media = new MediaInput();
        User activeAccount = null;

        while (activeAccount == null){
            System.out.println("[1] Login to Existing Account");
            System.out.println("[2] Create New Account");
            System.out.print(">> ");
            
            int loginChoice = Integer.parseInt(scanner.nextLine());
        
            if (loginChoice == 1){
                System.out.print("Enter Username: ");
                String username = scanner.nextLine();
                activeAccount = SaveData.loadAccount(username);
            }
            else if (loginChoice == 2){
                System.out.print("Create New Username: ");
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
        while (running){
            System.out.println("\n===================================");
            System.out.println("     MAIN MENU - " + activeAccount.getUsername().toUpperCase());
            System.out.println("===================================");
            System.out.println("[1] View My Library");
            System.out.println("[2] Add Media Entry");
            System.out.println("[3] Edit Existing Entry");
            System.out.println("[4] View Library Summary");
            System.out.println("[0] Save and Exit");
            System.out.print(">> ");
            int mainChoice = InputChecker.getValidInput(scanner,0,5);
            System.out.println("");

            switch (mainChoice){
                case 1:
                    System.out.println("View Library By:");
                    System.out.println("[1] All Entries");
                    System.out.println("[2] Status");
                    System.out.println("[3] Media Type");
                    System.out.print(">> ");
                    int viewChoice = InputChecker.getValidInput(scanner,1,3);
                    System.out.println("");

                    switch (viewChoice) {
                        case 1:
                            activeAccount.getLibrary().displayEntries();
                            break;
                        case 2:
                            System.out.println("Choose Status:");
                            System.out.println("[1] Planned");
                            System.out.println("[2] In Progress");
                            System.out.println("[3] Completed");
                            System.out.print(">> ");
                            int statusChoice = InputChecker.getValidInput(scanner,1,3);
                            System.out.println("");

                            switch (statusChoice){
                                case 1: 
                                    activeAccount.getLibrary().displayEntriesByStatus(Status.PLANNED);
                                    break;
                                case 2: 
                                    activeAccount.getLibrary().displayEntriesByStatus(Status.INPROGRESS);
                                    break;
                                case 3: 
                                    activeAccount.getLibrary().displayEntriesByStatus(Status.COMPLETED);
                                    break;  
                                default:
                                    System.out.println("Error: Invalid Option");
                            }
                            break;
                        case 3:
                            System.out.println("Choose Media Type:");
                            System.out.println("[1] Anime");
                            System.out.println("[2] Movie");
                            System.out.println("[3] Album");
                            System.out.print(">> ");
                            int typeChoice = InputChecker.getValidInput(scanner,1,3);

                            switch (typeChoice) {
                                case 1: 
                                    activeAccount.getLibrary().displayEntriesByMediaType("Anime");
                                    break;
                                case 2:
                                    activeAccount.getLibrary().displayEntriesByMediaType("Movie");
                                    break;
                                case 3:
                                    activeAccount.getLibrary().displayEntriesByMediaType("Album");
                                    break;
                                default:
                                    System.out.println("Error: Invalid Option");
                            }
                    }
                    break;
                    
                case 2:
                    System.out.println("Choose which Media to add:");
                    System.out.println("[1] Anime");
                    System.out.println("[2] Movie");
                    System.out.println("[3] Album");
                    System.out.print(">> ");
                    int mediaChoice = InputChecker.getValidInput(scanner,1,3);
                    
                    if (mediaChoice == 1){
                        Anime anime = media.createAnimeReview();
                        activeAccount.getLibrary().addEntry(anime);
                    } 
                    else if (mediaChoice == 2){
                        Movie movie = media.createMovieReview();
                        activeAccount.getLibrary().addEntry(movie);
                    } 
                    else if (mediaChoice == 3){
                        Album album = media.createAlbumReview();
                        activeAccount.getLibrary().addEntry(album);
                    }
                    else{
                        System.out.println("Error: Invalid Option");
                    }
                    break;
                    
                case 3:
                    System.out.println("Enter the title of the entry you want to edit:");
                    System.out.print(">> ");
                    String title = scanner.nextLine();
                    
                    MediaEntry entry = activeAccount.getLibrary().getEntry(title);

                    if (entry == null) {
                        System.out.println(title + " not found.");
                    } else {
                        // need to edit MediaInput first
                    }
                    break;

                case 4:
                    activeAccount.getLibrary().displaySummary();
                    break;
                    
                case 0:
                    SaveData.saveAccount(activeAccount);
                    System.out.println("Exiting Application...");
                    running = false;
                    break;
                    
                default:
                    System.out.println("Error: Invalid Action");
            }
        }
        scanner.close();
    }
}
