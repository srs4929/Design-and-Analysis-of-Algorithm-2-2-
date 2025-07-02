/* find the maximum sum of any consecutive subsequence (subarray) within the given sequence. */

import java.util.*;

public class maxsumconsecutive {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++)
            arr[i] = sc.nextInt();
        int[] dp = new int[n];
        dp[0] = arr[0];
        if (n == 1)
            System.out.println(arr[0]);
        for (int i = 1; i < n; i++) {

            dp[i] = Math.max(arr[i] + dp[i - 1], arr[i]);
        }
        int maxi = dp[0];
        for (int i = 1; i < n; i++) {
            maxi = Math.max(maxi, dp[i]);
        }
        System.out.println(maxi);

    }
}
