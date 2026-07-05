import java.io.*;

public class SaveData {
    public static void saveAccount(User user) {
        String fileName = user.getUsername() + ".sav";

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(user);
        } catch (IOException e) {
            System.out.println("Error saving user: " + e.getMessage());
        }
    }

    public static User loadAccount(String username) {
        String fileName = username + ".sav";
        File file = new File(fileName);

        if (!file.exists()) {
            System.out.println("No user found");
            return null;
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            User loadedAccount = (User) ois.readObject();
            System.out.println();
            System.out.println("Welcome back " + loadedAccount.getUsername() + "!");
            return loadedAccount;
        } 
        catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading user: " + e.getMessage());
            return null;
        }
    }
}