import java.io.*;

/**
 * Provides utility methods for saving and loading the user's account data.
 *
 * Relationships:
 * - Uses Java serialization to store and retrieve User objects.
 * - Interacts with the file system to manage user account files.
 * - Used by Main to manage user account data.
 */
public class SaveData {

    /**
     * Saves a user's account data to a serialized file.
     *
     * @param user the User object to save
     */
    public static void saveAccount(User user) {
        String fileName = user.getUsername() + ".sav";

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(user);
        } catch (IOException e) {
            System.out.println("Error Saving User: " + e.getMessage());
        }
    }

    /**
     * Loads a user's account from a serialized file.
     *
     * @param username the username of the account to load
     * @return the loaded User object
     */
    public static User loadAccount(String username) {
        String fileName = username + ".sav";
        File file = new File(fileName);

        if (!file.exists()) {
            System.out.println("Error: No User Found.\n");
            return null;
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            User loadedAccount = (User) ois.readObject();
            
            if (!loadedAccount.getUsername().equals(username)) {
                System.out.println("Error: No User Found.\n");
                return null;
            }
            
            return loadedAccount;
        } 
        catch (IOException | ClassNotFoundException e) {
            System.out.println("Error Loading User: " + e.getMessage());
            return null;
        }
    }

    /**
     * Checks whether an account file exists for the specified username.
     *
     * @param username the username to check
     * @return true if the account exists and false if not found
     */
    public static boolean accountExists(String username) {
        String fileName = username + ".sav";
        File file = new File(fileName);
        return file.exists();
    }
}
