
import java.util.*;

public class fractionalknapsack {

    static class itemcompare implements Comparator<int[]> {
        public int compare(int[] a, int[] b) {
            double a1 = (1.0 * a[0]) / a[1];
            double b1 = (1.0 * b[0]) / b[1];
            return Double.compare(b1, a1);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[][] item = new int[n][2];
        for (int i = 0; i < n; i++) {
            item[i][0] = sc.nextInt(); // value
            item[i][1] = sc.nextInt(); // weight
        }
        int w = sc.nextInt();
        Arrays.sort(item, new itemcompare());
        double res = 0;
        int currentcapacity = w;
        for (int i = 0; i < n; i++) {
            if (item[i][1] <= currentcapacity) {
                res += item[i][0];
                currentcapacity -= item[i][1];
            } else {
                res += ((1.0 * item[i][0]) / item[i][1]) * currentcapacity;
                break;

            }
        }
        System.out.println(res);

    }

}
