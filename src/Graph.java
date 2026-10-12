import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Undirected graph stored as an adjacency list (vertex name -> list of neighbours).
 * Memory is O(V + E). BFS and DFS both run in O(V + E).
 * BFS uses our own ArrayQueue; DFS uses recursion (the call stack).
 */
public class Graph {

    /** Outcome of a traversal: visit order, step count and time. */
    public static class TraversalResult {
        public final List<String> order = new ArrayList<>();
        public long steps = 0;   // vertices visited + edges examined
        public long nanos = 0;
    }

    private final Map<String, List<String>> adjacency = new LinkedHashMap<>();

    public int vertexCount() {
        return adjacency.size();
    }

    public int edgeCount() {
        int total = 0;
        for (List<String> neighbours : adjacency.values()) {
            total += neighbours.size();
        }
        return total / 2; // each undirected edge is stored twice
    }

    public boolean hasVertex(String name) {
        return adjacency.containsKey(name);
    }

    public String firstVertex() {
        return adjacency.keySet().iterator().next();
    }

    /** Adds a vertex. Returns false if it already exists. */
    public boolean addVertex(String name) {
        if (adjacency.containsKey(name)) {
            return false;
        }
        adjacency.put(name, new ArrayList<>());
        return true;
    }

    /** Adds an undirected edge. Throws IllegalArgumentException with a readable message if invalid. */
    public void addEdge(String a, String b) {
        if (!adjacency.containsKey(a) || !adjacency.containsKey(b)) {
            throw new IllegalArgumentException("Both vertices must exist before adding an edge.");
        }
        if (a.equals(b)) {
            throw new IllegalArgumentException("Self-loops are not allowed.");
        }
        if (adjacency.get(a).contains(b)) {
            throw new IllegalArgumentException("Edge " + a + " - " + b + " already exists.");
        }
        adjacency.get(a).add(b);
        adjacency.get(b).add(a);
    }

    public void display() {
        if (adjacency.isEmpty()) {
            System.out.println("Graph is empty.");
            return;
        }
        System.out.println("Graph (" + vertexCount() + " vertices, " + edgeCount() + " edges) - adjacency list:");
        for (Map.Entry<String, List<String>> entry : adjacency.entrySet()) {
            System.out.println("  " + entry.getKey() + " -> " + entry.getValue());
        }
    }

    /** Breadth-first traversal: visits vertices level by level using a queue. */
    public TraversalResult bfs(String start) {
        TraversalResult result = new TraversalResult();
        long begin = System.nanoTime();
        Set<String> visited = new HashSet<>();
        ArrayQueue<String> queue = new ArrayQueue<>(adjacency.size()); // each vertex is enqueued at most once
        visited.add(start);
        queue.enqueue(start);
        while (!queue.isEmpty()) {
            String current = queue.dequeue();
            result.order.add(current);
            result.steps++;
            for (String neighbour : adjacency.get(current)) {
                result.steps++;
                if (visited.add(neighbour)) {
                    queue.enqueue(neighbour);
                }
            }
        }
        result.nanos = System.nanoTime() - begin;
        return result;
    }

    /** Depth-first traversal: goes as deep as possible before backtracking (recursive). */
    public TraversalResult dfs(String start) {
        TraversalResult result = new TraversalResult();
        long begin = System.nanoTime();
        dfsVisit(start, new HashSet<>(), result);
        result.nanos = System.nanoTime() - begin;
        return result;
    }

    private void dfsVisit(String current, Set<String> visited, TraversalResult result) {
        visited.add(current);
        result.order.add(current);
        result.steps++;
        for (String neighbour : adjacency.get(current)) {
            result.steps++;
            if (!visited.contains(neighbour)) {
                dfsVisit(neighbour, visited, result);
            }
        }
    }

    /** Replaces the graph with a small sample graph (A-H) for quick demos. */
    public void loadSample() {
        adjacency.clear();
        for (String v : new String[]{"A", "B", "C", "D", "E", "F", "G", "H"}) {
            addVertex(v);
        }
        String[][] edges = {{"A", "B"}, {"A", "C"}, {"B", "D"}, {"B", "E"},
                {"C", "F"}, {"C", "G"}, {"E", "H"}, {"F", "H"}};
        for (String[] e : edges) {
            addEdge(e[0], e[1]);
        }
    }

    private void printTraversal(String name, String start, TraversalResult r) {
        System.out.println(name + " from " + start + ": " + String.join(" -> ", r.order));
        System.out.println("Vertices visited: " + r.order.size() + ", steps: " + r.steps + ", time: " + r.nanos + " ns");
        ResultLog.add(name + " from " + start + ": " + String.join(" -> ", r.order)
                + " (steps=" + r.steps + ", time=" + r.nanos + " ns)");
    }

    /** Asks for a start vertex; returns null if the graph is empty or the vertex does not exist. */
    private String askStartVertex() {
        if (adjacency.isEmpty()) {
            System.out.println("Graph is empty. Add vertices first (or load the sample graph).");
            return null;
        }
        String start = InputHelper.readNonEmptyString("Enter start vertex: ");
        if (!hasVertex(start)) {
            System.out.println("Vertex '" + start + "' does not exist.");
            return null;
        }
        return start;
    }

    public void menu() {
        while (true) {
            System.out.println("\n--------------- GRAPH OPERATIONS ------------");
            System.out.println("1. Add Vertex");
            System.out.println("2. Add Edge");
            System.out.println("3. Display Graph");
            System.out.println("4. BFS Traversal");
            System.out.println("5. DFS Traversal");
            System.out.println("6. Search for a Vertex (reachable from a start vertex)");
            System.out.println("7. Load Sample Graph");
            System.out.println("8. Return to Main Menu");
            int choice = InputHelper.readIntInRange("Enter your choice: ", 1, 8);

            switch (choice) {
                case 1: {
                    String name = InputHelper.readNonEmptyString("Enter vertex name: ");
                    if (addVertex(name)) {
                        System.out.println("Vertex '" + name + "' added.");
                    } else {
                        System.out.println("Vertex '" + name + "' already exists.");
                    }
                    break;
                }
                case 2: {
                    String a = InputHelper.readNonEmptyString("Enter first vertex: ");
                    String b = InputHelper.readNonEmptyString("Enter second vertex: ");
                    try {
                        addEdge(a, b);
                        System.out.println("Edge " + a + " - " + b + " added.");
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                }
                case 3:
                    display();
                    break;
                case 4: {
                    String start = askStartVertex();
                    if (start != null) {
                        printTraversal("BFS", start, bfs(start));
                    }
                    break;
                }
                case 5: {
                    String start = askStartVertex();
                    if (start != null) {
                        printTraversal("DFS", start, dfs(start));
                    }
                    break;
                }
                case 6: {
                    String start = askStartVertex();
                    if (start != null) {
                        String target = InputHelper.readNonEmptyString("Enter vertex to search for: ");
                        TraversalResult r = bfs(start);
                        boolean found = r.order.contains(target);
                        String msg = "Search for '" + target + "' from '" + start + "' using BFS: "
                                + (found ? "FOUND (reachable)" : "NOT reachable") + ", steps=" + r.steps;
                        System.out.println(msg);
                        ResultLog.add(msg);
                    }
                    break;
                }
                case 7:
                    loadSample();
                    System.out.println("Sample graph loaded (vertices A-H).");
                    display();
                    break;
                default:
                    return;
            }
        }
    }
}
