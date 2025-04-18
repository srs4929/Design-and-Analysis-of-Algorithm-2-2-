import java.util.*;

public class Main
{   
    static class Graph
    {
        int v;
        List<List<Integer>>adj;
        int [] d;
        int [] f;
        String[] color;
        int time=0;
        Graph(int v)
        {
            this.v=v;
            adj=new ArrayList<>();
            for(int i=0;i<v;i++) adj.add(new ArrayList<>());
            this.d=new int[v];
            this.f=new int[v];
            color=new String[v];
            for(int i=0;i<v;i++) color[i]="White";
        }
        public void addedge(int u,int v)
        {
            adj.get(u).add(v);
            adj.get(v).add(u);
        }
        public void dfs(int s)
        {   
            if(!color[s].equals("White")) return;
            time++;
            d[s]=time;
            color[s]="Grey";
            System.out.println("First visited "+s+" discovery "+d[s]);
            for(int v:adj.get(s))
            {
                if(color[v].equals("White"))
                {
                   dfs(v);
                }
            }
            time++;
            f[s]=time;
            color[s]="Black";
            System.out.println("Last visited "+s + " finish "+f[s]);
            
        }
    }
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
        //sort it in lexagraphical order
        for(int i=0;i<v;i++)
        {
            Collections.sort(g.adj.get(i));
        }
        g.dfs(startNode);
    }
}
