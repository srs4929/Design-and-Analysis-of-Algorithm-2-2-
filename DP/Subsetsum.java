
/* Given an array arr[] of non-negative integers and a value sum, the task is to check if there is a subset of the given array whose sum is equal to the given sum. 

Examples: 

Input: arr[] = [3, 34, 4, 12, 5, 2], sum = 9
Output: True
Explanation: There is a subset (4, 5) with sum 9.*/
import java.util.*;

public class Subsetsum {
    public static boolean tab(int n, int arr[], int targetsum) {
        boolean[][] dp = new boolean[n][targetsum + 1];
        for (int i = 0; i < n; i++) {
            dp[i][0] = true; // if target sum is 0 then it is always be true
        }

        for (int j = 1; j <= targetsum; j++) {
            dp[0][j] = (arr[0] == j);
        }

        for (int i = 1; i < n; i++) {
            for (int j = 1; j <= targetsum; j++) {
                boolean pick = false, notpick;
                if (j >= arr[i]) // if targetsum is greater then pick option
                {
                    pick = dp[i - 1][j - arr[i]];
                }
                notpick = dp[i - 1][j];
                dp[i][j] = pick || notpick;
            }
        }
        return dp[n - 1][targetsum];

    }

    public static boolean memo(int index, int targetsum, int arr[], int dp[][]) {
        if (targetsum == 0)
            return true; // base case
        if (index == 0)
            return (arr[0] == targetsum); // base case if index 0 is target or not
        // we have two option pick , not pick
        if (dp[index][targetsum] != -1)
            return dp[index][targetsum] == 1;
        boolean notpick = memo(index - 1, targetsum, arr, dp);// that index not taken move
        boolean pick = false;
        if (targetsum >= arr[index]) {
            pick = memo(index - 1, targetsum - arr[index], arr, dp);
        }
        dp[index][targetsum] = (pick || notpick) ? 1 : 0;
        return pick || notpick;

    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++)
            arr[i] = sc.nextInt();
        int targetsum = sc.nextInt();
        int[][] dp = new int[n][targetsum + 1];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= targetsum; j++) {
                dp[i][j] = -1;
            }
        }
        System.out.println(memo(n - 1, targetsum, arr, dp));
        System.out.println(tab(n, arr, targetsum));

    }

}
