import java.util.*;

public class CountInversion {

    public static int mergesort(int[] arr, int left, int right, int[] temp) {
        int mid, invcount = 0;
        if (left < right) {
            mid = (left + right) / 2;

            invcount += mergesort(arr, left, mid, temp); // Left half
            invcount += mergesort(arr, mid + 1, right, temp); // Right half
            invcount += merge(arr, temp, left, mid, right); // Merge and count
        }
        return invcount;
    }

    private static int merge(int[] arr, int[] temp, int left, int mid, int right) {
        int i = left;
        int j = mid + 1;
        int k = left;
        int invCount = 0;

        while (i <= mid && j <= right) {
            if (arr[i] <= arr[j]) {
                temp[k++] = arr[i++];
            } else {
                temp[k++] = arr[j++];
                invCount += (mid - i + 1); // Count inversions
            }
        }

        while (i <= mid)
            temp[k++] = arr[i++];

        while (j <= right)
            temp[k++] = arr[j++];

        for (i = left; i <= right; i++)
            arr[i] = temp[i];

        return invCount;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++)
            arr[i] = sc.nextInt();

        int[] temp = new int[arr.length];
        System.out.println("Number of inversion " + mergesort(arr, 0, n - 1, temp));
    }

}
