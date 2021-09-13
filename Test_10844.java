import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
 
public class Test_10844 {
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[][] dp = new int[n + 1][10];
		for(int i = 0; i < 10; i++)
			dp[1][i] = 1;
		
		for(int i = 2; i <= n; i++) {
			for(int j = 0; j < 10; j++) {
				if(j != 0)
					dp[i][j] += dp[i - 1][j - 1];
				if(j != 9)
					dp[i][j] += dp[i - 1][j + 1];
				dp[i][j] = dp[i][j] % 1000000000;
			}
		}
		
		int sum = 0;
		for(int i = 1; i < 10; i++) {
			sum += dp[n][i];
			sum %= 1000000000;
		}
		System.out.println(sum % 1000000000);
	}
}
