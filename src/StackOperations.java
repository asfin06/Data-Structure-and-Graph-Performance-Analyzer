/** Stack submenu: push, pop, peek and display, with empty/full handling. */
public class StackOperations {

    private static final int CAPACITY = 10;
    private final ArrayStack<Integer> stack = new ArrayStack<>(CAPACITY);

    public void menu() {
        while (true) {
            System.out.println("\n--------------- STACK OPERATIONS ------------");
            System.out.println("1. Push");
            System.out.println("2. Pop");
            System.out.println("3. Peek");
            System.out.println("4. Display Stack");
            System.out.println("5. Return to Main Menu");
            int choice = InputHelper.readIntInRange("Enter your choice: ", 1, 5);

            switch (choice) {
                case 1: {
                    int value = InputHelper.readInt("Enter value to push: ");
                    if (stack.push(value)) {
                        System.out.println("Pushed " + value + ".");
                    } else {
                        System.out.println("Stack overflow: the stack is full (capacity " + CAPACITY + ").");
                    }
                    break;
                }
                case 2:
                    if (stack.isEmpty()) {
                        System.out.println("Stack underflow: cannot pop from an empty stack.");
                    } else {
                        System.out.println("Popped " + stack.pop() + ".");
                    }
                    break;
                case 3:
                    if (stack.isEmpty()) {
                        System.out.println("Stack is empty. Nothing to peek.");
                    } else {
                        System.out.println("Top element is " + stack.peek() + ".");
                    }
                    break;
                case 4:
                    stack.display();
                    break;
                default:
                    return;
            }
        }
    }
}
