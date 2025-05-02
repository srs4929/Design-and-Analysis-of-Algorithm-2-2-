import java.util.*;

public class Activity {

    static void result(int[] start, int[] finish) {

        int n = start.length;
        int[][] arr = new int[n][2];
        for (int i = 0; i < n; i++) {
            arr[i][0] = start[i];
            arr[i][1] = finish[i];
        }
        int last=-1;
        int count=0;
        //sort basis one ending time
        Arrays.sort(arr,(a,b)->Integer.compare(a[1],b[1]));
        for(int i=0;i<n;i++)
        {
            if(arr[i][0]>last) //if start is greater than last then possible
            {
                last=arr[i][1];
                count++;
            }
        }
        System.out.println(count);

    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Event number");
        int n = sc.nextInt();
        int[] start = new int[n];
        int[] end = new int[n];
        System.out.println("Enter the starting time");
        for (int i = 0; i < n; i++) {
            start[i] = sc.nextInt();
        }
        System.out.println("Enter the ending time");
        for (int i = 0; i < n; i++) {
            end[i] = sc.nextInt();
        }
        result(start,end);
    }

}
