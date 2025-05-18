import java.util.*;

public class Secondmst {

    static class Edge {
        int u, v, w;

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
            edges = new ArrayList<>();
        }

        void addEdge(int u, int v, int w) {
            edges.add(new Edge(u, v, w));
        }
    }

    static class Subset {
        int parent, rank;
    }

    static int find(Subset[] subsets, int i) {
        if (subsets[i].parent != i) {
            subsets[i].parent = find(subsets, subsets[i].parent);
        }
        return subsets[i].parent;
    }

    static void union(Subset[] subsets, int x, int y) {
        int xroot = find(subsets, x);
        int yroot = find(subsets, y);

        if (subsets[xroot].rank < subsets[yroot].rank) {
            subsets[xroot].parent = yroot;
        } else if (subsets[yroot].rank < subsets[xroot].rank) {
            subsets[yroot].parent = xroot;
        } else {
            subsets[yroot].parent = xroot;
            subsets[xroot].rank++;
        }
    }

   
    // skipping one edge (for second-best MST)
    static int kruskalMST(Graph graph, Integer skipEdgeIndex, List<Integer> mstEdgesIndices) {
        Collections.sort(graph.edges, Comparator.comparingInt(e -> e.w));

        Subset[] subsets = new Subset[graph.v];
        for (int i = 0; i < graph.v; i++) {
            subsets[i] = new Subset();
            subsets[i].parent = i;
            subsets[i].rank = 0;
        }

        int mstWeight = 0, edgesUsed = 0;

        for (int i = 0; i < graph.edges.size(); i++) {
            if (skipEdgeIndex != null && i == skipEdgeIndex) continue;

            Edge edge = graph.edges.get(i);

            int x = find(subsets, edge.u);
            int y = find(subsets, edge.v);

            if (x != y) {
                union(subsets, x, y);
                mstWeight += edge.w;
                edgesUsed++;

                if (mstEdgesIndices != null) {
                    mstEdgesIndices.add(i); // track edges used in MST
                }

                if (edgesUsed == graph.v - 1) break;
            }
        }

        if (edgesUsed != graph.v - 1) return -1; // MST not possible

        return mstWeight;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of vertices: ");
        int V = sc.nextInt();

        Graph graph = new Graph(V);

        System.out.print("Enter number of edges: ");
        int E = sc.nextInt();

        System.out.println("Enter edges (u v weight): ");
        for (int i = 0; i < E; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            int w = sc.nextInt();
            graph.addEdge(u, v, w);
        }

        // 1) Find best MST and remember which edges were used
        List<Integer> bestMSTEdges = new ArrayList<>();
        int bestMSTWeight = kruskalMST(graph, null, bestMSTEdges);
       

        System.out.println("Best MST weight: " + bestMSTWeight);
        System.out.println("Edges in best MST:");
        for (int idx : bestMSTEdges) {
            Edge e = graph.edges.get(idx);
            System.out.println(e.u + " - " + e.v + " : " + e.w);
        }

        // 2) Try removing each MST edge to find second best MST
        int secondBest = Integer.MAX_VALUE;

        for (int skipIdx : bestMSTEdges) {
            int weight = kruskalMST(graph, skipIdx, null);
            if (weight != -1 && weight > bestMSTWeight && weight < secondBest) {
                secondBest = weight;
            }
        }

        if (secondBest == Integer.MAX_VALUE) {
            System.out.println("No second best MST found");
        } else {
            System.out.println("Second best MST weight: " + secondBest);
        }

        sc.close();
    }
}

