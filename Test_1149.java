import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
 
public class Test_1149 {
	
	static int min;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[][] cost = new int[n][3];
		for(int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j = 0; j < 3; j++)
				cost[i][j] = Integer.parseInt(st.nextToken());
		}
		
		int[][] dp = new int[n][3];
		for(int i = 0; i < 3; i++)
			dp[0][i] = cost[0][i];
		
		for(int i = 1; i < n; i++) {
			for(int j = 0; j < 3; j++)
				switch(j) {
					case 0:
						dp[i][j] = Math.min(dp[i - 1][1], dp[i - 1][2]) + cost[i][j];
						break;
					case 1:
						dp[i][j] = Math.mindp[i - 1][0], dp[i - 1][2]) + cost[i][j];
						break;
					case 2:
						dp[i][j] = Math.min(dp[i - 1][0], dp[i - 1][1]) + cost[i][j];
						break;
				}
		}
		
		int min = Integer.MAX_VALUE;
		for(int i = 0; i < 3; i++)
			if(min > dp[n - 1][i])
				min = dp[n - 1][i];
		System.out.println(min);
	}
	
}