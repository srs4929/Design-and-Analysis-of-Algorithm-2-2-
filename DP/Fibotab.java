import java.util.*;

public class Fibotab {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        if (n == 0) {
            System.out.println(0);
            return;
        }
        if (n == 1) {
            System.out.println(1);
            return;
        }

        int curr = 0;
        int prev1 = 0;
        int prev2 = 1;
        for (int i = 2; i <= n; i++) {
            curr = prev2 + prev1;
            prev1 = prev2;
            prev2 = curr;

        }
        System.out.println(curr);
    }

}

//space - O(1)
//time -O(n)
