import java.util.ArrayList;
import java.util.List;

/**
 * Stores a text record of every search, traversal and performance result
 * so that menu option 8 ("Display All Results") can print them later.
 */
public class ResultLog {

    private static final List<String> RESULTS = new ArrayList<>();

    public static void add(String result) {
        RESULTS.add(result);
    }

    public static void display() {
        System.out.println("=============================================");
        System.out.println("ALL RESULTS SO FAR");
        System.out.println("=============================================");
        if (RESULTS.isEmpty()) {
            System.out.println("No results recorded yet. Run a search, traversal or performance comparison first.");
        } else {
            for (int i = 0; i < RESULTS.size(); i++) {
                System.out.println((i + 1) + ". " + RESULTS.get(i));
            }
        }
        System.out.println("=============================================");
    }
}
