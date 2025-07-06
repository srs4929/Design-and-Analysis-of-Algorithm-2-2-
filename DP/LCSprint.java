import java.util.*;

public class LCSprint {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String a = sc.nextLine();
        String b = sc.nextLine();
        int x = a.length();
        int y = b.length();
        int[][] dp = new int[x + 1][y + 1];
        for (int i = 0; i <= x; i++)
            dp[i][0] = 0;
        for (int i = 0; i <= y; i++)
            dp[0][i] = 0;

        // lcs count
        for (int i = 1; i <= x; i++) {
            for (int j = 1; j <= y; j++) {

                if (a.charAt(i - 1) == b.charAt(j - 1)) // match
                {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }

            }
        }
        System.out.println(dp[x][y]);// print it

        // for printing lcs
        int i = x;
        int j = y;
        String result = "";
        while (i > 0 && j > 0) {

            if (a.charAt(i - 1) == b.charAt(j - 1)) {

                result += a.charAt(i - 1);
                i--;
                j--;

            } else if (dp[i - 1][j] > dp[i][j - 1]) {
                i--;
            } else
                j--;

        }
        String reverse = "";
        for (int p = result.length() - 1; p >= 0; p--) {
            reverse += result.charAt(p);
        }
        System.out.println(reverse);

    }

}
