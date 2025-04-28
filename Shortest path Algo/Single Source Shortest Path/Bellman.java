import java.util.*;

public class Bellman {

    static class Edge {
        int to;
        int weight;

        Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }

    }

    static class Graph {
        int v;
        List<List<Edge>> adj = new ArrayList<>();
        int e;

        Graph(int v) {
            this.v = v;

            adj = new ArrayList<>();
            for (int i = 0; i < v; i++) {
                adj.add(new ArrayList<>());
            }
        }

        public void addedge(int from, int to, int weight) {
            adj.get(from).add(new Edge(to, weight));
            // bellman ford is for directed graph only

        }

        public void result(int s) {
            int[] dist = new int[v];
            for (int i = 0; i < v; i++) {
               
                dist[i] = Integer.MAX_VALUE;
            }
            dist[s]=0;

            // relax all edges v-1 times

            for (int i = 1; i < v; i++) {
                for (int u = 0; u < v; u++) {
                    for (Edge edge : adj.get(u)) {

                        int to = edge.to;
                        int weight = edge.weight;
                        if (dist[u] != Integer.MAX_VALUE && dist[u] + weight < dist[to]) {
                            dist[to] = dist[u] + weight;
                        }
                    }
                }
            }

            // Print distances
            System.out.println("Vertex distances from source:");
            for (int i = 0; i < v; i++) {
                if (dist[i] == Integer.MAX_VALUE) {
                    System.out.println("Vertex " + i + ": INF");
                } else {
                    System.out.println("Vertex " + i + ": " + dist[i]);
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of vertices: ");
        int v = sc.nextInt();

        Graph g = new Graph(v);

        System.out.print("Enter number of edges: ");
        int E = sc.nextInt();

        System.out.println("Enter each edge (from to weight):");
        for (int i = 0; i < E; i++) {
            int from = sc.nextInt();
            int to = sc.nextInt();
            int weight = sc.nextInt();
            g.addedge(from, to, weight);
        }

        System.out.print("Enter source vertex: ");
        int source = sc.nextInt();

        g.result(source);

        sc.close();
    }

}
