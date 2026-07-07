import java.util.ArrayList;

public class Library implements java.io.Serializable {
    private ArrayList<MediaEntry> entries;

    public Library() {
        entries = new ArrayList<>();
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

        if(entry == null)
            System.out.println(title + " not found.");
        else 
            displayEntry(entry, true, true);
    }

    public void displayEntries() {
        System.out.println("\n=========== ALL ENTRIES ===========");

        if(entries.isEmpty()) {
                System.out.println("Library is empty.");
        } else {
            for(MediaEntry entry : entries) {
                displayEntry(entry, true, true);
                System.out.println("-----------------------------------");
            }
        }
    }

    public void displayEntriesByStatus(Status status) {
        boolean found = false;

        System.out.println("\n======== " + status + " ENTRIES =======");

        if(entries.isEmpty()) {
                System.out.println("Library is empty.");
        } else {
            for(MediaEntry entry : entries) {
                if(entry.getStatus() == status) {
                    found = true;
                    displayEntry(entry, false, true);
                    System.out.println("-----------------------------------");
                }
            }

            if(!found) {
                System.out.println("No " + status.toString().toLowerCase() + " entries found.");
                System.out.println("-----------------------------------");
            }
        }
    }

    public void displayEntriesByMediaType(String type) {
        boolean found = false;

        System.out.println("\n========= " + type.toUpperCase() + " ENTRIES ========");
        if(entries.isEmpty()) {
                System.out.println("Library is empty.");
        } else {
            for(MediaEntry entry : entries) {
                if(entry.getMediaType().equalsIgnoreCase(type)) {
                    displayEntry(entry, true, false);
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
        int planned = 0, inProgress = 0, completed = 0;
        int totalRating = 0, ratedEntries = 0;

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

                    if(entry.getRating() != -1) {
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

    private void displayEntry(MediaEntry entry, boolean showStatus, boolean showMediaType) {
        System.out.println("Title: " + entry.getTitle());

        if (showMediaType)
            System.out.println("Media Type: " + entry.getMediaType());

        if(entry instanceof Album)
            System.out.println("Artist: " + ((Album) entry).getArtist());
        else if(entry instanceof Anime)
            System.out.println("Number of Episodes: " + ((Anime) entry).getNumEps());
        else if(entry instanceof Movie)
            System.out.println("Duration: " + ((Movie) entry).getDuration() + " minutes");
        
        if (showStatus)
            System.out.println("Status: " + entry.getStatus());

        System.out.println("Rating: " + entry.getDisplayRating());
        System.out.println("Review: " + entry.getDisplayReview());
    }
}
