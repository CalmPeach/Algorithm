import java.io.*;
import java.util.*;
 
public class Test_2294 {
	
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	int n = Integer.parseInt(st.nextToken());
    	int k = Integer.parseInt(st.nextToken());
    	int[] dp = new int[k + 1];
    	Arrays.fill(dp, Integer.MAX_VALUE);
    	dp[0] = 0;
    	for(int i = 0; i < n; i++) {
    		int coin = Integer.parseInt(br.readLine());
    		for(int j = 1; j <= k; j++) {
    			if(j >= coin) {
    				if(dp[j - coin] == Integer.MAX_VALUE)
    					continue;
    				dp[j] = Math.min(dp[j - coin] + 1, dp[j]);
    			}
    		}
    	}
    	System.out.println(dp[k] == Integer.MAX_VALUE? -1: dp[k]);
    }
}