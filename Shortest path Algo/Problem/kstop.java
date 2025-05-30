/*There are n cities connected by some number of flights. You are given an array flights where flights[i] = [fromi, toi, pricei] indicates that there is a flight from city fromi to city toi with cost pricei.

You are also given three integers src, dst, and k, return the cheapest price from src to dst with at most k stops. If there is no such route, return -1.*/



import java.util.ArrayList;
import java.util.Scanner;
import java.util.*;

public class kstop {
    static class Edge {
        int to;
        int w;

        Edge(int to, int w) {
            this.to = to;
            this.w = w;
        }
    }

    static class Graph {
        List<List<Edge>> edge = new ArrayList<>();
        int v;

        Graph(int v) {
            this.v = v;
            this.edge = new ArrayList<>();
            for (int i = 0; i < v; i++) {
                edge.add(new ArrayList<>());
            }
        }

        public void addedge(int u, int v, int w) {
            edge.get(u).add(new Edge(v, w));
            // edge.get(v).add(new Edge(u, w));
        }

        public void result(int sc, int dst, int k) {
            int[] dist = new int[v];
            Arrays.fill(dist, Integer.MAX_VALUE);
            dist[sc]=0;
            for (int i = 0; i <= k; i++) {
                int[] temp = Arrays.copyOf(dist, v);
                for (int u = 0; u < v; u++) {
                    for (Edge edge : edge.get(u)) {
                         int v=edge.to;
                         int w=edge.w;
                        if(dist[u]!=Integer.MAX_VALUE && dist[u]+w<temp[v])
                        {
                              temp[v]=dist[u]+w;
                        }
                    }
                }
                dist=temp;
            }
            if(dist[dst]==Integer.MAX_VALUE) System.out.println(-1);
            else System.out.println(dist[dst]);
        }

    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int v = sc.nextInt();
        int e = sc.nextInt();
        Graph g = new Graph(v);
        for (int i = 0; i < e; i++) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            int w = sc.nextInt();
            g.addedge(x, y, w);
        }
        int src = sc.nextInt();
        int dst = sc.nextInt();
        int k = sc.nextInt();
        g.result(src, dst, k);
    }

}

/*Input: n = 3, flights = [[0,1,100],[1,2,100],[0,2,500]], src = 0, dst = 2, k = 1
Output: 200
Explanation:
The graph is shown above.
The optimal path with at most 1 stop from city 0 to 2 is marked in red and has cost 100 + 100 = 200.*/
