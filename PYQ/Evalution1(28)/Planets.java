/*A game has n planets, connected by m teleporters. Two planets a and b belong to the same kingdom exactly when there is a route both from a to b and from b to a. Your task is to determine for each planet its kingdom.
Input
The first input line has two integers n and m: the number of planets and teleporters. The planets are numbered 1,2,\dots,n.
After this, there are m lines describing the teleporters. Each line has two integers a and b: you can travel from planet a to planet b through a teleporter.
Output
First print an integer k: the number of kingdoms. After this, print for each planet a kingdom label between 1 and k. You can print any valid solution.
Constraints

1 \le n \le 10^5
1 \le m \le 2 \cdot 10^5
1 \le a,b \le n

Example
Input:
5 6
1 2
2 3
3 1
3 4
4 5
5 4

Output:
2
1 1 1 2 2
-Approach- SCC*/

import java.util.*;

public class Planets {

    static class Graph {
        int v;
        List<List<Integer>> adj;

        Graph(int v) {
            this.v = v;
            adj = new ArrayList<>();

            for (int i = 0; i <= v; i++) {
                adj.add(new ArrayList<>());

            }
        }

        void addedge(int u, int v) {
            adj.get(u).add(v);
        }

        public void dfs1(int s, boolean[] visit, Stack<Integer> stack) {
            visit[s] = true;
            for (int neigh : adj.get(s)) {
                if (!visit[neigh]) {
                    dfs1(neigh, visit, stack);
                }
            }
            stack.push(s);
        }

        public Graph transpose() {
            Graph g = new Graph(v);
            for (int i = 1; i <= v; i++) {
                for (int neigh : adj.get(i)) {
                    g.adj.get(neigh).add(i);
                }
            }
            return g;
        }

        public void dfs2(int s, boolean[] visit, List<Integer> Comp) {
            visit[s] = true;
            Comp.add(s);
            for (int neigh : adj.get(s)) {
                if (!visit[neigh]) {

                    dfs2(neigh, visit, Comp);

                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int v = sc.nextInt();
        Graph g = new Graph(v);

        int e = sc.nextInt();

        for (int i = 0; i < e; i++) {
            int u = sc.nextInt();
            int w = sc.nextInt();
            g.addedge(u, w);
        }
        boolean[] visit = new boolean[v + 1];
        Stack<Integer> s = new Stack<>();
        for (int i = 1; i <= v; i++) {
            if (!visit[i]) {
                g.dfs1(i, visit, s);
            }
        }

        Graph gt=g.transpose();
        for (int i = 1; i <= v; i++) {
            visit[i] = false;
        }
        List<List<Integer>>SCC=new ArrayList<>();
       
        while(!s.empty())
        {
            int x=s.pop();
            if(!visit[x])
            {
                List<Integer> comp = new ArrayList<>();
                gt.dfs2(x, visit, comp);
                SCC.add(comp);
            }

        }

        System.out.println(SCC.size());
        int []kingdom=new int[v+1];
        //assign the value
        for(int i=0;i<SCC.size();i++)
        {
            for(int planet:SCC.get(i))
            {
                kingdom[planet]=i+1;
            }
        }

        for(int i=1;i<=v;i++)
        {
           System.out.print(kingdom[i]+" ");
        }

    }

}
