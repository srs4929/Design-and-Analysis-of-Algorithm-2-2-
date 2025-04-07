import java.util.*;

class Graph {
    int v;
    List<List<Integer>> adj;
    boolean[] visit;

    Graph(int v) {
        this.v = v;
        adj=new ArrayList<>();
        for (int i = 0; i < v; i++) {
           adj.add(new ArrayList<>());
           
        }
        visit=new boolean[v];
    }

    public void addedge(int u,int v)
    {
       adj.get(u).add(v);
    }
    public void dfs(int s,Stack<Integer>a)
    {
        
        visit[s]=true;
      
        for(int neigh:adj.get(s))
        {
            if(!visit[neigh])
            {
               dfs(neigh,a);
            }
        }
        a.push(s);

      
    }
}

public class Topologicalsort {
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
        Stack<Integer>p=new Stack<>();
        for(int i=0;i<v;i++)
        {  
           if(!g.visit[i])  
            g.dfs(i,p);
        }
        System.out.println("The topological sort :");
        while(!p.isEmpty())
        {
            System.out.println(p.pop());
        }
       
       
    }

}
