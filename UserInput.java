import java.util.Scanner;

/**
 * Handles user authentication by login or registration.
 *
 * Relationships:
 * - Uses a Scanner object to receive user input.
 * - Interacts with SaveData to load, save, and validate user accounts.
 * - Creates and returns User objects upon successful authentication.
 * - Used by Main to authenticate users.
 */
public class UserInput {

    /**
     * Prompts the user to either log in to an existing account or
     * create a new account until authentication is successful.
     *
     * @param scanner the Scanner used to receive user input
     * @return the authenticated User object
     */
    public User handleAuthentication(Scanner scanner) {
        User activeAccount = null;

        while (activeAccount == null) {
            System.out.println("[1] Login to Existing Account");
            System.out.println("[2] Create New Account");
            System.out.print(">> ");
            
            int loginChoice = InputChecker.getValidInput(scanner, 1, 2);
        
            if (loginChoice == 1) {
                activeAccount = handleLogin(scanner);
            } else if (loginChoice == 2) {
                activeAccount = handleRegistration(scanner);
            }
        }
        return activeAccount;
    }

    /**
     * Authenticates a user by verifying the entered username and password.
     *
     * @param scanner the Scanner used to receive user input
     * @return the authenticated User object
     */
    private User handleLogin(Scanner scanner) {
        System.out.print("Enter Username: ");
        String username = scanner.nextLine();
        User loadedProfile = SaveData.loadAccount(username);

        if (loadedProfile == null) {
            return null;
        }

        System.out.print("Enter Password: ");
        String firstTryPassword = scanner.nextLine();
        
        if (loadedProfile.getPassword().equals(firstTryPassword)) {
            System.out.println("\nWelcome Back " + username + "!");
            return loadedProfile;
        }
        
        return handleWrongPassword(scanner, loadedProfile, username);
    }

    /**
     * Handles incorrect password attempts by allowing the user to retry,
     * reset the password, or return to the authentication menu.
     *
     * @param scanner the Scanner used to receive user input
     * @param loadedProfile the user account being authenticated
     * @param username the username of the account
     * @return the authenticated User object
     */
    private User handleWrongPassword(Scanner scanner, User loadedProfile, String username) {
        boolean checkingPassword = true;
        System.out.println("\nError: Incorrect password.");

        while (checkingPassword) {
            System.out.println("[1] Try Password Again");
            System.out.println("[2] Forgot Password");
            System.out.println("[3] Go Back");
            System.out.print(">> ");
            int pwChoice = InputChecker.getValidInput(scanner, 1, 3);

            switch (pwChoice) {
                case 1:
                    System.out.print("Enter Password: ");
                    String loopPassword = scanner.nextLine();
                    if (loadedProfile.getPassword().equals(loopPassword)) { 
                        System.out.println("\nWelcome Back " + username + "!");
                        return loadedProfile;
                    } else {
                        System.out.println("Error: Incorrect password.\n");
                    }
                    break;
                case 2:
                    System.out.println("\n[RESET PASSWORD]");
                    System.out.print("Enter Your New Password: ");
                    String newPassword = scanner.nextLine();
                    
                    loadedProfile.setPassword(newPassword);
                    SaveData.saveAccount(loadedProfile);
                    
                    System.out.println("Password Reset Successfully!");
                    System.out.println("Returning to Login...\n");
                    checkingPassword = false;
                    break;
                case 3:
                    System.out.println("Returning to Login...\n");
                    checkingPassword = false;
                    break;
            }
        }
        return null;
    }

    /**
     * Registers a new user account after verifying that the chosen username
     * is not already in use.
     *
     * @param scanner the Scanner used to receive user input
     * @return the newl User object
     */
    private User handleRegistration(Scanner scanner) {
        String newUsername = "";
        String newPassword = "";
        boolean choosingUsername = true;

        while (choosingUsername) {
            System.out.print("Create New Username: ");
            newUsername = scanner.nextLine();

            if (SaveData.accountExists(newUsername)) {
                System.out.println("Error: Username '" + newUsername + "' Already Taken.\n");
            } else {
                choosingUsername = false;
                System.out.print("Create New Password: ");
                newPassword = scanner.nextLine();
            }
        }
        
        User newAccount = new User(newUsername, newPassword);
        System.out.println("User '" + newUsername + "' Created Successfully!");
        SaveData.saveAccount(newAccount);
        return newAccount;
    }
}
