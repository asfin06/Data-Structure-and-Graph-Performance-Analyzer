/**
 * Main console application: shows the main menu and connects every component.
 * CIT300 Graded Practical Assignment 2 - Data Structure and Graph Performance Analyzer.
 */
public class Main {

    public static void main(String[] args) {
        // One shared object per component, so data persists while the program runs.
        ArrayOperations arrayOps = new ArrayOperations();
        StackOperations stackOps = new StackOperations();
        QueueOperations queueOps = new QueueOperations();
        LinkedListOperations listOps = new LinkedListOperations();
        SearchOperations searchOps = new SearchOperations();
        Graph graph = new Graph();

        while (true) {
            System.out.println("\n=============================================");
            System.out.println("DATA STRUCTURE & GRAPH ANALYZER");
            System.out.println("=============================================");
            System.out.println("1. Array Operations");
            System.out.println("2. Stack Operations");
            System.out.println("3. Queue Operations");
            System.out.println("4. Linked List Operations");
            System.out.println("5. Searching Operations");
            System.out.println("6. Graph Operations");
            System.out.println("7. Performance Comparison");
            System.out.println("8. Display All Results");
            System.out.println("9. Exit");
            int choice = InputHelper.readIntInRange("Enter your choice: ", 1, 9);

            switch (choice) {
                case 1:
                    arrayOps.menu();
                    break;
                case 2:
                    stackOps.menu();
                    break;
                case 3:
                    queueOps.menu();
                    break;
                case 4:
                    listOps.menu();
                    break;
                case 5:
                    searchOps.menu(arrayOps);
                    break;
                case 6:
                    graph.menu();
                    break;
                case 7:
                    PerformanceAnalyzer.run(graph);
                    break;
                case 8:
                    ResultLog.display();
                    break;
                default:
                    System.out.println("Thank you for using the Data Structure & Graph Analyzer. Goodbye!");
                    return;
            }
        }
    }
}
