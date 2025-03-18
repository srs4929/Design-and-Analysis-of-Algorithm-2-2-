/*There are two types of professional wrestlers:

Faces (short for babyfaces – the "good guys")
Heels (the "bad guys")
Each wrestler can belong to only one of these two groups. There are n wrestlers, and you are given r pairs of wrestlers who have rivalries.

Your task is to determine whether it is possible to assign all wrestlers as either Faces or Heels such that:

Each rivalry exists between one Face and one Heel (i.e., no two rivals belong to the same group).
If such an assignment is possible, print the designation for each wrestler. Otherwise, print that it is not possible to make such a classification.

Input:
An integer n (number of wrestlers).
n wrestler name
An integer r (number of rivalry pairs).
r pairs of integers, where each pair represents a rivalry between two wrestlers.
Output:
If possible, output the classification of each wrestler as either Face or Heel.
If not possible, print "Not possible".
Example
5
4
John Mike
Mike Steve
Steve David
David Rick
Output
Possible to assign wrestlers.
Faces:
John Steve Rick
Heels:
Mike David*/

//If we just check the bipartite it will be okay

import java.util.*;

class Graph {

    public int v;
    public List<List<Integer>> adj;
    public String[] wrestler; // Array to store wrestler name

    Graph(int v) {
        this.v = v;
        adj = new ArrayList<>();
        wrestler = new String[v];

        for (int i = 0; i < v; i++) {
            adj.add(new ArrayList<>());
        }
    }

    public void addwrestler(int index, String name) {
        wrestler[index] = name;
    }

    public int getindex(String name) {
        for (int i = 0; i < v; i++) {
            if (wrestler[i].equals(name)) {
                return i;
            }
        }
        return -1; // no index found
    }

    public void addedge(String wres1, String wres2) {
        int index1 = getindex(wres1);
        int index2 = getindex(wres2);
        if (index1 == -1 || index2 == -1) {
            System.out.println("Not found");
            return;
        }

        adj.get(index1).add(index2);
        adj.get(index2).add(index1);
    }

    public boolean BFS(int s, int[] color, List<String> Faces, List<String> Rivals) {
        color[s] = 0;
        Queue<Integer> queue = new LinkedList<>();
        queue.add(s);
        Faces.add(wrestler[s]);

        while (!queue.isEmpty()) {
            int curr = queue.poll();
            for (int neighbour : adj.get(curr)) {
                if (color[neighbour] == -1) {
                    color[neighbour] = 1 - color[curr];
                    queue.add(neighbour);
                    if (color[neighbour] == 0)
                        Faces.add(wrestler[neighbour]);
                    else
                        Rivals.add(wrestler[neighbour]);
                } else if (color[neighbour] == color[curr]) {
                    return false;
                }
            }
        }
        return true;
    }

    public void bipartite() {
        int[] color = new int[v];
        List<String> Faces = new ArrayList<>();
        List<String> Rivals = new ArrayList<>();
        for (int i = 0; i < v; i++) {
            color[i] = -1;
        }
        for (int i = 0; i < v; i++) {
            if (color[i] == -1) // if not visited
            {
                if (!BFS(i, color, Faces, Rivals)) {
                    System.out.println("Not possible");
                    return;
                }
            }
        }
        for (String x : Faces) {
            System.out.println("Faces :");
            System.out.print(x + " ");
        }
        System.out.println();
        for (String x : Rivals) {
            System.out.println("Heels");
            System.out.print(x + " ");
        }
    }

}

public class Wrestler {
    public static void main(String[] args) {

        System.out.println("Enter the number of wrester");
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.nextLine();
        Graph g = new Graph(n);
        for (int i = 0; i < n; i++) {
            String x = sc.nextLine();
            g.addwrestler(i, x);

        }

        System.out.println("Enter the number of rivalry");

        int r=sc.nextInt();
        sc.nextLine();
        for(int i=0;i<r;i++)
        {
            String x,y;
            x=sc.next();
            y=sc.next();
            g.addedge(x,y);
        }

        g.bipartite();

    }

}
