import java.io.*;
import java.util.*;

public class Main {
	
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	int n = Integer.parseInt(br.readLine());
    	int[] wine = new int[n + 1];
    	for(int i = 1; i <= n; i++)
    		wine[i] = Integer.parseInt(br.readLine());
    	int[] dp = new int[n + 1];
    	int max = wine[1];
    	dp[1] = wine[1];
    	if(n >= 2) {
    		dp[2] = wine[1] + wine[2];
    		max = dp[2];
    	}
    	for(int i = 3; i <= n; i++) {
    		dp[i] = Integer.max(dp[i - 3] + wine[i - 1], dp[i - 2]) + wine[i];
    		dp[i] = Integer.max(dp[i - 1], dp[i]);
    		max = max < dp[i]? dp[i]: max;
    	}
    	System.out.println(max);
    }
}
/*
import java.io.*;
import java.util.*;
 
public class Test_2156 {
	
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	int n = Integer.parseInt(br.readLine());
    	int[] arr = new int[n + 1];
    	for(int i = 1; i <= n; i++)
    		arr[i] = Integer.parseInt(br.readLine());
    	int[][] dp = new int[n + 1][3];
    	dp[1][1] = arr[1];
    	dp[1][2] = arr[1];
    	int max = arr[1];
    	
    	if(n >= 2) {
    		dp[2][1] = arr[2];
    		dp[2][2] = arr[1] + arr[2];
    		max = dp[2][2];
    	}
    	
    	for(int i = 3; i <= n; i++) {
    		dp[i][0] = max;
    		for(int j = 1; j < 3; j++) {
    			if(j == 1) {
    				int big = Math.max(dp[i - 2][2], dp[i - 2][1]);
    				big = Math.max(big, dp[i - 1][0]);
    				dp[i][j] = big + arr[i];
    			} else if(j == 2) {
    				dp[i][j] = dp[i - 1][1] + arr[i];
    			} 
    			if(max < dp[i][j])
    				max = dp[i][j];
    		}
    	}
    	System.out.println(max);
    }
}
*/