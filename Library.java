import java.util.ArrayList;

public class Library {
    private ArrayList<MediaEntry> entries;

    public Library() {
        entry = new ArrayList<>();
    }

    public MediaEntry getEntry(String title) {
        for(MediaEntry entry : entries) {
            if(entry.getTitle().equalsIgnoreCase(title)) {
                return entry;
            }
        }
        return null;
    }

    public void addEntry(MediaEntry entry) {
        if(getEntry(entry.getTitle()) != null) {
            System.out.println("Entry already exists.");
        } else {
            entries.add(entry);
            System.out.println("Entry added successfully!");
        }
    }

    public void removeEntry(String title) {
        MediaEntry entry = getEntry(title);

        if(entry == null) {
            System.out.println("Entry not found.");
        } else {
            entries.remove(entry);
            System.out.println("Entry removed successfully!");
        }
    }

    public void retrieveEntry(String title) {
        MediaEntry entry = getEntry(title);

        if(entry == null) {
            System.out.println(title + " not found.");
        } else {
            System.out.println("Title: " + entry.getTitle());
            System.out.println("Media Type: " + entry.getMediaType());
            System.out.println("Status: " + entry.getStatus());
            System.out.println("Rating: " + entry.getRating());
            System.out.println("Review: " + entry.getReview());
        }
    }

    public void displayEntries() {
        System.out.println("\n=========== ALL ENTRIES ===========");

        if(entries.isEmpty()) {
                System.out.println("Library is empty.");
        } else {
            for(MediaEntry entry : entries) {
                System.out.println("Title: " + entry.getTitle());
                System.out.println("Media Type: " + entry.getMediaType());

                if(entry.getMediaType().equals("Album")) {
                    System.out.println("Artist: " + entry.getArtist());
                } else if(entry.getMediaType().equals("Anime")) {
                    System.out.println("Number of Episodes: " + entry.getNumEps());
                } else {
                    System.out.println("Duration: " + entry.getDuration() + "minutes");
                }

                System.out.println("Status: " + entry.getStatus());
                System.out.println("Rating: " + entry.getRating());
                System.out.println("Review: " + entry.getReview());
                System.out.println("-----------------------------------");
            }
        }
    }

    public void displayEntriesByStatus(Status status) {
        boolean found = false;

        System.out.println("\n======== ENTRIES BY " + status + " =======");

        if(entries.isEmpty()) {
                System.out.println("Library is empty.");
        } else {
            for(MediaEntry entry : entries) {
                if(entry.getStatus() == status) {
                    found = true;
                    System.out.println("Title: " + entry.getTitle());
                    System.out.println("Media Type: " + entry.getMediaType());

                    if(entry.getMediaType().equals("Album")) {
                        System.out.println("Artist: " + entry.getArtist());
                    } else if(entry.getMediaType().equals("Anime")) {
                        System.out.println("Number of Episodes: " + entry.getNumEps());
                } else {
                        System.out.println("Duration: " + entry.getDuration() + "minutes");
                    }

                    System.out.println("Rating: " + entry.getRating());
                    System.out.println("Review: " + entry.getReview());
                    System.out.println("-----------------------------------");
                }
            }

            if(!found) {
                System.out.println("No " + status.toLowerCase() + " entries found.");
                System.out.println("-----------------------------------");
            }
        }
    }

    public void displayEntriesByMediaType(String type) {
        boolean found = false;

        System.out.println("\n========= ENTRIES BY " + type.toUpperCase() + " ========");
        if(entries.isEmpty()) {
                System.out.println("Library is empty.");
        } else {
            for(MediaEntry entry : entries) {
                if(entry.getMediaType().equalsIgnoreCase(type)) {
                    found = true;
                    System.out.println("Title: " + entry.getTitle());

                    if(type.equals("Album")) {
                        System.out.println("Artist: " + entry.getArtist());
                    } else if(type.equals("Anime")) {
                        System.out.println("Number of Episodes: " + entry.getNumEps());
                    } else {
                        System.out.println("Duration: " + entry.getDuration() + "minutes");
                    }

                    System.out.println("Status: " + entry.getStatus());
                    System.out.println("Rating: " + entry.getRating());
                    System.out.println("Review: " + entry.getReview());
                    System.out.println("-----------------------------------");
                }
            }

            if(!found) {
                System.out.println("No " + type + " Entries Found.");
                System.out.println("-----------------------------------");
            }
        }
    }

    public void displaySummary() {
        int planned = 0;
        int inProgress = 0;
        int completed = 0;
        int totalRating = 0;
        int ratedEntries = 0;

        for(MediaEntry entry : entries) {
            switch(entry.getStatus()) {
                case PLANNED:
                    planned++;
                    break;
               case INPROGRESS:
                    inProgress++;
                    break;
                case COMPLETED:
                    completed++;

                    if(entry.getRating() > -1) {
                        ratedEntries++;
                        totalRating += entry.getRating();
                    }

                    break;
            }
        }

        System.out.println("\n========= LIBRARY SUMMARY =========");
        System.out.println("Total Entries: " + entries.size());
        System.out.println("-----------------------------------");
        System.out.println("Planned: " + planned);
        System.out.println("In Progress: " + inProgress);
        System.out.println("Completed: " + completed);
        System.out.println("-----------------------------------");

        if(ratedEntries > 0) {
            double average = (double) totalRating / ratedEntries;
            System.out.printf("Average Rating: %.2f\n", average);
        } else {
            System.out.println("Average Rating: N/A");
        }
    }
}
