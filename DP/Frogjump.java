/* Given an integer array height[] where height[i] represents the height of the i-th stair, a frog starts from the first stair and wants to reach the top. From any stair i, the frog has two options: it can either jump to the (i+1)th stair or the (i+2)th stair. The cost of a jump is the absolute difference in height between the two stairs. Determine the minimum total cost required for the frog to reach the top.*/
import java.util.*;
public class Frogjump {
    public static int memo(int n,int [] h,int [] dp)
    {
        if(n==0) return 0; //base case
        if(dp[n]!=-1) return dp[n]; //it is already computed
        int onestep=memo(n-1,h,dp)+Math.abs(h[n]-h[n-1]);
        int twostep=Integer.MAX_VALUE;
        if(n>1){
         twostep=memo(n-2,h,dp)+Math.abs(h[n]-h[n-2]);
        }
        return dp[n]=Math.min(onestep,twostep);

    }
    public static int kstep(int n,int[] h,int[] dp,int k)
    {
        if(n==0) return 0;
        if(dp[n]!=-1) return dp[n];
        int mincost=Integer.MAX_VALUE;
        for(int j=1;j<=k;j++)
        {
             if(n-j>=0)
             {
                int jumpcost=kstep(n-j,h,dp,k)+Math.abs(h[n-j]-h[n]);
                mincost=Math.min(jumpcost,mincost);
             }
        }
        return dp[n]=mincost;
    }
    public static int tabulation(int n,int [] h)
    {
        int [] dp=new int[n];
        dp[0]=0; 
        int twostep=Integer.MAX_VALUE;//no step or energy needed
        for(int i=1;i<n;i++)
        {
             int onestep=dp[i-1]+Math.abs(h[i-1]-h[i]);
             if(i>1)
             {
               twostep=dp[i-2]+Math.abs(h[i-2]-h[i]);
             }
             dp[i]=Math.min(onestep,twostep);
        }
        return dp[n-1];
    }
    public static void main(String[] args)
    {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        int[] height=new int[n];
        for(int i=0;i<n;i++) height[i]=sc.nextInt();
        int [] dp=new int[n+1];
        Arrays.fill(dp,-1);
        System.out.println(memo(n-1,height,dp));
        System.out.println("Tabulation value "+tabulation(n, height));
        System.out.println("Enter number of steps");
        int k=sc.nextInt();
        Arrays.fill(dp,-1);
        System.out.println("Steps result "+kstep(n-1, height, dp, k));
    }
}

//space optimization can be done using previous and current
