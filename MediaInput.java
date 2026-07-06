import java.util.Scanner;

public class MediaInput {
    private Scanner input = new Scanner(System.in);

    private Status statusChosen(){
        System.out.println("Select Status:");
        System.out.println("[1] Planned");
        System.out.println("[2] In Progress");
        System.out.println("[3] Completed");
        System.out.print(">> ");
        int choice = Integer.parseInt(input.nextLine());

        switch (choice){
            case 1: return Status.PLANNED;
            case 2: return Status.INPROGRESS;
            case 3: return Status.COMPLETED;
            default:
                System.out.println("Invalid selection, relocating to PLANNED");
                return Status.PLANNED;
        }
    }

    public MediaEntry createMediaReview(int mediaChoice, Library library){
        int rating = 0, numOfEps = 0, duration = 0, reviewExists = 1;
        String review = "", artist = "";

        System.out.print("Enter Title: ");
        String title = input.nextLine();

        if (library.getEntry(title) != null) {
            System.out.println("Error: An Entry with the Title '" + title + "' Already Exists in the Library.");
            reviewExists = -1; 
        }

        if (reviewExists!=-1){
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

            if (status != Status.PLANNED){
                System.out.print("Enter Rating (1-5): ");
                rating = Integer.parseInt(input.nextLine());

                System.out.print("Enter Review: ");
                review = input.nextLine();
            }

            if (mediaChoice == 1){
                return new Anime(title, status, rating, review, numOfEps);
            } else if (mediaChoice == 2){
                return new Movie(title, status, rating, review, duration);

            } else if (mediaChoice == 3){
                return new Album(title, status, rating, review, artist);
            }
        }

        return null;
    }

    public void editAnimeReview(Library library){
        System.out.print("Search Anime Title: ");
        String title = input.nextLine();

        MediaEntry entry = library.getEntry(title);

        if (entry == null){
            System.out.println("'" + title + "' Not Found");
        } 
        else{
            Anime anime = (Anime) entry;
            boolean editing = true;

            while (editing){
                System.out.println("\n--- Editing " + anime.getTitle() + " ---");
                System.out.println("[1] Edit Title");
                System.out.println("[2] Edit Status");
                System.out.println("[3] Edit Rating and Review");
                System.out.println("[4] Edit Number of Episodes");
                System.out.println("[5] Finish & Go Back");
                System.out.print(">> ");
                int choice = Integer.parseInt(input.nextLine());

                switch (choice){
                    case 1:
                        System.out.print("Enter New Title: ");
                        anime.setTitle(input.nextLine());
                        System.out.println("Title Updated!");
                        break;
                    case 2:
                        Status newStatus = statusChosen();
                        anime.setStatus(newStatus);
                        
                        if (newStatus == Status.PLANNED){
                            anime.setRating(0);
                            anime.setReview("");
                            System.out.println("Status Updated!");
                        } 
                        else{
                            System.out.println("Status Updated!");
                        }
                        break;
                    case 3:
                        if (anime.getStatus() == Status.PLANNED){
                            System.out.println("Cannot Add a Rating and Review to a PLANNED Anime");
                        } 
                        else{
                            System.out.print("Enter New Rating (1-5): ");
                            anime.setRating(Integer.parseInt(input.nextLine()));
                            System.out.print("Enter New Review: ");
                            anime.setReview(input.nextLine());
                            System.out.println("Rating Updated!");
                        }
                        break;
                    case 4:
                            System.out.print("Enter New Number of Episodes: ");
                            anime.setNumEps(Integer.parseInt(input.nextLine()));
                            System.out.println("Episodes Updated!");
                        break;
                    case 5:
                        editing = false;
                        break;
                    default:
                        System.out.println("Error: Invalid Option");
                }
            }
        }
    }

    public void editMovieReview(Library library){
        System.out.print("Search Movie Title: ");
        String title = input.nextLine();

        MediaEntry entry = library.getEntry(title);

        if (entry == null){
            System.out.println("'" + title + "' Not Found");
        }
        else{
            Movie movie = (Movie) entry;
            boolean editing = true;

            while (editing) {
                System.out.println("\n--- Editing " + movie.getTitle() + " ---");
                System.out.println("[1] Edit Title");
                System.out.println("[2] Edit Status");
                System.out.println("[3] Edit Rating");
                System.out.println("[4] Edit Review");
                System.out.println("[5] Edit Duration");
                System.out.println("[6] Finish & Go Back");
                System.out.print(">> ");
                int choice = Integer.parseInt(input.nextLine());

                switch (choice){
                    case 1:
                        System.out.print("Enter New Title: ");
                        movie.setTitle(input.nextLine());
                        System.out.println("Title Updated!");
                        break;
                    case 2:
                        Status newStatus = statusChosen();
                        movie.setStatus(newStatus);
                        
                        if (newStatus == Status.PLANNED){
                            movie.setRating(0);
                            movie.setReview("");
                            System.out.println("Status Updated!");
                        } 
                        else{
                            System.out.println("Status Updated!");
                        }
                        break;
                    case 3:
                        if (movie.getStatus() == Status.PLANNED) {
                            System.out.println("Cannot Add a Rating and Review to a PLANNED Movie");
                        } else {
                            System.out.print("Enter New Rating (1-5): ");
                            movie.setRating(Integer.parseInt(input.nextLine()));
                            System.out.print("Enter New Review: ");
                            movie.setReview(input.nextLine());
                            System.out.println("Rating Updated!");
                        }
                        break;
                    case 4:
                            System.out.print("Enter New Duration (minutes): ");
                            movie.setDuration(Integer.parseInt(input.nextLine()));
                            System.out.println("Duration Updated!");
                        break;
                    case 5:
                        editing = false;
                        break;
                    default:
                        System.out.println("Error: Invalid Option");
                }
            }
        }
    }

    public void editAlbumReview(Library library){
        System.out.print("Search Album Title: ");
        String title = input.nextLine();

        MediaEntry entry = library.getEntry(title);

        if (entry == null){
            System.out.println("'" + title + "' Not Found");
        }
        else{
            Album album = (Album) entry;
            boolean editing = true;

            while (editing){
                System.out.println("\n--- Editing " + album.getTitle() + " ---");
                System.out.println("[1] Edit Title");
                System.out.println("[2] Edit Status");
                System.out.println("[3] Edit Rating");
                System.out.println("[4] Edit Review");
                System.out.println("[5] Edit Artist Name");
                System.out.println("[6] Finish & Go Back");
                System.out.print(">> ");
                int choice = Integer.parseInt(input.nextLine());

                switch (choice){
                    case 1:
                        System.out.print("Enter New Title: ");
                        album.setTitle(input.nextLine());
                        System.out.println("Title Updated!");
                        break;
                    case 2:
                        Status newStatus = statusChosen();
                        album.setStatus(newStatus);
                        
                        if (newStatus == Status.PLANNED){
                            album.setRating(0);
                            album.setReview("");
                            System.out.println("Status Updated!");
                        } 
                        else{
                            System.out.println("Status Updated!");
                        }
                        break;
                    case 3:
                        if (album.getStatus() == Status.PLANNED){
                            System.out.println("Cannot Add a Rating and Review to a PLANNED Album");
                        } 
                        else{
                            System.out.print("Enter New Rating (1-5): ");
                            album.setRating(Integer.parseInt(input.nextLine()));
                            System.out.print("Enter New Review: ");
                            album.setReview(input.nextLine());
                            System.out.println("Rating Updated!");
                        }
                        break;
                    case 4:
                            System.out.print("Enter New Artist Name: ");
                            album.setArtist(input.nextLine());
                            System.out.println("Artist Updated!");
                        break;
                    case 5:
                        editing = false;
                        break;
                    default:
                        System.out.println("Error: Invalid Option");
                }
            }
        }
    }
}
