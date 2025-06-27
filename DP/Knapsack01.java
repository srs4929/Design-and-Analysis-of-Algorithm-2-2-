import java.util.*;

public class Knapsack01 {
    public static int knapsack(int[][] dpmemo, int capacity, int n, int[] wt, int[] val) {
        // if no item choosen or capacity 0(base case)
        if (n == 0 || capacity == 0)
            return 0;
        // if the value is already stored return
        if (dpmemo[n][capacity] != -1)
            return dpmemo[n][capacity];

        // if the item has been taken;
        int pick = 0;
        if (wt[n - 1] <= capacity) {
            pick = val[n - 1] + knapsack(dpmemo, capacity - wt[n - 1], n - 1, wt, val);
        }
        // not taken
        int notpick = knapsack(dpmemo, capacity, n - 1, wt, val);
        return dpmemo[n][capacity] = Math.max(pick, notpick);
    }

    public static int result(int[] wt, int[] val, int capacity, int n) {
        int[][] dpmemo = new int[n + 1][capacity + 1];
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= capacity; j++) {
                dpmemo[i][j] = -1;
            }
        }
        return knapsack(dpmemo, capacity, n, wt, val);
    }

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
        System.out.println(result(wt, val, capacity, n));
    }

}

// Time complexity = O(n * capacity)
// Space complexity = O(n * capacity)
// Recursion stack space = O(n)