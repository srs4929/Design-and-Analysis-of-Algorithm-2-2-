import java.util.*;

class Graph{

    public int v;
    public List<List<Integer>>adj;

    Graph(int v)
    {
        this.v=v;
        adj=new ArrayList<>();
        for(int i=0;i<v;i++)
        {
            adj.add(new ArrayList<>());
        }
    }

    //edge connect
    public void addedge(int u,int v)
    {
        adj.get(u).add(v);
        adj.get(v).add(u);
    }
    //BFS

    public void BFS(int start)
    {
        boolean [] visit=new boolean[v];
        Queue<Integer>queue=new LinkedList<>();

        visit[start]=true;
        queue.add(start);
        while(!queue.isEmpty())
        {
            int node=queue.poll();
            System.out.print(node+" ");
            //visit neighbour

            for(int neighbour: adj.get(node))
            {
                if(!visit[neighbour])
                {
                    visit[neighbour] =true;
                    queue.add(neighbour);
                }
            }
        }
    }
}

public class BFS{
    public static void main(String[] args)
    {
        Scanner sc=new Scanner(System.in);
        int v=sc.nextInt();//Vertex number
        Graph g=new Graph(v);
        System.out.print("Enter number of edges: ");
        int e = sc.nextInt();

        System.out.println("Enter edges (format: u v):");
        for (int i = 0; i < e; i++) {
            int u = sc.nextInt();
            int w = sc.nextInt();
            g.addedge(u, w);
        }

        System.out.print("Enter starting node for BFS: ");
        int startNode = sc.nextInt();

        System.out.println("BFS traversal from node " + startNode + ":");
        g.BFS(startNode);
        
        sc.close();

    }
}