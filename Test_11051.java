import java.io.*;
import java.util.*;
 
public class Test_11051 {
	
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	int n = Integer.parseInt(st.nextToken());
    	int k = Integer.parseInt(st.nextToken());
    	
    	int[][] dp = new int[n + 1][n + 1];
    	dp[0][0] = 1;
    	for(int i = 1; i <= n; i++) {
    		for(int j = 0; j <= i; j++) {
    			if(j != 0) {
    				dp[i][j] += dp[i - 1][j - 1];
    			}
    			if(j != i) {
    				dp[i][j] += dp[i - 1][j];
    			}
    			dp[i][j] = dp[i][j] % 10007;
     		}
    	}
    	System.out.println(dp[n][k]);
    }
}