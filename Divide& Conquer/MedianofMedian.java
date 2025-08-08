import java.util.*;

public class MedianofMedian {
    static int getMedian(int[] group) {
        Arrays.sort(group);
        return group[group.length / 2];
    }

    // Partition array around pivot value, return pivot's final index
    static int partition(int[] arr, int left, int right, int pivotValue) {
        // Move pivot to the end
        for (int i = left; i < right; i++) {
            if (arr[i] == pivotValue) {
                int temp = arr[i];
                arr[i] = arr[right];
                arr[right] = temp;
                break;
            }
        }

        // Partition: smaller values on left
        int storeIndex = left;
        for (int j = left; j < right; j++) {
            if (arr[j] <= pivotValue) {
                int temp = arr[storeIndex];
                arr[storeIndex] = arr[j];
                arr[j] = temp;
                storeIndex++;
            }
        }

        // Move pivot to its final place
        int temp = arr[storeIndex];
        arr[storeIndex] = arr[right];
        arr[right] = temp;

        return storeIndex;
    }

    public static int kthsmallest(int[] arr, int left, int right, int k) {
        if (k > 0 && k <= right - left + 1) // in the range
        {
            int n = right - left + 1;
            ArrayList<Integer> median = new ArrayList<>();
            int i;
            for (i = 0; i < n / 5; i++) {
                int[] group = Arrays.copyOfRange(arr, left + i * 5, left + i * 5 + 5); // 5 chuncks
                median.add(getMedian(group));
            }
            // Handle the last group with less than 5 elements
            if (i * 5 < n) {
                int[] lastGroup = Arrays.copyOfRange(arr, left + i * 5, left+ i * 5 + (n % 5));
                median.add(getMedian(lastGroup));
            }
            int pivot;
            if (median.size() == 1)
                pivot = median.get(0);
            else {
                int[] medArr = new int[median.size()];
                for (int j = 0; j < median.size(); j++) medArr[j] = median.get(j);
                pivot = kthsmallest(medArr, 0, medArr.length - 1, medArr.length / 2);
            }

            // Partition array and get position of pivot
            int pos = partition(arr, left, right, pivot);

            // If position matches k, return result
            if (pos - left == k - 1) return arr[pos];

            // Recur on left or right part accordingly
            if (pos - left > k - 1)
                return kthsmallest(arr, left, pos - 1, k);

            return kthsmallest(arr, pos + 1, right, k - pos + left - 1);
        }

        return Integer.MAX_VALUE;
        }
    

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++)
            arr[i] = sc.nextInt();
        int k = sc.nextInt();
        System.out.println(kthsmallest(arr, 0, n-1, k));
    }
}
