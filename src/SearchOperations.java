import java.util.Arrays;
import java.util.Random;

/**
 * Searching component: linear search O(n) and binary search O(log n).
 * Both count comparisons ("steps") and measure execution time so they can be compared.
 */
public class SearchOperations {

    /** Holds the outcome of one search. */
    public static class Result {
        public final int index;
        public final long steps;
        public final long nanos;

        Result(int index, long steps, long nanos) {
            this.index = index;
            this.steps = steps;
            this.nanos = nanos;
        }
    }

    /** Linear search: checks every element in order until found. Works on unsorted data. */
    public static Result linearSearch(int[] array, int target) {
        long start = System.nanoTime();
        long steps = 0;
        int found = -1;
        for (int i = 0; i < array.length; i++) {
            steps++;
            if (array[i] == target) {
                found = i;
                break;
            }
        }
        return new Result(found, steps, System.nanoTime() - start);
    }

    /** Binary search: halves the search range each step. The array MUST be sorted. */
    public static Result binarySearch(int[] sorted, int target) {
        long start = System.nanoTime();
        long steps = 0;
        int low = 0;
        int high = sorted.length - 1;
        int found = -1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            steps++;
            if (sorted[mid] == target) {
                found = mid;
                break;
            } else if (sorted[mid] < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return new Result(found, steps, System.nanoTime() - start);
    }

    private static String describe(Result r) {
        return (r.index == -1 ? "NOT FOUND" : "found at index " + r.index)
                + ", steps=" + r.steps + ", time=" + r.nanos + " ns";
    }

    /** Runs both searches on the same data and prints a side-by-side comparison. */
    public static void compare(int[] original, int[] sorted, int target, String label) {
        Result linear = linearSearch(original, target);
        Result binary = binarySearch(sorted, target);

        System.out.println("\n" + label + " (n = " + original.length + ", target = " + target + ")");
        System.out.printf("%-16s %-28s %8s %12s%n", "Algorithm", "Result", "Steps", "Time (ns)");
        System.out.printf("%-16s %-28s %8d %12d%n", "Linear Search",
                linear.index == -1 ? "NOT FOUND" : "found at index " + linear.index, linear.steps, linear.nanos);
        System.out.printf("%-16s %-28s %8d %12d%n", "Binary Search",
                binary.index == -1 ? "NOT FOUND" : "found at index " + binary.index, binary.steps, binary.nanos);
        System.out.println("Binary search needed " + (binary.steps) + " steps vs " + linear.steps
                + " for linear: O(log n) grows much slower than O(n).");

        ResultLog.add("Search comparison on " + label + " (n=" + original.length + ", target=" + target
                + "): Linear steps=" + linear.steps + ", Binary steps=" + binary.steps);
    }

    public void menu(ArrayOperations arrayOps) {
        while (true) {
            System.out.println("\n-------------- SEARCHING OPERATIONS ---------");
            System.out.println("1. Linear Search (on the Array component's data)");
            System.out.println("2. Binary Search (sorts a copy of the Array data first)");
            System.out.println("3. Compare Linear vs Binary (on the Array component's data)");
            System.out.println("4. Compare Linear vs Binary (on a large generated dataset)");
            System.out.println("5. Return to Main Menu");
            int choice = InputHelper.readIntInRange("Enter your choice: ", 1, 5);

            switch (choice) {
                case 1: {
                    int[] data = arrayOps.toArray();
                    if (data.length == 0) {
                        System.out.println("Array is empty. Add data in Array Operations first.");
                        break;
                    }
                    int target = InputHelper.readInt("Enter value to search: ");
                    Result r = linearSearch(data, target);
                    System.out.println("Linear search: " + describe(r));
                    ResultLog.add("Linear search for " + target + " in array: " + describe(r));
                    break;
                }
                case 2: {
                    int[] data = arrayOps.toArray();
                    if (data.length == 0) {
                        System.out.println("Array is empty. Add data in Array Operations first.");
                        break;
                    }
                    int[] sorted = data.clone();
                    Arrays.sort(sorted);
                    System.out.println("Sorted copy: " + Arrays.toString(sorted));
                    int target = InputHelper.readInt("Enter value to search: ");
                    Result r = binarySearch(sorted, target);
                    System.out.println("Binary search (on sorted copy): " + describe(r));
                    ResultLog.add("Binary search for " + target + " in sorted array: " + describe(r));
                    break;
                }
                case 3: {
                    int[] data = arrayOps.toArray();
                    if (data.length == 0) {
                        System.out.println("Array is empty. Add data in Array Operations first.");
                        break;
                    }
                    int[] sorted = data.clone();
                    Arrays.sort(sorted);
                    int target = InputHelper.readInt("Enter value to search: ");
                    compare(data, sorted, target, "Array data");
                    break;
                }
                case 4: {
                    int n = InputHelper.readIntInRange("Dataset size (10 to 1000000): ", 10, 1000000);
                    int[] data = new int[n];
                    Random random = new Random();
                    for (int i = 0; i < n; i++) {
                        data[i] = random.nextInt(n * 2);
                    }
                    int[] sorted = data.clone();
                    Arrays.sort(sorted);
                    int target = InputHelper.readInt("Enter value to search: ");
                    compare(data, sorted, target, "Generated dataset");
                    break;
                }
                default:
                    return;
            }
        }
    }
}
