import java.util.*;

class Graph {
    public int v;
    public List<List<Integer>> arr;
    public int[] d;// discovery time
    public int[] f; // finishing time
    public int time;
    public String[] color;

    Graph(int v) {
        this.v = v;
        arr = new ArrayList<>(v + 1);
        for (int i = 0; i <= v; i++) {
            arr.add(new ArrayList<>());
        }
        d = new int[v + 1];
        f = new int[v + 1];
        color = new String[v + 1];
        time = 0;
        for (int i = 0; i <= v; i++) // Initially all vertices white
        {
            color[i] = "White";
        }
    }

    public void addedge(int u, int v) {
        arr.get(u).add(v);
        arr.get(v).add(u);
    }

    public void dfs(int s) {
        if (!color[s].equals("White"))
            return; // if already visited -(base case)
        d[s] = time++;
        color[s] = "Grey"; // mark as beign explored
        System.out.println("Visited vertex " + s + " First discovery time " + d[s]);
        for (int neigh : arr.get(s)) {
            if (color[neigh].equals("White")) // if not visited yet
            {
                dfs(neigh);
            }
        }
        f[s] = time++;
        color[s] = "Black"; // mark as fully explored
        System.out.println("Finished vertex " + s + " Finished time " + f[s]);

    }

}

public class DFStime {
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the number of vertices:");
        int v = sc.nextInt();
        Graph g = new Graph(v);

        System.out.println("Enter the number of edges:");
        int e = sc.nextInt();

        System.out.println("Enter the edges:");
        for (int i = 0; i < e; i++) {
            int u = sc.nextInt();
            int w = sc.nextInt();
            g.addedge(u, w);
        }

        System.out.println("Enter the start node:");
        int startNode = sc.nextInt();
        g.dfs(startNode);
    }
}