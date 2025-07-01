/* Consider a money system consisting of n coins. Each coin has a positive integer value. Your task is to calculate the number of distinct ways you can produce a money sum x using the available coins.
For example, if the coins are \{2,3,5\} and the desired sum is 9, there are 8 ways:

2+2+5
2+5+2
5+2+2
3+3+3
2+2+2+3
2+2+3+2
2+3+2+2
3+2+2+2

Input
The first input line has two integers n and x: the number of coins and the desired sum of money.
The second line has n distinct integers c_1,c_2,\dots,c_n: the value of each coin.
Output
Print one integer: the number of ways modulo 10^9+7.
Constraints

1 \le n \le 100
1 \le x \le 10^6
1 \le c_i \le 10^6*/

import java.util.*;

public class coincombination {
    static int mod = (int) 1e9;

    public static int result(int n, int[] coin, int target) {

        int[] dp = new int[target + 1];
        dp[0] = 1;
        for (int i = 1; i <= target; i++) {
            for (int j = 0; j < n; j++) {
                if (i >= coin[j]) {
                    dp[i] = (dp[i] + dp[i - coin[j]]) % mod;
                }
            }
        }
        return dp[target];
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] coin = new int[n];
        int target = sc.nextInt();

        for (int i = 0; i < n; i++)
            coin[i] = sc.nextInt();
        System.out.println(result(n, coin, target));
    }

}
