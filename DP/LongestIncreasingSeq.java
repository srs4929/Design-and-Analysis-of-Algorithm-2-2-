import java.util.*;

public class LongestIncreasingSeq {

    public static int result(int index, int prev, int[][] memo, int[] arr, int n) {
        if (index == n)
            return 0; // base case no found
        if (memo[index][prev + 1] != -1)
            return memo[index][prev + 1];

        // take'
        int take = 0;
        if (prev == -1 || arr[index] > arr[prev])
            take = 1 + result(index + 1, index, memo, arr, n);
        // not take
        int dont = 0 + result(index + 1, prev, memo, arr, n); // prev not changed

        return memo[index][prev+1] = Math.max(dont, take);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++)
            arr[i] = sc.nextInt();
        int[][] memo = new int[n][n + 1]; // prev index shifting
        for(int i=0;i<n;i++)
        {
            for(int j=0;j<=n;j++)
            memo[i][j]=-1;
        }
        System.out.println(result(0,-1,memo,arr,n));

    }

}
