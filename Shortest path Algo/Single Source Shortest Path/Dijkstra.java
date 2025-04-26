import java.util.*;

public class Dijkstra {

    static class Edge {

        int to; // where we are going
        int weight;

        Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }

    static class Graph {
        public int v;
        public int e;
        public List<List<Edge>> adj = new ArrayList<>();

        Graph(int v) {
            this.v = v;
            this.e = e;
            adj = new ArrayList<>();
            for (int i = 0; i < v; i++) {
                adj.add(new ArrayList<>());
            }

        }

        public void addedge(int from, int to, int weight) {
            adj.get(from).add(new Edge(to, weight));
            adj.get(to).add(new Edge(from, weight));
        }

        public void result(int s) {
            int[] dist = new int[v];
            for (int i = 0; i < v; i++) {
                dist[i] = Integer.MAX_VALUE;
            }
            dist[s] = 0;
            PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparing(a -> a[1]));// 2d pq.Sort on based on
                                                                                           // distance-a[1]
            pq.add(new int[] { s, 0 }); // source distance 0 push

            while (!pq.isEmpty()) {
                int[] curr = pq.poll();
                int n = curr[0];// node
                int w = curr[1]; // weight
                if (w > dist[n])
                    continue; // already better distance
                for (Edge edge : adj.get(n)) {
                    int v = edge.to;
                    int wt = edge.weight;

                    if (dist[n] + wt < dist[v]) {
                        dist[v] = dist[n] + wt;
                        pq.add(new int[] { v, dist[v] });
                    }
                }

            }

            for (int i = 0; i < v; i++) {
                if (dist[i] == Integer.MAX_VALUE) {
                    System.out.println("Distance from source " + s + " to " + i + " INF");
                } else {
                    System.out.println("Distance from source " + s + " to " + i + dist[i]);
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
        System.out.println("Enter edges (format: from to weight):");
        for (int i = 0; i < E; i++) {
            int from = sc.nextInt();
            int to = sc.nextInt();
            int weight = sc.nextInt();
            g.addedge(from, to, weight);

        }
        System.out.print("Enter source vertex: ");
        int source = sc.nextInt();
        g.result(source);
    }

}
