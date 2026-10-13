import java.util.Arrays;

/**
 * Performance comparison: runs the algorithms on the same data and prints
 * steps (operation counts) and execution time side by side.
 */
public class PerformanceAnalyzer {

    private static void row(String operation, String algorithm, long steps, long nanos, String complexity) {
        System.out.printf("%-18s %-22s %12d %14d  %-10s%n", operation, algorithm, steps, nanos, complexity);
        ResultLog.add("Performance: " + operation + " / " + algorithm + " -> steps=" + steps
                + ", time=" + nanos + " ns (" + complexity + ")");
    }

    public static void run(Graph userGraph) {
        int n = InputHelper.readIntInRange("Enter dataset size for the comparison (10 to 1000000): ", 10, 1000000);

        // Sorted data: 0, 2, 4, ... so binary search is valid. Target = last element (worst case for linear).
        int[] data = new int[n];
        for (int i = 0; i < n; i++) {
            data[i] = i * 2;
        }
        int target = data[n - 1];
        SearchOperations.Result linear = SearchOperations.linearSearch(data, target);
        SearchOperations.Result binary = SearchOperations.binarySearch(data, target);

        // Graph: use the user's graph if it has at least 2 vertices, otherwise the sample graph.
        Graph graph = userGraph;
        String graphNote = "your graph";
        if (userGraph.vertexCount() < 2) {
            graph = new Graph();
            graph.loadSample();
            graphNote = "the built-in sample graph (A-H)";
        }
        String start = graph.firstVertex();
        Graph.TraversalResult bfs = graph.bfs(start);
        Graph.TraversalResult dfs = graph.dfs(start);

        // Insert at the front: array must shift every element; linked list just relinks the head.
        int[] arr = new int[n + 1];
        System.arraycopy(data, 0, arr, 0, n);
        long arrayStart = System.nanoTime();
        long arraySteps = 0;
        for (int i = n; i > 0; i--) {
            arr[i] = arr[i - 1];
            arraySteps++;
        }
        arr[0] = -1;
        arraySteps++;
        long arrayNanos = System.nanoTime() - arrayStart;

        LinkedListOperations list = new LinkedListOperations();
        for (int i = 0; i < n; i++) {
            list.insertAtBeginning(i); // building the list is not timed
        }
        long listStart = System.nanoTime();
        list.insertAtBeginning(-1);
        long listNanos = System.nanoTime() - listStart;

        System.out.println("=============================================");
        System.out.println("PERFORMANCE COMPARISON");
        System.out.println("=============================================");
        System.out.println("Search: n = " + n + ", target = last element (worst case for linear search).");
        System.out.println("Graph: traversals run on " + graphNote + ", starting at '" + start + "'.");
        System.out.println("Graph steps = vertices visited + edges examined.");
        System.out.println();
        System.out.printf("%-18s %-22s %12s %14s  %-10s%n", "Operation", "Algorithm", "Steps", "Time (ns)", "Complexity");
        System.out.println("---------------------------------------------------------------------------------");
        row("Search", "Linear Search", linear.steps, linear.nanos, "O(n)");
        row("Search", "Binary Search", binary.steps, binary.nanos, "O(log n)");
        row("Graph Traversal", "BFS", bfs.steps, bfs.nanos, "O(V+E)");
        row("Graph Traversal", "DFS", dfs.steps, dfs.nanos, "O(V+E)");
        row("Insert at front", "Array (shift all)", arraySteps, arrayNanos, "O(n)");
        row("Insert at front", "Linked List (head)", 1, listNanos, "O(1)");
        System.out.println("=============================================");
        System.out.println("Why the results differ:");
        System.out.println("- Linear search checks elements one by one (O(n)); binary search halves the range each step (O(log n)).");
        System.out.println("- BFS and DFS both visit every vertex and edge once, so their step counts are similar (O(V+E));");
        System.out.println("  they differ in visit ORDER (queue = level by level, recursion/stack = deep first).");
        System.out.println("- Inserting at the front of an array shifts n elements; a linked list only changes the head pointer.");
        System.out.println("- Times in nanoseconds vary between runs (JVM warm-up), so steps are the reliable measure.");
    }
}
