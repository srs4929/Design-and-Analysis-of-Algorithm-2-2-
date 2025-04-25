import java.util.*;

public class Prims {
    static class Graph {
        int v;
        int[][] adj;

        Graph(int v) {
            this.v = v;
            adj = new int[v][v];
        }

        public void addedge(int x, int y, int w) {
            adj[x][y] = w; // weight add
            adj[y][x] = w;
        }

        // find the min key node
        public int minkey(int[] key, boolean[] mst) {
            int mini = Integer.MAX_VALUE;
            int minindex = -1;
            for (int i = 0; i < v; i++) {
                if (!mst[i] && key[i] < mini) // if the node is not visited in mst and has small key it is the node
                {
                    minindex = i;
                    mini = key[i];
                }
            }
            return minindex;
        }

        public void primist(int s) {
            int[] parent = new int[v];
            boolean[] mst = new boolean[v];
            int[] key = new int[v];
            // initialize key with infinity
            int x = Integer.MAX_VALUE;
            for (int i = 0; i < v; i++) {
                key[i] = x;
            }
            // initialize all the parent with -1
            for (int i = 0; i < v; i++) {
                parent[i] = -1;
            }
            parent[s] = -1;
            key[s] = 0;
            for (int i = 0; i < v; i++) {
                int u = minkey(key, mst); // 1) go to key array find the minimum
                mst[u] = true;

                // update the min node adjacency node
                for (int j = 0; j < v; j++) {
                    if (adj[u][j] != 0 && !mst[j] && adj[u][j] < key[j]) {
                        parent[j] = u;
                        key[j] = adj[u][j];
                    }
                }
            }

            int total = 0;
            System.out.println("Edge and weight");
            for (int i = 1; i < v; i++) // source is avoiding
            {

                System.out.println(parent[i] + 1 + " - " + (i + 1) + " weight" + adj[i][parent[i]]);
                total += adj[i][parent[i]];

            }
            System.out.println("Total Weight");

        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the number of vertices: ");
        int vertices = sc.nextInt();

        Graph g = new Graph(vertices);

        System.out.print("Enter the number of edges: ");
        int edges = sc.nextInt();

        for (int i = 0; i < edges; i++) {
            System.out.print("Enter edge (x, y) and weight: ");
            int x = sc.nextInt();
            int y = sc.nextInt();
            int w = sc.nextInt();
            g.addedge(x - 1, y - 1, w); // -1 for 0-based indexing
        }

        System.out.print("Enter the starting vertex: ");
        int start = sc.nextInt();

        g.primist(start - 1); // -1 for 0-based indexing
        sc.close();
    }
}
