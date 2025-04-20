import java.util.*;

public class C {

    static class Graph {
        int r;
        int c;
        int[][] grid;

        Graph(int[][] grid) {
            this.r = grid.length;
            this.c = grid[0].length;
            this.grid = grid;
        }
        
        public int dfs(int row,int col)
        {
             if(row<0 || col<0 || row>=r || col>=c || grid[row][col]==0 )
             {
                return 1; //perimeter found
             }
             if(grid[row][col]==-1) return 0;//already visited
             grid[row][col]= -1 ;//mark it -1 if it is visited
             int result=0;
             result+=dfs(r-1,c); //left
             result+=dfs(r+1,c);//right
             result+=dfs(r,c-1); //up
             result+=dfs(r,c+1); //down
             return result;


        }
        public int result()
        {
            for(int i=0;i<r;i++)
            {
                for(int j=0;j<c;j++)
                {
                    if(grid[i][j]==1)
                    {
                        return dfs(i,j);
                    }
                }
            }
            return 0;
        }

       
    }

        public static void main(String[] args) {
            Scanner sc = new Scanner(System.in);
            int r = sc.nextInt();
            int c = sc.nextInt();
            int[][] grid = new int[r][c];
            for (int i = 0; i < r; i++) {
                for (int j = 0; j < c; j++) {
                    int x = sc.nextInt();
                    grid[i][j] = x;
                }
            }
            Graph g=new Graph(grid);
            System.out.println(g.result());

        }

}
