/**
 * Singly linked list of integers. Inserting at the head is O(1); inserting at the tail,
 * deleting by value and searching are O(n) because the list must be walked node by node.
 */
public class LinkedListOperations {

    /** One node: a value and a reference to the next node. */
    private static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
        }
    }

    private Node head = null;
    private int size = 0;

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return head == null;
    }

    /** Insert at the head. O(1). */
    public void insertAtBeginning(int value) {
        Node node = new Node(value);
        node.next = head;
        head = node;
        size++;
    }

    /** Insert at the tail. O(n) because we walk to the last node. */
    public void insertAtEnd(int value) {
        Node node = new Node(value);
        if (head == null) {
            head = node;
        } else {
            Node current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = node;
        }
        size++;
    }

    /** Deletes the first node holding the value. Returns false if not found. O(n). */
    public boolean deleteByValue(int value) {
        if (head == null) {
            return false;
        }
        if (head.data == value) {
            head = head.next;
            size--;
            return true;
        }
        Node current = head;
        while (current.next != null && current.next.data != value) {
            current = current.next;
        }
        if (current.next == null) {
            return false;
        }
        current.next = current.next.next; // unlink the node
        size--;
        return true;
    }

    /** Returns the 1-based position of the value, or -1 if not found. O(n). */
    public int search(int value) {
        Node current = head;
        int position = 1;
        while (current != null) {
            if (current.data == value) {
                return position;
            }
            current = current.next;
            position++;
        }
        return -1;
    }

    public void display() {
        if (head == null) {
            System.out.println("Linked list is empty.");
            return;
        }
        StringBuilder sb = new StringBuilder("Linked List: ");
        Node current = head;
        while (current != null) {
            sb.append(current.data).append(" -> ");
            current = current.next;
        }
        sb.append("null");
        System.out.println(sb + "  (size " + size + ")");
    }

    public void menu() {
        while (true) {
            System.out.println("\n----------- LINKED LIST OPERATIONS ----------");
            System.out.println("1. Insert at Beginning");
            System.out.println("2. Insert at End");
            System.out.println("3. Delete by Value");
            System.out.println("4. Search for a Value");
            System.out.println("5. Display List");
            System.out.println("6. Return to Main Menu");
            int choice = InputHelper.readIntInRange("Enter your choice: ", 1, 6);

            switch (choice) {
                case 1: {
                    int value = InputHelper.readInt("Enter value to insert: ");
                    insertAtBeginning(value);
                    System.out.println("Inserted " + value + " at the beginning.");
                    break;
                }
                case 2: {
                    int value = InputHelper.readInt("Enter value to insert: ");
                    insertAtEnd(value);
                    System.out.println("Inserted " + value + " at the end.");
                    break;
                }
                case 3: {
                    if (isEmpty()) {
                        System.out.println("List is empty. Nothing to delete.");
                        break;
                    }
                    int value = InputHelper.readInt("Enter value to delete: ");
                    if (deleteByValue(value)) {
                        System.out.println("Deleted " + value + ".");
                    } else {
                        System.out.println("Value " + value + " not found.");
                    }
                    break;
                }
                case 4: {
                    if (isEmpty()) {
                        System.out.println("List is empty. Nothing to search.");
                        break;
                    }
                    int value = InputHelper.readInt("Enter value to search: ");
                    int position = search(value);
                    if (position == -1) {
                        System.out.println("Value " + value + " not found.");
                    } else {
                        System.out.println("Value " + value + " found at position " + position + " (1-based).");
                    }
                    break;
                }
                case 5:
                    display();
                    break;
                default:
                    return;
            }
        }
    }
}
