import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
 
public class Test_11052 {
	
	static int n;
	static int[] p;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		p = new int[n + 1];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 1; i <= n; i++) {
			p[i] = Integer.parseInt(st.nextToken());
		}
		int[] dp = new int[n + 1];
		dp[1] = p[1];
		for(int i = 2; i <= n; i++) {
			int j = i - 1;
			int k = 1;
			int max = p[i];
			while(k <= j) {
				if(dp[j] + dp[k] > max)
					max = dp[j] + dp[k];
				j--;
				k++;
			}
			dp[i] = max;
		}
		System.out.println(dp[n]);
	}
}
