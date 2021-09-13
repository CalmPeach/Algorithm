import java.io.*;
import java.util.*;
 
public class Test_1932 {
	
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	int n = Integer.parseInt(br.readLine());
    	int[][] triangle = new int[n][n];
    	for(int i = 0; i < n; i++) {
    		StringTokenizer st = new StringTokenizer(br.readLine());
    		for(int j = 0; j <= i; j++)
    			triangle[i][j] = Integer.parseInt(st.nextToken());
    	}
    	
    	int[][] dp = new int[n][n];
    	dp[0][0] = triangle[0][0];
    	int max = 0;
    	for(int i = 1; i < n; i++) {
    		for(int j = 0; j <= i; j++) {
    			dp[i][j] = triangle[i][j];
    			if(j != 0) {
    				dp[i][j] += dp[i - 1][j - 1];
    			}
    			if(j != i) {
    				if(dp[i][j] < dp[i - 1][j] + triangle[i][j])
    					dp[i][j] = dp[i - 1][j] + triangle[i][j];
    			}
    			
    			if(i == n - 1 && max < dp[i][j])
    				max = dp[i][j];
    		}
    	}
    	System.out.println(max);
    }
}