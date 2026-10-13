/**
 * Circular array queue (FIFO) with a fixed capacity. Written from scratch (no java.util.Queue).
 * The circular design means enqueue and dequeue never shift elements, so both are O(1).
 */
public class ArrayQueue<T> {

    private final Object[] items;
    private int front = 0;
    private int size = 0;

    public ArrayQueue(int capacity) {
        items = new Object[Math.max(capacity, 1)];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == items.length;
    }

    public int size() {
        return size;
    }

    public int capacity() {
        return items.length;
    }

    /** Adds to the rear. Returns false if the queue is full. */
    public boolean enqueue(T item) {
        if (isFull()) {
            return false;
        }
        int rear = (front + size) % items.length; // wraps around the array
        items[rear] = item;
        size++;
        return true;
    }

    /** Removes from the front. Caller must check isEmpty() first. */
    @SuppressWarnings("unchecked")
    public T dequeue() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty");
        }
        T item = (T) items[front];
        items[front] = null;
        front = (front + 1) % items.length;
        size--;
        return item;
    }

    /** Returns the front item without removing it. */
    @SuppressWarnings("unchecked")
    public T peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty");
        }
        return (T) items[front];
    }

    /** Prints the queue from front to rear. */
    public void display() {
        if (isEmpty()) {
            System.out.println("Queue is empty.");
            return;
        }
        StringBuilder sb = new StringBuilder("Queue (front -> rear): ");
        for (int i = 0; i < size; i++) {
            sb.append(items[(front + i) % items.length]);
            if (i < size - 1) {
                sb.append(" <- ");
            }
        }
        System.out.println(sb);
    }
}
