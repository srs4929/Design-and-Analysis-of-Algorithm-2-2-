import java.util.*;

public class Fibnum {
    public static int fibmemo(int n, HashMap<Integer, Integer> memo) {
        if (n <= 1) {
            memo.put(n, n);
            return n;
        }
        if (memo.containsKey(n))
            return memo.get(n);

        int result = fibmemo(n - 1, memo) + fibmemo(n - 2, memo);
        memo.put(n, result);
        return result;
    }

    public static int fibotabo(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];

    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        HashMap<Integer, Integer> memo = new HashMap<>();
        System.out.println("Fibomemo "+ fibmemo(n, memo));
        System.out.println("Fibotabo "+ fibotabo(n));

    }

}
