import java.util.Arrays;
import java.util.Random;

/**
 * Array component: a fixed-capacity integer array with insert, delete, search and display.
 * Insert/delete in the middle shift elements, so they cost O(n); access by index is O(1).
 */
public class ArrayOperations {

    private static final int CAPACITY = 50;
    private final int[] data = new int[CAPACITY];
    private int size = 0;

    public int size() {
        return size;
    }

    /** Returns a copy of the used part of the array (used by the searching component). */
    public int[] toArray() {
        return Arrays.copyOf(data, size);
    }

    /** Inserts at the end. O(1). Returns false if the array is full. */
    public boolean insertAtEnd(int value) {
        if (size == CAPACITY) {
            return false;
        }
        data[size++] = value;
        return true;
    }

    /** Inserts at a position (0..size), shifting later elements right. O(n). */
    public boolean insertAt(int index, int value) {
        if (size == CAPACITY || index < 0 || index > size) {
            return false;
        }
        for (int i = size; i > index; i--) {
            data[i] = data[i - 1];
        }
        data[index] = value;
        size++;
        return true;
    }

    /** Deletes the element at an index, shifting later elements left. O(n). */
    public boolean deleteAt(int index) {
        if (index < 0 || index >= size) {
            return false;
        }
        for (int i = index; i < size - 1; i++) {
            data[i] = data[i + 1];
        }
        size--;
        return true;
    }

    /** Linear search. Returns the index of the first match or -1. O(n). */
    public int search(int value) {
        for (int i = 0; i < size; i++) {
            if (data[i] == value) {
                return i;
            }
        }
        return -1;
    }

    public void display() {
        if (size == 0) {
            System.out.println("Array is empty.");
            return;
        }
        System.out.println("Array (" + size + "/" + CAPACITY + "): " + Arrays.toString(toArray()));
    }

    /** Replaces the contents with random numbers (1-100). */
    public void fillRandom(int count) {
        Random random = new Random();
        size = 0;
        for (int i = 0; i < count; i++) {
            data[size++] = random.nextInt(100) + 1;
        }
    }

    public void menu() {
        while (true) {
            System.out.println("\n--------------- ARRAY OPERATIONS ------------");
            System.out.println("1. Insert at End");
            System.out.println("2. Insert at Position");
            System.out.println("3. Delete by Position");
            System.out.println("4. Delete by Value");
            System.out.println("5. Search for a Value");
            System.out.println("6. Display Array");
            System.out.println("7. Fill with Random Numbers");
            System.out.println("8. Return to Main Menu");
            int choice = InputHelper.readIntInRange("Enter your choice: ", 1, 8);

            switch (choice) {
                case 1: {
                    int value = InputHelper.readInt("Enter value to insert: ");
                    if (insertAtEnd(value)) {
                        System.out.println("Inserted " + value + ".");
                    } else {
                        System.out.println("Array is full (capacity " + CAPACITY + "). Cannot insert.");
                    }
                    break;
                }
                case 2: {
                    int index = InputHelper.readIntInRange("Enter position (0 to " + size + "): ", 0, size);
                    int value = InputHelper.readInt("Enter value to insert: ");
                    if (insertAt(index, value)) {
                        System.out.println("Inserted " + value + " at position " + index + ".");
                    } else {
                        System.out.println("Array is full (capacity " + CAPACITY + "). Cannot insert.");
                    }
                    break;
                }
                case 3: {
                    if (size == 0) {
                        System.out.println("Array is empty. Nothing to delete.");
                        break;
                    }
                    int index = InputHelper.readIntInRange("Enter position to delete (0 to " + (size - 1) + "): ", 0, size - 1);
                    deleteAt(index);
                    System.out.println("Deleted element at position " + index + ".");
                    break;
                }
                case 4: {
                    if (size == 0) {
                        System.out.println("Array is empty. Nothing to delete.");
                        break;
                    }
                    int value = InputHelper.readInt("Enter value to delete: ");
                    int index = search(value);
                    if (index == -1) {
                        System.out.println("Value " + value + " not found.");
                    } else {
                        deleteAt(index);
                        System.out.println("Deleted " + value + " (was at position " + index + ").");
                    }
                    break;
                }
                case 5: {
                    if (size == 0) {
                        System.out.println("Array is empty. Nothing to search.");
                        break;
                    }
                    int value = InputHelper.readInt("Enter value to search: ");
                    int index = search(value);
                    if (index == -1) {
                        System.out.println("Value " + value + " not found.");
                    } else {
                        System.out.println("Value " + value + " found at position " + index + ".");
                    }
                    break;
                }
                case 6:
                    display();
                    break;
                case 7: {
                    int count = InputHelper.readIntInRange("How many numbers (1 to " + CAPACITY + ")? ", 1, CAPACITY);
                    fillRandom(count);
                    System.out.println("Array filled with " + count + " random numbers.");
                    display();
                    break;
                }
                default:
                    return;
            }
        }
    }
}
