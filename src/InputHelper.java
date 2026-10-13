import java.util.Scanner;

/**
 * Shared input helper. Every menu and prompt in the program uses this class,
 * so invalid input (letters, blanks, out-of-range numbers) never crashes the program.
 */
public class InputHelper {

    private static final Scanner SCANNER = new Scanner(System.in);

    /** Reads a line; exits cleanly if the input stream is closed. */
    private static String readLine(String prompt) {
        System.out.print(prompt);
        if (!SCANNER.hasNextLine()) {
            System.out.println("\nInput closed. Exiting program.");
            System.exit(0);
        }
        return SCANNER.nextLine().trim();
    }

    /** Keeps asking until the user types a valid whole number. */
    public static int readInt(String prompt) {
        while (true) {
            String line = readLine(prompt);
            try {
                return Integer.parseInt(line);
            } catch (NumberFormatException e) {
                System.out.println("  Invalid input. Please enter a whole number.");
            }
        }
    }

    /** Keeps asking until the number is between min and max (inclusive). */
    public static int readIntInRange(String prompt, int min, int max) {
        while (true) {
            int value = readInt(prompt);
            if (value >= min && value <= max) {
                return value;
            }
            System.out.println("  Please enter a number between " + min + " and " + max + ".");
        }
    }

    /** Keeps asking until the user types some non-blank text. */
    public static String readNonEmptyString(String prompt) {
        while (true) {
            String line = readLine(prompt);
            if (!line.isEmpty()) {
                return line;
            }
            System.out.println("  Input cannot be empty.");
        }
    }
}
