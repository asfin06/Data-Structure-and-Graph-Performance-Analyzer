/** Queue submenu: enqueue, dequeue, peek/front and display, with empty/full handling. */
public class QueueOperations {

    private static final int CAPACITY = 10;
    private final ArrayQueue<Integer> queue = new ArrayQueue<>(CAPACITY);

    public void menu() {
        while (true) {
            System.out.println("\n--------------- QUEUE OPERATIONS ------------");
            System.out.println("1. Enqueue");
            System.out.println("2. Dequeue");
            System.out.println("3. Peek / Front");
            System.out.println("4. Display Queue");
            System.out.println("5. Return to Main Menu");
            int choice = InputHelper.readIntInRange("Enter your choice: ", 1, 5);

            switch (choice) {
                case 1: {
                    int value = InputHelper.readInt("Enter value to enqueue: ");
                    if (queue.enqueue(value)) {
                        System.out.println("Enqueued " + value + ".");
                    } else {
                        System.out.println("Queue overflow: the queue is full (capacity " + CAPACITY + ").");
                    }
                    break;
                }
                case 2:
                    if (queue.isEmpty()) {
                        System.out.println("Queue underflow: cannot dequeue from an empty queue.");
                    } else {
                        System.out.println("Dequeued " + queue.dequeue() + ".");
                    }
                    break;
                case 3:
                    if (queue.isEmpty()) {
                        System.out.println("Queue is empty. Nothing at the front.");
                    } else {
                        System.out.println("Front element is " + queue.peek() + ".");
                    }
                    break;
                case 4:
                    queue.display();
                    break;
                default:
                    return;
            }
        }
    }
}
