import java.util.*;

public class LongestIncreasingSeqPrint {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++)
            arr[i] = sc.nextInt();
        int[][] dp = new int[n + 1][n + 1];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= n; j++) {
                dp[i][j] = 0;
            }
        }

        for (int index = n - 1; index >= 0; index--) {
            for (int prev = index - 1; prev >= -1; prev--) {
                int take = 0;
                if (prev == -1 || arr[index] > arr[prev])
                    take = 1 + dp[index + 1][index + 1];
                int dont = 0 + dp[index + 1][prev + 1];
                dp[index][prev + 1] = Math.max(take, dont);
            }
        }
        System.out.println(dp[0][0]);

        //Reconstruct
        List<Integer>lis=new ArrayList<>();
        int index = 0, prev = -1;

        while (index < n) {
            int take = 0;
            if (prev == -1 || arr[index] > arr[prev]) {
                take = 1 + dp[index + 1][index + 1];
            }
            int dont = dp[index + 1][prev + 1];

            if (take >= dont) {
                lis.add(arr[index]);
                prev = index;
            }
            index++;
        }
        System.out.println(lis);

    }

}
