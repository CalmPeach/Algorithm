import java.io.*;
import java.util.*;

public class Test_12865 {
	
	static int max;
	static int[][] arr;
	static int k;
	
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	int n = Integer.parseInt(st.nextToken());
    	k = Integer.parseInt(st.nextToken());
    	
    	int[][] w = new int[n + 1][2];
    	for(int i = 1; i <= n; i++) {
    		st = new StringTokenizer(br.readLine());
    		w[i][0] = Integer.parseInt(st.nextToken()); // 무게
    		w[i][1] = Integer.parseInt(st.nextToken()); // 가치
    	}
    	int[][] dp = new int[n + 1][k + 1];
    	for(int i = 1; i <= n; i++) {
    		for(int j = 1; j <= k; j++) {
    			if(w[i][0] > j)
    				dp[i][j] = dp[i - 1][j];
    			else
    				dp[i][j] = Integer.max(dp[i - 1][j], dp[i - 1][j - w[i][0]] + w[i][1]);
    		}
    	}
    	System.out.println(dp[n][k]);
    }
}

