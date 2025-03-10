import java.util.*;

class Graph {
    public int v;
    public List<List<Integer>> adj;
    // Constructor

    Graph(int v) {
        this.v = v;
        adj = new ArrayList<>(v);
        // initialize
        for (int i = 0; i < v; i++) {
            adj.add(new ArrayList<>());
        }
    }

    // method to add edge

    public void addedge(int u, int w) {
        adj.get(u).add(w);
        adj.get(w).add(u);
    }

    public void print() {
        for (int i = 0; i < v; i++) {
            System.out.println(i + "->");
            for (int neighbour : adj.get(i)) {
                System.out.print(neighbour+" ");
            }
          System.out.println();
        }
    }
}

public class AdjacencyList {
    public static void main(String[] args) {

        Graph g=new Graph(3);
        g.addedge(0,1);
        g.addedge(1,2);
        g.print();



    }
}