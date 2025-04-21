import java.util.*;

class Graph {
    int v;
    List<List<Integer>> adj;
    int time = 0;

    Graph(int v) {
        this.v = v;
        adj = new ArrayList<>();
        for (int i = 0; i <= v; i++)
            adj.add(new ArrayList<>());
    }

    public void addedge(int u, int v) {
        adj.get(u).add(v);
        adj.get(v).add(u);
    }

    public void DFS(int s, boolean[] visit, int[] dis, int[] low, int[] parent) {
        visit[s] = true;
        dis[s] = low[s] = time++;
        for (int v : adj.get(s)) {
            if (!visit[v]) {
                parent[v] = s;
                DFS(v, visit, dis, low, parent);

                // tree edge
                low[s] = Math.min(low[s], low[v]);

                // Bridge condition
                if (low[v] > dis[s]) {
                    System.out.println(s + "->" + v);
                }
            } else if (v != parent[s]) { //back edge found
                
                low[s]=Math.min(low[v],dis[s]);
            }
        }

    }

    public void findBridges()
    {
        boolean [] visit=new boolean[v+1];
        int [] dis=new int[v+1];
        int [] low=new int[v+1];
        int [] parent=new int[v+1];
        for(int i=1;i<=v;i++)
        {   
            if(!visit[i])
            DFS(i,visit,dis,low,parent);
        }

    }
}

public class Bridge{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of vertices: ");
        int V = sc.nextInt();
        System.out.print("Enter number of edges: ");
        int E = sc.nextInt();

        Graph g = new Graph(V);

        System.out.println("Enter edges (u v):");
        for (int i = 0; i < E; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            g.addedge(u, v);
        }

        g.findBridges();
    }
    
}
