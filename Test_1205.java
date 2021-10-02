import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Test_1205{
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		int[] memory = new int[n + 1];
		int[] price = new int[n + 1];
		st = new StringTokenizer(br.readLine());
		for(int i = 1; i <= n; i++) {
			memory[i] = Integer.parseInt(st.nextToken());
		}
		
		st = new StringTokenizer(br.readLine());
		int min = Integer.MAX_VALUE;
		for(int i = 1; i <= n; i++) {
			price[i] = Integer.parseInt(st.nextToken());
			if(memory[i] >= m && min > price[i])
				min = price[i];
		}
		
		int[] dp = new int[m + 1];
		Arrays.fill(dp, -1);
		dp[0] = 0;
		dp[m] = min == Integer.MAX_VALUE? -1: min;
		
		for(int i = 1; i <= n; i++) {
			if(memory[i] >= m)
				continue;
			for(int j = m; j >= memory[i]; j--) {
				if(dp[j] != -1 && j + memory[i] > m) {
					if(dp[m] == -1 || dp[m] > dp[j] + price[i])
						dp[m] = dp[j] + price[i];
				}
				
				if(dp[j] != -1 && dp[j - memory[i]] != -1) {
					dp[j] = Integer.min(dp[j], dp[j - memory[i]] + price[i]);
				} else if(dp[j - memory[i]] != -1){
					dp[j] = dp[j - memory[i]] + price[i];
				}
			}
		}
		
		System.out.println(dp[m]);
	}
}