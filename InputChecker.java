import java.util.Scanner;

public class InputChecker {
    public static int getValidInput(Scanner scanner, int min, int max) {
        int num = 0;
        boolean valid = false;

        do {
            try {
                num = Integer.parseInt(scanner.nextLine());

                if (num < min || num > max) {
                    System.out.println("Error: Please enter a number between " + min + " and " + max + ".");
                    System.out.print(">> ");
                } else {
                    valid = true;
                }
            }
            catch (NumberFormatException e) {
                System.out.println("Error: Please enter a valid integer.");
            }
        } while (!valid);

        return num;
    }
}
