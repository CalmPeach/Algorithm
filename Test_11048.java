import java.io.*;
import java.util.*;

public class Test_11048 {
	
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	int n = Integer.parseInt(st.nextToken());
    	int m = Integer.parseInt(st.nextToken());
    	int[][] map = new int[n][m];
    	for(int i = 0; i < n; i++) {
    		st = new StringTokenizer(br.readLine());
    		for(int j = 0; j < m; j++) {
    			map[i][j] = Integer.parseInt(st.nextToken());
    		}
    	}
    	
    	int[][] dp = new int[n][m];
    	dp[0][0] = map[0][0];
    	for(int i = 0; i < n; i++) {
    		for(int j = 0; j < m; j++) {
    			if(i == 0 && j != 0) {
    				dp[i][j] = dp[i][j - 1] + map[i][j];
    			} else if(j == 0 && i != 0) {
    				dp[i][j] = dp[i - 1][j] + map[i][j];
    			} else if(i != 0 && j != 0) {
    				dp[i][j] = Integer.max(dp[i][j - 1], dp[i - 1][j]);
                    dp[i][j] = Integer.max(dp[i][j], dp[i - 1][j - 1]) + map[i][j];
    			}
    		}
    	}
    	System.out.println(dp[n - 1][m - 1]);
    }
}

