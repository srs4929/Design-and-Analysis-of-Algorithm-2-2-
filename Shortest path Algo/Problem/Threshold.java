/*There are n cities numbered from 0 to n-1. Given the array edges where edges[i] = [fromi, toi, weighti] represents a bidirectional and weighted edge between cities fromi and toi, and given the integer distanceThreshold.

Return the city with the smallest number of cities that are reachable through some path and whose distance is at most distanceThreshold, If there are multiple such cities, return the city with the greatest number.

Notice that the distance of a path connecting cities i and j is equal to the sum of the edges' weights along that path.
Leetcode: Find the city with smallest numbers at a threshold distance*/
import java.util.*;

public class Threshold {

    final static long inf = (long) 1e18;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int v = sc.nextInt();
        int e = sc.nextInt();
        long[][] adj = new long[v][v];
        for (int i = 0; i < v; i++) {
            for (int j = 0; j < v; j++) {
                if (i == j)
                    adj[i][j] = 0;
                else
                    adj[i][j] = inf;

            }
        }
        for (int i = 0; i < e; i++) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            int w = sc.nextInt();
            adj[x][y] = w;
        }

        int thread=sc.nextInt();

        for (int k = 0; k < v; k++) {
            for (int i = 0; i < v; i++) {
                for (int j = 0; j < v; j++) {
                    if (adj[i][k] != inf && adj[k][j] != inf) {
                        adj[i][j] = Math.min(adj[i][j], (adj[i][k] + adj[k][j]));
                    }
                }
            }
        }

        int minimum=v;
        int result=-1;
        for(int i=0;i<v;i++)
        {
            int count=0;
            for(int j=0;j<v;j++)
            {
                if(i!=j && adj[i][j]<=thread)
                {
                   count++;
                }
            }
            if(count<minimum)
            {
                minimum=count;
                result=i;
            }
        }

       System.out.println(result);

    }

}
/* Input: n = 4, edges = [[0,1,3],[1,2,1],[1,3,4],[2,3,1]], distanceThreshold = 4
Output: 3
Explanation: The figure above describes the graph. 
The neighboring cities at a distanceThreshold = 4 for each city are:
City 0 -> [City 1, City 2] 
City 1 -> [City 0, City 2, City 3] 
City 2 -> [City 0, City 1, City 3] 
City 3 -> [City 1, City 2] 
Cities 0 and 3 have 2 neighboring cities at a distanceThreshold = 4, but we have to return city 3 since it has the greatest number.*/
