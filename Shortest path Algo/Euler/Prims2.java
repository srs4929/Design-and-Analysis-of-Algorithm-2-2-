import java.util.*;
public class Main{
    
    static class Edge{
        
        int v;
        int w;
        Edge(int v,int w)
        {
           
            this.v=v;
            this.w=w;
        }
        
    }
    static class Graph{
        
        int v;
        List<List<Edge>>adj;
        Graph(int v)
        {    this.v=v;
            this.adj=new ArrayList<>();
            for(int i=0;i<v;i++) adj.add(new ArrayList<>());
        }
        public void addedge(int u,int v,int w)
        {
            adj.get(u).add(new Edge(v,w));
            adj.get(v).add(new Edge(u,w));
        }
        public void result(int s)
        {
            int [] key=new int[v];
            Arrays.fill(key,Integer.MAX_VALUE);
            boolean [] mst=new boolean[v];
            key[s]=0;
            PriorityQueue<int[]>pq=new PriorityQueue<>(Comparator.comparing(a->a[1]));
            int total=0;
            pq.add(new int []{s,0});
            while(!pq.isEmpty())
            {
                int []curr=pq.poll();
                int u=curr[0];
                if(mst[u]) continue;
                mst[u]=true;
                total+=curr[1];
                for(Edge e:adj.get(u))
                {
                    if(!mst[e.v] && e.w<key[e.v])
                    {
                        key[e.v]=e.w;
                        pq.add(new int []{e.v,e.w});
                    }
                }
            }
            System.out.println(total);
            
            
        }
    }
    public static void main(String[] args)
    {
        Scanner sc=new Scanner(System.in);
        int v=sc.nextInt();
        int e=sc.nextInt();
        Graph g=new Graph(v);
        for(int i=0;i<e;i++)
        {
            int a=sc.nextInt();
            int b=sc.nextInt();
            int w=sc.nextInt();
            g.addedge(a-1, b-1, w);
        }
        int s=sc.nextInt();
        g.result(s-1);
    }
}
        
                  
    

               
                
            
            
            
  
