import java.util.*;

class Graph {
    int v;
    List<List<Integer>> adj;
    List<List<Integer>> transpose;

    Graph(int v) {
        this.v = v;
        adj = new ArrayList<>();
        transpose = new ArrayList<>();
        for (int i = 0; i < v; i++) {
            adj.add(new ArrayList<>());
            transpose.add(new ArrayList<>());
        }
    }

    void addedge(int u, int v) {
        adj.get(u).add(v);
    }

    // Step 1: DFS to fill the stack by finishing time
    void dfs1(int u, boolean[] visited, Stack<Integer> s) {
        visited[u] = true;
        for (int v : adj.get(u)) {
            if (!visited[v]) {
                dfs1(v, visited, s);
            }
        }
        s.push(u);
    }

    // Step 2: Transpose the graph
    void buildTranspose() {
        for (int u = 0; u < v; u++) {
            for (int x : adj.get(u)) {
                transpose.get(x).add(u);
            }
        }
    }

    // Step 3: DFS on the transposed graph
    void dfs2(int u, boolean[] visited, List<Integer> component) {
        visited[u] = true;
        component.add(u);
        for (int v : transpose.get(u)) {
            if (!visited[v]) {
                dfs2(v, visited, component);
            }
        }
    }

    // Step 4: Main function to print SCCs
    void printSCC() {
        Stack<Integer> s = new Stack<>();
        boolean[] visited = new boolean[v];

        // Step 1: Fill stack with finishing times
        for (int i = 0; i < v; i++) {
            if (!visited[i]) {
                dfs1(i, visited, s);
            }
        }

        // Step 2: Build transpose
        buildTranspose();

        // Step 3: DFS on transpose in the order of the stack
        for(int i=0;i<v;i++) // Reset visited for 2nd DFS
        {
           visited[i]=false;
        } 
        System.out.println("Strongly Connected Components:");
        while (!s.isEmpty()) {
            int u = s.pop();
            if (!visited[u]) {
                List<Integer> component = new ArrayList<>();
                dfs2(u, visited, component);
                System.out.println(component);
            }
        }
    }
}

public class SCC {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter number of vertices:");
        int v = sc.nextInt();
        Graph g = new Graph(v);

        System.out.println("Enter number of edges:");
        int e = sc.nextInt();
        System.out.println("Enter the edges (from to):");
        for (int i = 0; i < e; i++) {
            int u = sc.nextInt();
            int w = sc.nextInt();
            g.addedge(u, w);
        }

        g.printSCC();
    }
}

