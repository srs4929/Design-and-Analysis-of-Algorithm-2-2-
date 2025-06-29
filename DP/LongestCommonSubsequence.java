
/* Given two strings, s1 and s2, the task is to find the length of the Longest Common Subsequence. If there is no common subsequence, return 0. A subsequence is a string generated from the original string by deleting 0 or more characters, without changing the relative order of the remaining character*/
import java.util.*;

public class LongestCommonSubsequence {

    public static int memo(String a, String b, int i, int j, int[][] dp) {
        if (i < 0 || j < 0)
            return 0; // base case index bound
        if (dp[i][j] != -1)
            return dp[i][j];
        // if matched
        if (a.charAt(i) == b.charAt(j))
            return dp[i][j]=1 + memo(a, b, i - 1, j - 1, dp); // move the both index
        // if not matched two case move i or move j
        return dp[i][j]=0 + Math.max(memo(a, b, i - 1, j, dp), memo(a, b, i, j - 1, dp));
    }

    public static int tab(String a, String b, int x, int y) {
        int[][] dp = new int[x + 1][y + 1]; // shifting
        for (int i = 0; i <= y; i++)
            dp[0][i] = 0;
        for (int j = 0; j <= x; j++)
            dp[j][0] = 0;
        int count = 0;
        for (int i = 1; i <= x; i++) {
            for (int j = 1; j <= y; j++) {
                if (a.charAt(i - 1) == b.charAt(j - 1)) { // matched //since 0 based a,b so matching check i-1,j-1
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                } else { // not matched
                    dp[i][j] = 0 + Math.max(dp[i][j - 1], dp[i - 1][j]);
                }
            }
        }
        return dp[x][y];

    }

    public static int space(String a, String b, int x, int y) {
        int[] prev = new int[y + 1];
        int[] curr = new int[y + 1];
        Arrays.fill(prev, 0);
        for (int j = 0; j <= y; j++)
            prev[j] = 0;

         for (int i = 1; i <= x; i++) {
            for (int j = 1; j <= y; j++) {
                if (a.charAt(i - 1) == b.charAt(j - 1)) { // matched //since 0 based a,b so matching check i-1,j-1
                   curr[j] = 1 + prev[j - 1];
                } else { // not matched
                    curr[j] = 0 + Math.max(curr[j - 1], prev[j]);
                }
            }
            prev=curr;
        }
        return prev[y];   

    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String a = sc.nextLine();
        String b = sc.nextLine();
        int x = a.length();
        int y = b.length();
        int[][] dp = new int[x][y];
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                dp[i][j] = -1;
            }
        }
        System.out.println("Memo " + memo(a, b, x - 1, y - 1, dp));
        System.out.println("Tab " + tab(a, b, x, y));
        System.out.println("Space "+space(a, b, x, y));
    }
}
