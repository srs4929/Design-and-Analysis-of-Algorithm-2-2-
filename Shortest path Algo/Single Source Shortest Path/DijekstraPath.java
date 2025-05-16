import java.util.*;
import java.io.*;

public class DijekstraPath {

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
        List<List<Edge>> adj;

        Graph(int v) {
            this.v = v;
            this.adj = new ArrayList<>();
            for (int i = 0; i < v; i++) {
                adj.add(new ArrayList<>());
            }
        }

        public void addedge(int u, int v, int w) {
            adj.get(u).add(new Edge(v, w));
            adj.get(v).add(new Edge(u, w));
        }

        public void result(int s) {
            int[] dist = new int[v];
            Arrays.fill(dist, Integer.MAX_VALUE);
            dist[s] = 0;
            PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparing(a -> a[1]));
            pq.add(new int[] { s, 0 });
            int[] p = new int[v];
            Arrays.fill(p, -1);
            while (!pq.isEmpty()) {
                int[] node = pq.poll();
                int u = node[0];
                int w = node[1];
                if (w > dist[u])
                    continue;
                for (Edge edge : adj.get(u)) {
                    int to = edge.to;
                    int wt = edge.weight;
                    if (dist[u] + wt < dist[to]) {
                        dist[to] = dist[u] + wt;
                        p[to] = u;
                        pq.add(new int[] { to, dist[to] });
                    }
                }
            }
            // Path Construction
            List<Integer> Path = new ArrayList<>();
            if (dist[v - 1] == Integer.MAX_VALUE) {
                System.out.println(-1);
                return;
            }
            for (int i = v - 1; i != -1; i = p[i]) {
                Path.add(i + 1);
            }
            Collections.reverse(Path);
            for (int node : Path) {
                System.out.print(node + " ");
            }
            System.out.println();

        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(reader.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        Graph g = new Graph(n);
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(reader.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            g.addedge(u - 1, v - 1, w);
        }
        g.result(0);
    }

}

/*You are given a weighted undirected graph. The vertices are enumerated from 1 to n. Your task is to find the shortest path between the vertex 1 and the vertex n.

Input
The first line contains two integers n and m (2 ≤ n ≤ 105, 0 ≤ m ≤ 105), where n is the number of vertices and m is the number of edges. Following m lines contain one edge each in form ai, bi and wi (1 ≤ ai, bi ≤ n, 1 ≤ wi ≤ 106), where ai, bi are edge endpoints and wi is the length of the edge.

It is possible that the graph has loops and multiple edges between pair of vertices.

Output
Write the only integer -1 in case of no path. Write the shortest path in opposite case. If there are many solutions, print any of them.
5 6
1 2 2
2 5 5
2 3 4
1 4 1
4 3 3
3 5 1
Output
1 4 3 5 */
