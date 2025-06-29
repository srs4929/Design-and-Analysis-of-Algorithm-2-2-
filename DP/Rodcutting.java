
/* Given a rod of length n inches and an array price[]. price[i] denotes the value of a piece of length i. The task is to determine the maximum value obtainable by cutting up the rod and selling the pieces*/
import java.util.*;

public class Rodcutting {

    public static int memo(int index, int[] price, int[][] dp, int n) {

        if (index == 0)
            return 0; // base case no rod is taken
        if (index == 1) // if 1 length rod is taken //base case2
        {
            return dp[index][n] = price[index] * n; // like 1 length i have want 4 lrngth then 1+1+1+1
        }
        if (dp[index][n] != -1)
            return dp[index][n]; // already calculated
        int notpick = 0 + memo(index - 1, price, dp, n);

        int pick = Integer.MIN_VALUE;
        if (index <= n) // if current length is less than target
            pick = price[index] + memo(index, price, dp, n - index); // I can up to take many times so not moving
        return dp[index][n] = Math.max(pick, notpick);
    }

    public static int tab(int[] price, int n) {
        // base case
        int[][] dp = new int[n + 1][n + 1];
        for (int i = 0; i <= n; i++) {
            dp[0][i] = 0; // for no rod taken the value is 0
        }
        for (int i = 1; i <= n; i++) {
            dp[1][i] = price[1] * i;
        }
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j <= n; j++) {

                int dontpick = dp[i - 1][j] + 0;
                int pick = Integer.MIN_VALUE;
                if (i <= j) {
                    pick = price[i] + dp[i][j - i]; // decrease the length
                }
                dp[i][j] = Math.max(pick, dontpick);
            }
        }
        return dp[n][n];

    }

    public static int space(int[] price, int n) {
        int[] prev = new int[n + 1];
        int[] curr = new int[n + 1];

        Arrays.fill(curr, 0);
        for (int i = 0; i <= n; i++) {
            prev[i] = 0; // for no rod taken the value is 0
        }
        for (int i = 1; i <= n; i++) {
            prev[i] = price[1] * i;
        }
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j <= n; j++) {

                int dontpick = prev[j] + 0;
                int pick = Integer.MIN_VALUE;
                if (i <= j) {
                    pick = price[i] + curr[j - i]; // decrease the length
                }
                curr[j] = Math.max(pick, dontpick);
            }
            prev = curr;
        }
        return prev[n];

    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] price = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            price[i] = sc.nextInt();
        }
        int[][] dp = new int[n + 1][n + 1];
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= n; j++) {
                dp[i][j] = -1;
            }
        }
        System.out.println("Memo " + memo(n, price, dp, n));
        System.out.println("Tabu " + tab(price, n));
        System.out.println("Space "+tab(price, n));
    }

}
