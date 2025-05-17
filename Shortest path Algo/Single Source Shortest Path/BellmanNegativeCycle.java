import java.util.*;
import java.io.*;

public class BellmanNegativeCycle {

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
        int e;
        List<List<Edge>> adj;

        Graph(int v, int e) {
            this.v = v;
            this.adj = new ArrayList<>();
            this.e = e;
            for (int i = 0; i < v; i++)
                adj.add(new ArrayList<>());
        }

        public void addedge(int u, int v, int w) {
            adj.get(u).add(new Edge(v, w));
        }

        public boolean result(int s) {
            int[] dist = new int[v];
            int[] p = new int[v];
            Arrays.fill(dist, Integer.MAX_VALUE);
            dist[s] = 0;
            for (int i = 0; i < v - 1; i++) {
                for (int u = 0; u < v; u++) {
                    for (Edge edge : adj.get(u)) {
                        int v = edge.to;
                        int w = edge.weight;
                        if (dist[u] != Integer.MAX_VALUE) {
                            dist[v] = Math.min(dist[v], dist[u] + w);
                            p[v] = u;
                        }
                    }
                }
            }
            // check for negative weight cycle but relaxing again
            for (int i = 0; i < v; i++) {
                for (Edge edge : adj.get(i)) {
                    int v = edge.to;
                    int w = edge.weight;
                    if (dist[i] != Integer.MAX_VALUE && dist[v] > w + dist[i])
                    return true;

                }
            }
            return false;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        PrintWriter out = new PrintWriter(System.out);
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        Graph g = new Graph(n, m);
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            g.addedge(a - 1, b - 1, c);
        }
        if (g.result(0))
            System.out.println("Yes");
        else
            System.out.println("No");

    }

}
