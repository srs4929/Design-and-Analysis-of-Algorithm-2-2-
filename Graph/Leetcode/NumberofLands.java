/*Given an m x n 2D binary grid grid which represents a map of '1's (land) and '0's (water), return the number of islands.

An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.

 

Example 1:

Input: grid = [
  ["1","1","1","1","0"],
  ["1","1","0","1","0"],
  ["1","1","0","0","0"],
  ["0","0","0","0","0"]
]
Output: 1*/

class Solution {
    public int numIslands(char[][] grid) {
        
        if(grid==null||grid.length==0) return 0;
        int r=grid.length;
        int c=grid[0].length;
        int number=0;
        for(int i=0;i<r;i++)
        {
            for(int j=0;j<c;j++)
            {
                if(grid[i][j]=='1')
                {
                    number++;
                    dfs(grid,i,j);
                }
            }
        }
        return number;
    }

    public void dfs(char[][] grid,int r,int c)
    {
        int row=grid.length;
        int col=grid[0].length;
        if(r<0 || c<0 || r>=row||c>=col || grid[r][c]=='0') return;
        grid[r][c]='0';
        dfs(grid,r+1,c);
        dfs(grid,r-1,c);
        dfs(grid,r,c-1);
        dfs(grid,r,c+1);


    }
}
