import java.util.*;

class Graph {
    public int v;
    public List<List<Integer>> arr;
    public int maxdist; // Storing global farnode & maxdistance
    public int farnode;

    Graph(int v) {
        this.v = v;
        arr = new ArrayList<>(v+1);
        for (int i = 0; i < (v+1); i++) {
            arr.add(new ArrayList<>());
        }
        maxdist = 0;
        farnode = 0;
    }

    public void addedge(int u, int v) {
        arr.get(u).add(v);
        arr.get(v).add(u);
    }

    public void BFS(int start) {
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visit = new boolean[v + 1];
        int[] dist = new int[v + 1];
        queue.add(start);
        visit[start] = true;
        dist[start] = 0;
        maxdist = 0;
        farnode = start;
        while (!queue.isEmpty()) {
            int node = queue.poll();
            for (int neighbour : arr.get(node)) {
                if (!visit[neighbour]) {
                    visit[neighbour] = true;
                    dist[neighbour] = dist[node] + 1;
                    queue.add(neighbour);
                }

                if(dist[neighbour]>maxdist)
                {
                    maxdist=dist[neighbour];
                    farnode=neighbour;
                }

            }

        }
    }
    public int diameter(int start)
    {
        //start bfs from an arbitary node
        BFS(start);
        //Perform BFS from the farthest node found in the first BFS
        BFS(farnode);
        return maxdist;
    }
   /*  public int farthest(int start)
    {
        BFS(start);
        BFS(farnode);
        return farnode;
    }*/ 
}

public class diameter{
    public static void main(String[] args)
    {
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter the number of nodes");
        int vertex=sc.nextInt();
        Graph g=new Graph(vertex);
        System.out.println("Enter the number of edges");
        int e=sc.nextInt();
        System.out.println("Enter edges");
        for(int i=0;i<e;i++)
        {
           int u=sc.nextInt();
           int w=sc.nextInt();
           g.addedge(u, w);
        }
        System.out.println("Enter the starting node");
        int s=sc.nextInt();
        System.out.println("The diameter is "+g.diameter(s));
        //System.out.println("The farthest node "+g.diameter(s));
    }
}