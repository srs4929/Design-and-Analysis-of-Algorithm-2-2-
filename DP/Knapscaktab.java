import java.util.*;

public class Knapscaktab {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number of items");
        int n = sc.nextInt();
        int[] wt = new int[n];
        int[] val = new int[n];
        System.out.println("Enter the wt of each item");
        for (int i = 0; i < n; i++)
            wt[i] = sc.nextInt();
        System.out.println("Enter the val of each item");
        for (int i = 0; i < n; i++)
            val[i] = sc.nextInt();
        System.out.println("Enter the capacity");
        int capacity = sc.nextInt();
        int result = 0;
        int[][] dp = new int[n + 1][capacity + 1];
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= capacity; j++) {
                if (i == 0 || j == 0) // no item chosen or capacity 0
                {
                    dp[i][j] = 0;
                } else {
                    int pick = 0;
                    if (wt[i - 1] <= j) {
                        pick = val[i - 1] + dp[i - 1][j - wt[i - 1]];
                    }
                    int notpick = dp[i - 1][j];
                    dp[i][j] = Math.max(pick, notpick);
                }
            }
        }
        System.out.println(dp[n][capacity]);

        // backtracking to identify which item was taken'

        int i = n;
        int j = capacity;
        System.out.println("Item taken");
        while (i > 0 && j > 0) {
             
            if(dp[i][j]!=dp[i-1][j]) //value changed but included
            {
               System.out.println(i+" ");
               j-=wt[i-1];
            }
            i--;
        }
        

    }
}

//time - o(n*capacity)
//space =o(n*capacity)