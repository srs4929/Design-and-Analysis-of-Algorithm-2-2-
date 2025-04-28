import java.util.*;

public class Kruskal {

    static class Edge {
        int u;
        int v;
        int w;

        Edge(int u, int v, int w) {
            this.u = u;
            this.v = v;
            this.w = w;
        }

    }

    static class Graph {

        int v;
        List<Edge> edges;

        Graph(int v) {
            this.v = v;
            this.edges = new ArrayList<>();
        }

        public void addedge(int u, int v, int w) {
            edges.add(new Edge(u, v, w));
        }
    }

    static class Subset {
        int parent;
        int rank;
    }

    // disjoint set
    static int find(Subset subset[], int i) {

        if (subset[i].parent != i) {
            subset[i].parent = find(subset, subset[i].parent);
        }
        return subset[i].parent;
    }
    // union

    static void union(Subset subset[], int x, int y) {
        int xroot = find(subset, x);
        int yroot = find(subset, y);
        if (subset[xroot].rank < subset[yroot].rank) {
            subset[xroot].parent = yroot;
        } else if (subset[yroot].rank < subset[xroot].rank) {
            subset[yroot].parent = xroot;
        } else {
            subset[yroot].parent = xroot;
            subset[xroot].rank++;
        }
    }

    // Kruskal
    static void result(Graph graph) {
        List<Edge> result = new ArrayList<>();
        // sort edges
        Collections.sort(graph.edges, (a, b) -> a.w - b.w);
        // create v subset

        Subset[] subset = new Subset[graph.v];
        int total = 0;
        for (int i = 0; i < graph.v; i++) {
            subset[i] = new Subset();
            subset[i].parent = i;
            subset[i].rank = 0;
        }

        // process each edge

        for (Edge edge : graph.edges) {
            int x = find(subset, edge.u); // startng vertex of the edge
            int y = find(subset, edge.v); // ending vertex of the edge
            if (x != y) // does not form cycle
            {
                result.add(edge);
                union(subset, x, y);
                total += edge.w;
            }
        }
        // total spanning

        System.out.println("Total weight of mst " + total);

        // output the minimum spanning

        for (Edge edge : result) {
            System.out.println(edge.u + " " + edge.v + " " + edge.w);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the number of vertices: ");
        int V = sc.nextInt();

        Graph graph = new Graph(V);

        System.out.print("Enter the number of edges: ");
        int E = sc.nextInt();

        System.out.println("Enter the edges (u v weight): ");
        for (int i = 0; i < E; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            int w = sc.nextInt();
            graph.addedge(u, v, w);
        }

        result(graph);

        sc.close();
    }

}
