
/* Given an array of ‘N’  positive integers, we need to return the maximum sum of the subsequence such that no two elements of the subsequence are adjacent elements in the array.*/
import java.util.*;

public class Maxsubsequence {
    public static int memo(int index, int[] dp, int[] arr) {
        if (index == 0)
            return arr[0];
        if (index < 0)
            return 0;
        if (dp[index] != -1)
            return dp[index];
        int pick = arr[index] + memo(index - 2, dp, arr);
        // if taken I can not take the next as adjacent
        int dontpick = 0 + memo(index - 1, dp, arr);
        return dp[index] = Math.max(pick, dontpick);
    }

    public static int tab(int index, int[] arr) {
        int[] dp = new int[index];
        dp[0] = arr[0];
        for (int i = 1; i < index; i++) {
            if (i == 1) {
                int pick = arr[i];
                int dontpick = 0 + dp[i - 1];
                dp[i] = Math.max(pick, dontpick);
            } else {
                int pick = arr[i] + dp[i - 2];// adjacent baad
                int dontpick = 0 + dp[i - 1];
                dp[i] = Math.max(pick, dontpick);
            }

        }
        return dp[index - 1];

    }

    public static int space(int index,int [] arr)
    {
        int prev1=arr[0];
        int prev2=0;
        int curr;
        for (int i = 1; i < index; i++) {
            if (i == 1) {
                int pick = arr[i];
                int dontpick = 0 + prev1;
                curr = Math.max(pick, dontpick);
                prev2=prev1;
                prev1=curr;
            } else {
                int pick = arr[i] + prev2;// adjacent baad
                int dontpick = 0 + prev1;
                curr= Math.max(pick, dontpick);
                prev2=prev1;
                prev1=curr;
            }

        }
        return prev1;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++)
            arr[i] = sc.nextInt();
        int[] dp = new int[n];
        Arrays.fill(dp, -1);
        System.out.println(memo(n - 1, dp, arr));
        System.out.println("tabulation " + tab(n, arr));
        System.out.println("Space optimized "+space(n,arr));
    }

}
