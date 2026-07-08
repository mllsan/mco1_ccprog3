import java.util.Scanner;

/**
 * Provides utility methods for validating user input.
 *
 * Relationships:
 * - Independent utility class.
 * - Used by Main and MediaInput to ensure only valid
 *   numerical menu choices are accepted.
 */
public class InputChecker {

    /**
     * Prompts the user until a valid integer within the specified range is entered.
     *
     * @param scanner the Scanner used to read user input
     * @param min the minimum accepted value (inclusive)
     * @param max the maximum accepted value (inclusive)
     * @return a valid integer within the specified range
     */
    public static int getValidInput(Scanner scanner, int min, int max) {
        int num = 0;
        boolean valid = false;

        do {
            try {
                num = Integer.parseInt(scanner.nextLine());

                // Makes sure the entered value is within the accepted range.
                if (num < min || num > max) {
                    System.out.println("Error: Please enter a number between " + min + " and " + max + ".");
                    System.out.print(">> ");
                } else {
                    valid = true;
                }
            }
            catch (NumberFormatException e) {
                // Handles inputs that are not valid integers.
                System.out.println("Error: Please enter a valid integer.");
                System.out.print(">> ");
            }
        } while (!valid);

        return num;
    }
}
