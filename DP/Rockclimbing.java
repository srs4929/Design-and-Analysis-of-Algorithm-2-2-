
import java.util.*;

public class Rockclimbing {

    public static int memo(int i, int j, int[][] grid, int[][] dp) {

        int m = grid[0].length;
        if (i == 0)
            return grid[0][j]; // if first row return first rows vales
        if (i < 0 || j < 0 || j >= m)
            return Integer.MAX_VALUE; // edge bound
        if (dp[i][j] != -1)
            return dp[i][j];
        int middle = Integer.MAX_VALUE;
        int rightup = Integer.MAX_VALUE;
        int leftup = Integer.MAX_VALUE;
        if (i > 0)
            middle = grid[i][j] + memo(i - 1, j, grid, dp);
        if (i > 0 && j > 0)
            rightup = grid[i][j] + memo(i - 1, j - 1, grid, dp);
        if (i > 0 && j < m - 1)
            leftup = grid[i][j] + memo(i - 1, j + 1, grid, dp);
        return dp[i][j] = Math.min(middle, Math.min(rightup, leftup));

    }

    public static int tab(int n, int m, int[][] grid) {

        int[][] dp = new int[n][m];
        for (int i = 0; i < m; i++) {
            dp[0][i] = grid[0][i]; // base case , 1st row value will be same
        }

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int leftup = Integer.MAX_VALUE;
                int rightup = Integer.MAX_VALUE;
                int middle = grid[i][j] + dp[i - 1][j];
                if (j > 0)
                    rightup = grid[i][j] + dp[i - 1][j - 1];
                if (j < m - 1)
                    leftup = grid[i][j] + dp[i - 1][j + 1];
                dp[i][j] = Math.min(middle, Math.min(rightup, leftup));

            }
        }
        // from the last row find the min
        int result = Integer.MAX_VALUE;
        for (int i = 0; i < m; i++) {
            result = Math.min(result, dp[n - 1][i]);
        }
        return result;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[][] rock = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                rock[i][j] = sc.nextInt();
            }
        }
        int[][] dp = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                dp[i][j] = -1;
            }
        }
        int minDanger = Integer.MAX_VALUE; // start with a very large number

        for (int j = 0; j < m; j++) {
            // Calculate the minimum danger to reach cell (n-1, j)
            int dangerForColumn = memo(n - 1, j, rock, dp);

            // Keep track of the smallest danger among all bottom row cells
            minDanger = Math.min(minDanger, dangerForColumn);
        }
        System.out.println(minDanger);
        System.out.println("Tab " + tab(n, m, rock));
    }

}
