import java.util.*;

public class A {

    public static class ItemCompare implements Comparator<int[]> {
        public int compare(int[] a, int[] b) {
            return Integer.compare(a[1], b[1]);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Map<String, Integer> day = new HashMap<>();
        day.put("fri", 0);
        day.put("sat", 1);
        day.put("sun", 2);
        day.put("mon", 3);
        day.put("tue", 4);
        day.put("wed", 5);
        day.put("thu", 6);
        int n = sc.nextInt();
        int[][] activity = new int[n][2];
        for (int i = 0; i < n; i++) {
            String d1 = sc.next();
            int t1 = sc.nextInt();
            activity[i][0] = day.get(d1) * 24 + t1;
            String d2 = sc.next();
            int t2 = sc.nextInt();
            activity[i][1] = day.get(d2) * 24 + t2;
        }
        Arrays.sort(activity, new ItemCompare());
        int last = Integer.MIN_VALUE;
        int count = 0;
        for (int i = 0; i < n; i++) {
            if (activity[i][0] >=last) {
                count++;
                last = activity[i][1];
            }
        }
        System.out.println(count);

    }
   

}