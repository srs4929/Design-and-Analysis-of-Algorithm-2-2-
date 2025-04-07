import java.util.*;

class Graph {
    int v;
    List<List<Integer>> adj;
    boolean[] visit;

    Graph(int v) {
        this.v = v;
        adj = new ArrayList<>();
        for (int i = 0; i < v; i++) {
            adj.add(new ArrayList<>());

        }
        visit = new boolean[v];
    }

    public void addedge(int u, int v) {
        adj.get(u).add(v);
    }

    public boolean dfs(int s, Stack<Integer> a, boolean[] recstack) {

        visit[s] = true;
        recstack[s] = true;

        for (int neigh : adj.get(s)) {
            if (!visit[neigh]) {
                if (dfs(neigh, a, recstack)) {
                    return true;
                }
            } else if (recstack[neigh]) // already found that is already in stack
            {
                return true;
            }
        }
        recstack[s] = false;
        a.push(s);
        return false;

    }
}

public class Topocycle {
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
        Stack<Integer> p = new Stack<>();
        boolean[] recstack = new boolean[v];
        boolean cycle = false;
        for (int i = 0; i < v; i++) {
            if (!g.visit[i]) {
                if (g.dfs(i, p, recstack)) // cycle founded
                {
                    cycle = true;
                    break;
                }
            }
        }
        if (cycle) {
            System.out.println("Cycle found. Topological sort not possible");
        } else {
            System.out.println("The topological sort :");
            while (!p.isEmpty()) {
                System.out.println(p.pop());
            }
        }

    }

}