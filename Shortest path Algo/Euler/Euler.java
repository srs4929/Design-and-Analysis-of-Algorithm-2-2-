
import java.util.*;
class Graph
{
    int v;
    List<List<Integer>>adj;
    Graph(int v)
    {
        this.v=v;
        adj=new ArrayList<>();
        for(int i=0;i<=v;i++) adj.add(new ArrayList<>());
    }

    public void addedge(int u,int v)
    {
        adj.get(u).add(v);
        adj.get(v).add(u); // undirected
    }

    public void dfs(int s,boolean [] visit)
    {
        visit[s]=true;
        for(int neigh: adj.get(s))
        {
             if(!visit[neigh])
             {
                dfs(neigh,visit);
             }
        }
    }
    public boolean connected()
    {
        boolean [] visit=new boolean[v+1];
        int start=-1; 
        for(int i=1;i<=v;i++) // finding first non-zero vertex
        {
            if(adj.get(i).size()>1) // got first non-zero vertex
            {
                start=i;
                break;
            }
        }

        if(start==-1) return true; // there is no non-zero vertex means no path
        dfs(start,visit) ; // if has non zero do dfs from it
        for(int i=1;i<=v;i++)
        {
            if(!visit[i] && adj.get(i).size()>0) // if the vertex is not visited but it has adjoint means has no path not connect
              return false; 
        }
        return true;
    }

    public void check()
    {
        if(!connected())
        {
            System.out.println("Graph is not Eulerian");
            return ;
        }

        int oddcount=0;
        for(int i=1;i<=v;i++)
        {
            if(adj.get(i).size()%2!=0)
            {
                oddcount++;
            }
        }
        if(oddcount==0) System.out.println("Graph has an eulerian circuit");
        else if(oddcount==2) System.out.println("Graph is trail");
        else System.out.println("Graph is not eulerian");
    }

     // Method to display the graph
     public void displayGraph() {
        for (int i = 1; i <= v; i++) {
            System.out.print(i + " -> ");
            for (int neighbor : adj.get(i)) {
                System.out.print(neighbor + " ");
            }
            System.out.println();
        }
    }
}

public class Euler
{
    public static void main(String[] args)
    {
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
        g.displayGraph();
        g.check();

    }
}
