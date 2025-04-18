import java.util.*;

class Graph {
    public List<List<Integer>> adj;
    int t = 0;
    public boolean[] visit;
    public int[] dis;
    public int[] low;
    public int[] parent;
    public boolean[] isarticulation; // to mark articulation point

    Graph(int v) {
        adj = new ArrayList<>();
        for (int i = 0; i < v; i++)
            adj.add(new ArrayList<>());
        visit = new boolean[v];
        dis = new int[v];
        low = new int[v];
        parent = new int[v];
        isarticulation = new boolean[v];
        for (int i = 0; i < v; i++)
            parent[i] = -1;
    }

    public void addedge(int u, int v) {
        adj.get(u).add(v);
        adj.get(v).add(u);
    }

    public void dfs(int u) {
        visit[u] = true;
        dis[u] = low[u] = t++;  // dis & low time
        int child=0;
        
        for(int v: adj.get(u))
        {
            if(!visit[v])
            {
               child++;
               parent[v]=u;
               dfs(v);
               low[u]=Math.min(low[u],low[v]);
               //u is root and has more than one child
               if(parent[u]==-1 && child>1)
               {
                  isarticulation[u]=true;
               }
                
               if(parent[u]!=-1 && low[v]>=dis[u])
               {
                  isarticulation[u]=true;
               }

            }
            else if(v!=parent[u])  //back edge
            {
                low[u]=Math.min(low[u],dis[v]);
            }
        }
       
    }

    public void find()
    {
        for(int i=0;i<adj.size();i++)
        {
            if(!visit[i]) dfs(i);
        }

        //print articulation point
        for (int u = 0; u < isarticulation.length; u++) {
            if (isarticulation[u]) {
                System.out.println("Vertex " + u);
            }
        }
    }

}

public class Articulation{
    public static void main(String[] args)
    {
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter the number of vertices");
        int v=sc.nextInt();
        Graph g=new Graph(v);
        System.out.println("Enter the number of edges");
        int e=sc.nextInt();
        System.out.println("Enter the edges");
        for(int i=0;i<e;i++)
        {
           int u=sc.nextInt();
           int w=sc.nextInt();
           g.addedge(u,w);
        }
        g.find();
    }

}
