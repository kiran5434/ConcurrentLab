import java.util.*;

public class ResourceAllocationGraphSimulator {

    static Map<String, List<String>> graph = new HashMap<>();

    // Add node
    static void addNode(String node) {
        graph.putIfAbsent(node, new ArrayList<>());
    }

    // Add edge
    static void addEdge(String from, String to) {
        graph.putIfAbsent(from, new ArrayList<>());
        graph.get(from).add(to);
    }

    // Display Graph
    static void displayGraph() {
        System.out.println("\n----- Resource Allocation Graph -----");
        for (String node : graph.keySet()) {
            System.out.print(node + " --> ");
            List<String> edges = graph.get(node);
            if (edges.isEmpty())
                System.out.print("NULL");
            else
                for (String edge : edges)
                    System.out.print(edge + " ");
            System.out.println();
        }
    }

    // DFS for cycle detection
    static boolean dfs(String node, Set<String> visited, Set<String> recStack) {

        if (recStack.contains(node))
            return true;

        if (visited.contains(node))
            return false;

        visited.add(node);
        recStack.add(node);

        for (String neighbor : graph.getOrDefault(node, new ArrayList<>())) {
            if (dfs(neighbor, visited, recStack))
                return true;
        }

        recStack.remove(node);
        return false;
    }

    // Deadlock Detection
    static void detectDeadlock() {

        Set<String> visited = new HashSet<>();
        Set<String> recStack = new HashSet<>();

        for (String node : graph.keySet()) {
            if (dfs(node, visited, recStack)) {
                System.out.println("\nDeadlock Detected (Cycle Exists)");
                return;
            }
        }

        System.out.println("\nNo Deadlock Detected");
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        while (true) {

            System.out.println("\n===== Resource Allocation Graph Simulator =====");
            System.out.println("1. Add Process");
            System.out.println("2. Add Resource");
            System.out.println("3. Add Request Edge (Process -> Resource)");
            System.out.println("4. Add Allocation Edge (Resource -> Process)");
            System.out.println("5. Display Graph");
            System.out.println("6. Detect Deadlock");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");

            int choice = sc.nextInt();

            switch (choice) {

                case 1:
                    System.out.print("Enter Process Name (P1, P2...): ");
                    addNode(sc.next());
                    break;

                case 2:
                    System.out.print("Enter Resource Name (R1, R2...): ");
                    addNode(sc.next());
                    break;

                case 3:
                    System.out.print("Enter Process: ");
                    String process = sc.next();

                    System.out.print("Enter Resource: ");
                    String resource = sc.next();

                    addEdge(process, resource);
                    break;

                case 4:
                    System.out.print("Enter Resource: ");
                    resource = sc.next();

                    System.out.print("Enter Process: ");
                    process = sc.next();

                    addEdge(resource, process);
                    break;

                case 5:
                    displayGraph();
                    break;

                case 6:
                    detectDeadlock();
                    break;

                case 7:
                    System.out.println("Program Terminated.");
                    sc.close();
                    System.exit(0);

                default:
                    System.out.println("Invalid Choice!");
            }
        }
    }
}
