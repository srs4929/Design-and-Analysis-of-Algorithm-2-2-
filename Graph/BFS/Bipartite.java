//Bipartite graph -  Color the graph with two colors such that no adjacent node
//have the same color
//checks for all the nodes
import java.util.*;

class Graph {
    private int v;
    private List<List<Integer>> adj;

    Graph(int v) {
        this.v = v;
        adj = new ArrayList<>();
        for (int i = 0; i <= v; i++) {
            adj.add(new ArrayList<>());
        }
    }

    public void addedge(int u, int v) {
        adj.get(u).add(v);
        adj.get(v).add(u);
    }

    public boolean BFS(int s, int[] color) {

        color[s] = 0; // make the source color 0
        Queue<Integer> queue = new LinkedList<>();
        queue.add(s);
        while (!queue.isEmpty()) {
            int node = queue.poll();
            for (int neighbour : adj.get(node)) {
                if (color[neighbour] == -1) { // If uncolored
                    color[neighbour] = 1 - color[node]; // Assign opposite color . Works as !color[node]
                    queue.add(neighbour); // Add to queue for further BFS traversal
                } else if (color[neighbour] == color[node]) {
                    return false;
                }
            }
        }
        return true; // if no conflicts , bipartite
    }

    public boolean isBipartite() // for checking disconnected also
    {
        int[] color = new int[v + 1]; // initially color all the vertex with -1
        for (int i = 0; i <= v; i++) {
            color[i] = -1;
        }
        for (int i = 0; i <= v; i++) {
            if (color[i] == -1) // unvisited component
            {
                if (!BFS(i, color)) {
                    return false;
                }
            }
        }
        return true;
    }

}

public class Bipartite {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int v = sc.nextInt();
        Graph g = new Graph(v);
        System.out.print("Enter number of edges: ");
        int e = sc.nextInt();

        System.out.println("Enter edges ");
        for (int i = 0; i < e; i++) {
            int u = sc.nextInt();
            int w = sc.nextInt();
            g.addedge(u, w);

        }
        
        if (g.isBipartite()) {
            System.out.println("Bipartite");
        } else
            System.out.println("Not Bipartite");

    }

}