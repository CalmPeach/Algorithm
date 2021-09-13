import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
 
public class Test_2133 {
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		int[] dp = new int[n + 1];
		dp[0] = 1;
		if(n >= 2)
			dp[2] = 3;
		for(int i = 4; i <= n; i += 2) { // 홀수인 경우 불가능 그러므로 경우의 수는 0
			dp[i] = dp[i - 2] * dp[2];
			for(int j = i - 4; j >= 2; j -= 2)
				dp[i] += dp[j] * 2; // 중복되지 않는 부분 
			dp[i] += 2; // 특별한 모양
		}
		System.out.println(dp[n]);
	}
}
