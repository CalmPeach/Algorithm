import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
 
public class Test_15486 {
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[] dp = new int[n + 1];
		int max = 0;
		int bigIdx = 0;
		for(int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int t = Integer.parseInt(st.nextToken());
			int p = Integer.parseInt(st.nextToken());
			if(dp[i] == 0 || dp[i] < dp[bigIdx]) {
				dp[i] = dp[bigIdx];
			}
			if(dp[bigIdx] < dp[i]) {
				bigIdx = i;
			}
			
			if(i + t >= n + 1) {
				continue;
			}
			if(dp[i + t] < dp[i] + p) {
				dp[i + t] = dp[i] + p;
				if(dp[i + t] > max)
					max = dp[i + t];
			}
		}
		System.out.println(max);
	}
}
