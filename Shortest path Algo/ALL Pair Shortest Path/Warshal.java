import java.util.*;

public class Warshal {

    final static int INF = 99999;

    static class Graph {
        int v;
        int[][] adj;

        Graph(int[][] adj, int v) {
            this.v = v;
            this.adj = new int[v][v];
            for(int i=0;i<v;i++)
            {
                for(int j=0;j<v;j++)
                {
                    this.adj[i][j]=adj[i][j];
                }
            }

        }

        public void result() {
            for (int k = 0; k < v; k++) {
                for (int i = 0; i < v; i++) {
                    for (int j = 0; j < v; j++) {
                        
                        if(adj[i][k]!=INF && adj[k][j]!=INF)
                        adj[i][j] = Math.min(adj[i][j], (adj[i][k] + adj[k][j]));
                    }
                }

            }
            System.out.println("The result is ");
            for (int i = 0; i < v; i++) {
                for (int j = 0; j < v; j++) {
                    if (adj[i][j] == INF)
                        System.out.print("INF");
                    else
                        System.out.print(adj[i][j] + " ");
                }
                System.out.println();
            }

        }

    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int v = sc.nextInt();
        int[][] g = new int[v][v];
        for (int i = 0; i < v; i++) {
            for (int j = 0; j < v; j++) {
              String s=sc.next();
              if(s.equals("INF"))
              g[i][j]=INF;
              else g[i][j]=Integer.parseInt(s);

            }
        }
        Graph x = new Graph(g, v);
        x.result();

    }
}
