import java.util.*;

class Graph
{
    public int v;
    public List<List<Integer>>arr;
    String color [];
    boolean hascycle=false;
    Graph(int v)
    {
      this.v=v;
      arr=new ArrayList<>();
      for(int i=0;i<=v;i++)
      {
         arr.add(new ArrayList<>());
      }
      color=new String[v+1];
      for(int i=1;i<=v;i++)
      {
         color[i]="White";
      }
    }

    public void addedge(int u,int v)
    {
        arr.get(u).add(v); // directed graph
    }

    public void detect(int s)
    {
         if(!color[s].equals("White")) return; //base case
         color[s]="Grey";
         for(int neigh: arr.get(s))
         {
            if(color[neigh].equals("White"))
            {
                detect(neigh);
            }
            else if(color[neigh].equals("Grey"))
            {
                hascycle=true;
                return;
            }
         }
         color[s]="Black";
    }
}

public class Cycledfs
{
    public static void main(String[] args) {
        
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter the number of vertices");
        int v=sc.nextInt();
        System.out.println("Enter the number of edges edges");
        int e=sc.nextInt();
        System.out.println("Enter the edges");
        Graph g=new Graph(v);
        for(int i=0;i<e;i++)
        {
           int u=sc.nextInt();
           int w=sc.nextInt();
            g.addedge(u, w); 

        }
        System.out.println("Enter the starting");
        int s=sc.nextInt();
        g.detect(s);

        if(g.hascycle)
        {
            System.out.println("Cycle detected");
        }
        else{
            System.out.println("No cycle");
        }
    }
}