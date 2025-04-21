/*Byteland has n cities, and m roads between them. The goal is to construct new roads so that there is a route between any two cities.
Your task is to find out the minimum number of roads required, and also determine which roads should be built.
Input
The first input line has two integers n and m: the number of cities and roads. The cities are numbered 1,2,\dots,n.
After that, there are m lines describing the roads. Each line has two integers a and b: there is a road between those cities.
A road always connects two different cities, and there is at most one road between any two cities.
Output
First print an integer k: the number of required roads.
Then, print k lines that describe the new roads. You can print any valid solution.
Constraints

1 \le n \le 10^5
1 \le m \le 2 \cdot 10^5
1 \le a,b \le n

Example
Input:
4 2
1 2
3 4

Output:
1
2 3*/


import java.util.*;

public class Buildingroads {
    
    static class Graph
    {
        int v;
        List<List<Integer>>adj;
        List<Integer>Component;
        boolean [] visit;
        Graph(int v)
        {
            this.v=v;
            adj=new ArrayList<>();
            for(int i=0;i<=v;i++)
            {
                adj.add(new ArrayList<>());
            }
            this.visit=new boolean[v+1];
        }

        public void add(int u,int v)
        {
            adj.get(u).add(v);
            adj.get(v).add(u);
        }

        public void dfs(int s)
        {
             visit[s]=true;
             for(int neigh:adj.get(s))
             {
                if(!visit[neigh])
                {
                   dfs(neigh);
                }
             }
        }

    }
    public static void main(String[] args)
    {
        Scanner sc=new Scanner(System.in);
        int c=sc.nextInt();
        int r=sc.nextInt();
        Graph g= new Graph(c);
        for(int i=0;i<r;i++)
        {
             int x=sc.nextInt();
             int y=sc.nextInt();
             g.add(x,y);
        }
       
        List<Integer>comp=new ArrayList<>();
        for(int i=1;i<=c;i++)
        {
             if(!g.visit[i])
             {   
                 comp.add(i);
                 g.dfs(i);
             }
        }
        System.out.println(comp.size()-1); //road need
        for(int i=1;i<comp.size();i++)
        {
            System.out.println(comp.get(i-1)+" "+comp.get(i));
        }

    }
    
}
