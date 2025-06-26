import java.util.*;

public class Fibomemo {
   public static int result(int n,int [] dp)
   {
         if(n<=1) return dp[n]=n;
         if(dp[n]!=-1) return dp[n];
         return dp[n]=result(n-1,dp)+result(n-2,dp);
   } 
    public static void main(String[] args){
    Scanner sc=new Scanner(System.in);
    int n=sc.nextInt();
    int [] dp=new int[n+1];
    Arrays.fill(dp,-1);
     System.out.println(result(n,dp));
    }
    
}

//memoziation time complexity:0(n),Space o(n)