import java.io.*;

public class SaveData {
    public static void saveAccount(User user) {
        String fileName = user.getUsername() + ".sav";

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(user);
        } catch (IOException e) {
            System.out.println("Error Saving User: " + e.getMessage());
        }
    }

    public static User loadAccount(String username) {
        String fileName = username + ".sav";
        File file = new File(fileName);

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            User loadedAccount = (User) ois.readObject();
            if (!loadedAccount.getUsername().equals(username)) {
                System.out.println("Error: No User Found.");
                System.out.println();
            }
            else {
                System.out.println();
                System.out.println("Welcome Back " + loadedAccount.getUsername() + "!");
                return loadedAccount;
            }
            return null;
        } 
        catch (IOException | ClassNotFoundException e) {
            System.out.println("Error Loading User: " + e.getMessage());
            return null;
        }
    }

    public static boolean accountExists(String username) {
        String fileName = username + ".sav";
        File file = new File(fileName);
        return file.exists();
    }
}
