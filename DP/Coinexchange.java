
/* Minimum coin choose */
import java.util.*;

public class Coinexchange {
    public static int space(int n, int[] coin, int target) {
        int[] prev = new int[target + 1];
        int[] curr = new int[target + 1];
        Arrays.fill(prev, (int)1e9);
        Arrays.fill(curr, 0);
        for (int i = 0; i <= target; i++) {
            if (i % coin[0] == 0) {
                prev[i] = i / coin[0];
            } else {
                prev[i] = (int) 1e9;
            }
        }

        // do some stuff on indexes
        for (int i = 1; i < n; i++) {
            for (int j = 0; j <= target; j++) {
                int pick = Integer.MAX_VALUE; // give recurence formula just
                if (j >= coin[i]) {
                    pick = 1 + curr[j - coin[i]];
                }
                int notpick = prev[j];
                curr[j] = Math.min(pick, notpick);
            }
            prev=curr;
        }

        if (prev[target] >= (int) 1e9)
            return -1;
        else
            return prev[target];

    }

    public static int tab(int n, int[] coin, int target) {
        int[][] dp = new int[n][target + 1];
        // base case
        for (int i = 0; i <= target; i++) {
            if (i % coin[0] == 0) {
                dp[0][i] = i / coin[0];
            } else {
                dp[0][i] = (int) 1e9;
            }
        }

        // do some stuff on indexes
        for (int i = 1; i < n; i++) {
            for (int j = 0; j <= target; j++) {
                int pick = Integer.MAX_VALUE; // give recurence formula just
                if (j >= coin[i]) {
                    pick = 1 + dp[i][j - coin[i]];
                }
                int notpick = dp[i - 1][j];
                dp[i][j] = Math.min(pick, notpick);
            }
        }

        if (dp[n - 1][target] >= (int) 1e9)
            return -1;
        else
            return dp[n - 1][target];

    }

    public static int memo(int n, int[] coin, int[][] dp, int target) {

        if (n == 0) {
            if (target % coin[n] == 0)
                return target / coin[n];
            else
                return (int) 1e9;
        }
        if (dp[n][target] != -1)
            return dp[n][target];
        // two option pick or not pick
        int notpick = 0 + memo(n - 1, coin, dp, target);
        // if pick
        int pick = Integer.MAX_VALUE;
        if (target >= coin[n]) // only if smaller or equal
            pick = 1 + memo(n, coin, dp, target - coin[n]); // remain on this if more can happen
        return dp[n][target] = Math.min(pick, notpick);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] coin = new int[n];
        for (int i = 0; i < n; i++)
            coin[i] = sc.nextInt();
        int sum = sc.nextInt();
        int[][] dp = new int[n][sum + 1];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= sum; j++) {
                dp[i][j] = -1;
            }
        }
        System.out.println("Memo " + memo(n - 1, coin, dp, sum));
        System.out.println("tab " + tab(n, coin, sum));
        System.out.println("Space "+space(n, coin, sum));

    }

}
