/*There are n cities and m roads between them. Unfortunately, the condition of the roads is so poor that they cannot be used. Your task is to repair some of the roads so that there will be a decent route between any two cities.
For each road, you know its reparation cost, and you should find a solution where the total cost is as small as possible.*/

import java.io.*;
import java.util.*;

public class Road {

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
        List<List<Edge>> adj;

        Graph(int v) {
            this.v = v;
            adj = new ArrayList<>();
            for (int i = 0; i < v; i++) {
                adj.add(new ArrayList<>());
            }
        }

        public void addEdge(int u, int v, int w) {
            adj.get(u).add(new Edge(u, v, w));
            adj.get(v).add(new Edge(v, u, w));
        }

        public void prim(int start, PrintWriter out) {
            boolean[] mst = new boolean[v];
            int[] key = new int[v];
            Arrays.fill(key, Integer.MAX_VALUE);
            key[start] = 0;

            PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
            pq.add(new int[]{start, 0});
            long total = 0;
            int count = 0;

            while (!pq.isEmpty()) {
                int[] curr = pq.poll();
                int u = curr[0];
                if (mst[u]) continue;
                mst[u] = true;
                total += curr[1];
                count++;

                for (Edge e : adj.get(u)) {
                    if (!mst[e.v] && e.w < key[e.v]) {
                        key[e.v] = e.w;
                        pq.add(new int[]{e.v, e.w});
                    }
                }
            }

            if (count != v)
                out.println("IMPOSSIBLE");
            else
                out.println(total);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        String[] first = br.readLine().split(" ");
        int n = Integer.parseInt(first[0]);
        int m = Integer.parseInt(first[1]);

        Graph g = new Graph(n);
        for (int i = 0; i < m; i++) {
            String[] parts = br.readLine().split(" ");
            int u = Integer.parseInt(parts[0]) - 1;
            int v = Integer.parseInt(parts[1]) - 1;
            int w = Integer.parseInt(parts[2]);
            g.addEdge(u, v, w);
        }

        g.prim(0, out);
        out.flush();
    }
}
