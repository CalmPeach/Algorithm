import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
 
public class Test_9465 {
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int tc = Integer.parseInt(br.readLine());
		for(int t = 0; t < tc; t++) {
			int n = Integer.parseInt(br.readLine());
			int[][] sticker = new int[2][n + 1];
			for(int i = 0; i < 2; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for(int j = 1; j <= n; j++) {
					sticker[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			int[][] dp = new int[2][n + 1];
			dp[0][1] = sticker[0][1];
			dp[1][1] = sticker[1][1];
			for(int i = 2; i <= n; i++) {
				dp[0][i] = Math.max(dp[1][i - 1], dp[1][i - 2]) + sticker[0][i];
				dp[1][i] = Math.max(dp[0][i - 1], dp[0][i - 2]) + sticker[1][i];
			}
			System.out.println(Math.max(dp[0][n], dp[1][n]));
		}
	}
}
