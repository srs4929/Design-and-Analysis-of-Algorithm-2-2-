import java.util.*;
import java.io.*;

class Graph {
    public int v;
    public List<List<Integer>> adj;
    public int e;
    String[] colour;
    int[] f;
    int[] l;
    int time;
    Stack<Integer> p;

    Graph(String file) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String[] filename = reader.readLine().split(" ");
        this.v = Integer.parseInt(filename[0]);
        this.e = Integer.parseInt(filename[1]);
        adj = new ArrayList<>();
        for (int i = 0; i <= v; i++) {
            adj.add(new ArrayList<>());
        }

        for (int i = 0; i < e; i++) {
            String[] edge = reader.readLine().split(" ");
            int u = Integer.parseInt(edge[0]);
            int v = Integer.parseInt(edge[1]);
            adj.get(u).add(v);
        }
        this.f = new int[v + 1];
        this.l = new int[v + 1];
        time = 0;
        this.colour = new String[v + 1];
        for (int i = 0; i <= v; i++) {
            colour[i] = "White";
        }
        this.p = new Stack<>();

    }

    public void addvertex(int n) {
        for (int i = 0; i <= n; i++) {
            adj.add(new ArrayList<>());
            v += n;
        }
    }

    public void addedge(int u, int v) {
        adj.get(u).add(v);
    }

    public int vertices() {
        return v;
    }

    public List<Integer> getadj(int vertex) {
        return adj.get(vertex);
    }

    public void displayGraph() {
        for (int i = 0; i <= v; i++) {
            System.out.print(i + "->");
            for (int neigh : adj.get(i)) {
                System.out.print(neigh + " ");
            }
            System.out.println();
        }
    }

    public void dfslex(int s) {
        if (!colour[s].equals("White"))
            return;
        colour[s] = "Grey";
        System.out.print(s + " ");
        time++;
        f[s] = time;
        List<Integer> arr = new ArrayList<>(adj.get(s));
        arr.sort(Comparator.naturalOrder());
        for (int neigh : arr) {
            if (colour[neigh].equals("White"))
                dfslex(neigh);
        }

        colour[s] = "Black";
        time++;
        l[s] = time;

    }

}

public class Bon_B_07 {
    public static void main(String[] args) throws IOException {
        Graph g = new Graph("input.txt");
        System.out.println("DFS in lexagraphical order");
        g.dfslex(5);

    }
}