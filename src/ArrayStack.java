/**
 * Array-based stack (LIFO) with a fixed capacity. Written from scratch (no java.util.Stack).
 * push, pop and peek are all O(1).
 */
public class ArrayStack<T> {

    private final Object[] items;
    private int top = -1;

    public ArrayStack(int capacity) {
        items = new Object[capacity];
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public boolean isFull() {
        return top == items.length - 1;
    }

    public int size() {
        return top + 1;
    }

    public int capacity() {
        return items.length;
    }

    /** Pushes an item. Returns false on overflow (stack full). */
    public boolean push(T item) {
        if (isFull()) {
            return false;
        }
        items[++top] = item;
        return true;
    }

    /** Pops the top item. Caller must check isEmpty() first. */
    @SuppressWarnings("unchecked")
    public T pop() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }
        T item = (T) items[top];
        items[top--] = null;
        return item;
    }

    /** Returns the top item without removing it. */
    @SuppressWarnings("unchecked")
    public T peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }
        return (T) items[top];
    }

    /** Prints the stack from top to bottom. */
    public void display() {
        if (isEmpty()) {
            System.out.println("Stack is empty.");
            return;
        }
        System.out.println("Stack (top -> bottom):");
        for (int i = top; i >= 0; i--) {
            System.out.println("  | " + items[i] + " |" + (i == top ? "  <- top" : ""));
        }
    }
}
