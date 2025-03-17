import java.util.*;

class Graph {
    private int v;
    private List<List<Integer>> adj;

    Graph(int v) {
        this.v = v;
        adj = new ArrayList<>();
        for (int i = 0; i <= v; i++) {
            adj.add(new ArrayList<>());
        }
    }

    public void addedge(int u, int v) {
        adj.get(u).add(v);
        adj.get(v).add(u);
    }

    public int[] BFS(int s) {
        boolean[] visit = new boolean[v+1];
        int[] prev = new int[v+1];
        for (int i = 0; i <= v; i++) {
            prev[i] = -1;
        }

        Queue<Integer> queue = new LinkedList<>();
        queue.add(s);
        visit[s] = true;

        while (!queue.isEmpty()) {
            int curr = queue.poll();
            for (int neighbour : adj.get(curr)) {
                if (visit[neighbour] == false) {
                    prev[neighbour] = curr;
                    visit[neighbour]=true;
                    queue.add(neighbour);
                }
            }
        }
        return prev;
    }

    public void print(int[] prev, int start, int target) {
        if (target == start) {
            System.out.print(start);
        } else if (prev[target] == -1) {
            System.out.println("No path found");
        } else {
            print(prev, start, prev[target]);
            System.out.print("-> " + target);
        }
    }

}

public class ShortestPath {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int v = sc.nextInt();
        Graph g = new Graph(v);
        System.out.print("Enter number of edges: ");
        int e = sc.nextInt();

        System.out.println("Enter edges ");
        for (int i = 0; i < e; i++) {
            int u=sc.nextInt();
            int w=sc.nextInt();
            g.addedge(u,w);

        }
        System.out.println("Enter the starting node");
        int start=sc.nextInt();
        System.out.println("Enter the target node");
        int target=sc.nextInt();
        int [] prev=g.BFS(start);
        System.out.println("Path");
        g.print(prev,start,target);

    }

}

//Example :
// 1-2-5
// |  |
// 4 -3
/* Target node 4 starting 1 shortest path 1-4 */

