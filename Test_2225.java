import java.io.*;
import java.util.*;

public class Test_2225 {
	
	static int n;
	static int cnt;
	
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	n = Integer.parseInt(st.nextToken());
    	int k = Integer.parseInt(st.nextToken());
    	int[][] dp = new int[n + 1][201];
    	
    	for(int i = 0; i <= n; i++)
    		dp[i][1] = 1;
    	
    	for(int i = 0; i <= n; i++) {
    		for(int j = 2; j <= 200; j++) {
    			for(int l = i; l >= 0; l--) {
    				dp[i][j] += dp[l][j - 1];
    				dp[i][j] %= 1000000000;
    			}
    			dp[i][j] %= 1000000000;
    		}
    	}
    	System.out.println(dp[n][k]);
    }
    
}