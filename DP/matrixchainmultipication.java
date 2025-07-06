import java.util.*;

public class matrixchainmultipication {

    public static int memo(int i, int j, int[] arr, int[][] dp) {

        if (i == j)
            return 0; // one single element of array no possible of matrix multipication
        if (dp[i][j] != -1)
            return dp[i][j];
        int mini = Integer.MAX_VALUE; // k is a partion index
        for (int k = i; k < j; k++) {
            int step = arr[i - 1] * arr[j] * arr[k] + memo(i, k, arr, dp) + memo(k + 1, j, arr, dp);
            mini = Math.min(step, mini);
        }
        dp[i][j] = mini;
        return mini;
    }

    public static int tab(int[] arr, int n) {
        int[][] dp = new int[n][n];
        for (int i = 0; i < n; i++) { // if one single matrix the value is 0
            dp[i][i] = 0;
        }

        // for valid mul we need 2 matrix
      
        for (int len = 2; len < n; len++) {
            for (int i = 0; i < n - len; i++) {
                int j = i +len;
                dp[i][j] = Integer.MAX_VALUE;
                for (int k = i + 1; k < j; k++) {
                    int cost = dp[i][k] +dp[k][j] + arr[i] * arr[k] * arr[j];
                    dp[i][j]=Math.min(cost,dp[i][j]);
                }
            }

        }
        return dp[0][n - 1];
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++)
            arr[i] = sc.nextInt();
        int[][] dp = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                dp[i][j] = -1;
            }
        }
        System.out.println(memo(1, n - 1, arr, dp));
        System.out.println(tab(arr, n));

    }

}
