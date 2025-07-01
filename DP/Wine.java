
/* Given n wines in a row, with integers denoting the cost of each wine respectively. Each year you can sale the first or the last wine in a row. Let the initial profits from the wines be P1, P2, P3...Pn. In the Yth year, the profit from the ith wine will be Y*Pi. For each year, your task is to print "beg" or "end" denoting whether first or last wine should be sold. Also, calculate the maximum profit from all the wines*/
import java.util.*;

public class Wine {

    public static int result(int i, int j, int year, int[][] dp, int n, int[] wine) {
        if (i == j)
            return wine[i] * year; // if one item
        if (i > j)
            return 0;
        if (dp[i][j] != -1)
            return dp[i][j];
        int left = wine[i] * year + result(i + 1, j, year + 1, dp, n, wine);
        int right = wine[j] * year + result(i, j - 1, year + 1, dp, n, wine);
        return dp[i][j] = Math.max(left, right);

    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] wine = new int[n];
        for (int i = 0; i < n; i++)
            wine[i] = sc.nextInt();
        // since we can take from left or right, we have 2 parameter
        int[][] dp = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                dp[i][j] = -1;
            }
        }
        int year = 1;
        System.out.println(result(0, n - 1, year, dp, n, wine));
    }

}
