import java.util.*;

class Graph

{
    public int v;
    public List<List<Integer>> arr;

    Graph(int v) {
        this.v = v;
        arr = new ArrayList<>(v + 1);
        for (int i = 0; i <= v; i++) {
            arr.add(new ArrayList<>());
        }
    }

    public void addedge(int u, int v) {
        arr.get(u).add(v);
        arr.get(v).add(u);
    }

    public void dfsvisit(int start, boolean[] visit) {
        visit[start] = true;
        System.out.println("Visited " + start);

        for (int neighbour : arr.get(start)) {
            if (!visit[neighbour]) {

                dfsvisit(neighbour, visit);
            }
        }
    }

}

public class DFS {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number of vertex");
        int v = sc.nextInt();
        System.out.println("Enter the number of edges");
        int e = sc.nextInt();
        Graph g = new Graph(v);
        System.out.println("Enter the edges");
        for (int i = 0; i < e; i++) {
            int u = sc.nextInt();
            int w = sc.nextInt();
            g.addedge(u, w);

        }
        System.out.println("Enter the start node");
        int s = sc.nextInt();
        boolean[] visit = new boolean[v + 1];
        g.dfsvisit(s, visit);

    }

}
