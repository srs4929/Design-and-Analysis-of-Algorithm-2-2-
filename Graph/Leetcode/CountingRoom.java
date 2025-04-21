

/*You are given a map of a building, and your task is to count the number of its rooms. The size of the map is n \times m squares, and each square is either floor or wall. You can walk left, right, up, and down through the floor squares.
Input
The first input line has two integers n and m: the height and width of the map.
Then there are n lines of m characters describing the map. Each character is either . (floor) or # (wall).
Output
Print one integer: the number of rooms.
Constraints

1 \le n,m \le 1000

Example
Input:
5 8
########
#..#...#
####.#.#
#..#...#
########

Output:
3*/



import java.util.*;

public class CountingRoom {

    static class Graph {
        char[][] room;
        boolean[][] visit;

        Graph(char[][] room) {
            this.room = room;
            visit = new boolean[room.length][room[0].length];
        }

        void dfs(int x, int y) {
            int n = room.length;
            int m = room[0].length;

            if (x < 0 || y < 0 || x >= n || y >= m || visit[x][y] || room[x][y] == '#') return;

            visit[x][y] = true;

            dfs(x - 1, y); // up
            dfs(x + 1, y); // down
            dfs(x, y - 1); // left
            dfs(x, y + 1); // right
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        sc.nextLine(); 
        char[][] room = new char[n][m];
        for (int i = 0; i < n; i++) {
            String line = sc.nextLine(); // read each line
            room[i] = line.toCharArray(); // convert to char array
        }

        Graph g = new Graph(room);
        int count = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (!g.visit[i][j] && room[i][j] == '.') {
                    count++;
                    g.dfs(i, j);
                }
            }
        }

        System.out.println(count);
    }
}
