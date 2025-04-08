import java.util.*;
import java.io.*;

class Graph {

    public int v;
    public int e;
    public List<List<Integer>> adj;
    String[] color;

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
        this.color = new String[v + 1];
        for (int i = 0; i <= v; i++) {
            color[i] = "White";
        }

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

    public void fpath(int s, int d, List<Integer> path, List<List<Integer>> allpath) {
        color[s] = "Grey";
        path.add(s);
        if (s == d) {
            allpath.add(new ArrayList<>(path));
        } else {
            for (int neigh : adj.get(s)) {
                if (color[neigh].equals("White")) {
                    fpath(neigh, d, path, allpath);
                }
            }
        }
        color[s] = "Black";
        path.remove(path.size() - 1); // backtracking remove
    }

    public List<List<Integer>> apath(int s, int d) {
        List<Integer> path = new ArrayList<>();
        List<List<Integer>> allpath = new ArrayList<>();
        fpath(s, d, path, allpath);
        return allpath;
    }

}

public class Bon_A_07
{
    public static void main(String[] args) throws IOException
    {
        Graph g=new Graph("input.txt");
        List<List<Integer>>allpath=g.apath(5,1);
        System.out.println("All paths are");
        for(List<Integer>path:allpath)
        {
            System.out.println(path);
        }

    }
}