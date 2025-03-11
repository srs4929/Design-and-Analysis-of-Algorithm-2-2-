/* In this problem , you will be given a directed unweighted graph G with N nodes.
   Input Format :
   The first line of the input will contain two integers N and E.
   The next E lines will each contain two integers A and B, representing a directed edge from node A to node B
   After that, there will be a single integer X denoting a node from the given graph.

   Output Format :

   # In the first line , print a single integer denoting the number of
   adjacent nodes of the given node X

   # In the second line ,  print N values seperated by a single space where ith value denotes the minimun number of edges
   recquired to reach the ith node. If a node is not reachable from X, the corresponding value should be -1. There should not be 
   any trailing or leading space

   Example :
   5 7
   1 2
   1 4
   2 3
   2 4
   3 4
   3 5
   4 5
   1


 */
import java.util.*;
class Graph
{
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

  public void addedge(int u,int v)
  {
     adj.get(u).add(v);
   
     //adj.get(v).add(u);
  }
  public void BFS(int start)
  {
    boolean [] visit=new boolean[v];
    Queue<Integer>queue=new LinkedList();
    int [] distance=new int[v];
    for(int i=0;i<v;i++)
    {
      distance[i]=-1;
    }
     
    visit[start]=true;
    queue.add(start);
    distance[start]=0;
    int count=0;
    for(int neighbour:adj.get(start))
    {
         count++;
    }
    System.out.print(count +"\n");

    while(!queue.isEmpty())
    {
        int node=queue.poll();
        
        for(int neighbour:adj.get(node))
        {
           if(!visit[neighbour])
           {
              visit[neighbour]=true;
              queue.add(neighbour);
              distance[neighbour]=distance[node]+1;
           }
        }

    }
    for(int i=1;i<v;i++)
    {
      if(i==(v-1)) {System.out.print(distance[i]);
      System.out.println();
      
     
      }
      else 
      System.out.print(distance[i]+" ");

    }
  }
}

public class BFS{

  public static void main(String[] args)
  {  
     
     Scanner sc=new Scanner(System.in);
     int v=sc.nextInt();
     int n=v+1;
     Graph g=new Graph(n);
     
     int e=sc.nextInt();
     for(int i=0;i<e;i++)
     {
        int u=sc.nextInt();
        int w=sc.nextInt();
        g.addedge(u,w);
     }
      int start=sc.nextInt();
      g.BFS(start);




  }
}


