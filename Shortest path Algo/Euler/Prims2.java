import java.util.*;

public class Main {

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
        List<Edge> edge;
        int v;

        Graph(int v) {
            this.v = v;
            this.edge = new ArrayList<>();
        }

        public void addedge(int u, int v, int w) {
            edge.add(new Edge(u, v, w));
        }

        void printmst(int s) {
            int[] p = new int[v];
            int[] key = new int[v];
            boolean[] mst = new boolean[v];
            PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparing(e -> e.w));

            Arrays.fill(key, Integer.MAX_VALUE);
            Arrays.fill(p, -1);

            key[s] = 0;
            pq.add(new Edge(-1, s, 0));

            while (!pq.isEmpty()) {
                Edge cur = pq.poll();
                int u = cur.v;

                if (mst[u]) continue;

                mst[u] = true;

                for (Edge e : this.edge) {   // loop over all edges
                    if (e.u == u && !mst[e.v] && e.w < key[e.v]) {
                        key[e.v] = e.w;
                        p[e.v] = u;
                        pq.add(new Edge(u, e.v, e.w));
                    }
                    if (e.v == u && !mst[e.u] && e.w < key[e.u]) {
                        key[e.u] = e.w;
                        p[e.u] = u;
                        pq.add(new Edge(u, e.u, e.w));
                    }
                }
            }

            int total = 0;
            for (int i = 0; i < v; i++) {
                if (p[i] != -1) {
                    System.out.println(p[i] + " - " + i);
                    total += key[i];
                }
            }
            System.out.println(total);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int V = sc.nextInt();
        int E = sc.nextInt();
        Graph graph = new Graph(V);

        for (int i = 0; i < E; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            int weight = sc.nextInt();
            graph.addedge(u, v, weight);
        }

        int start = sc.nextInt();
        graph.printmst(start);
    }
}
