import java.util.Scanner;

public class Test_9095 {	
	
	static int cnt;
	static int N;
	
	public static void main(String args[]) {
		Scanner scan = new Scanner(System.in);
		
		int T = scan.nextInt();
		for(int i = 0; i < T; i++) {
			N = scan.nextInt();
		
			int items[] = new int[] {1, 2, 3};
			int bucket[] =  new int[N];
			
			cnt = 0;
			solution(items, bucket, bucket.length);
			System.out.println(cnt);
		}
	}
	
	static void solution(int items[], int bucket[], int k) {
		
		if(k == 0 || k != bucket.length) {
			int sum = 0;
			for(int i = 0; i < bucket.length - k; i++) {
				sum += items[bucket[i]];
			}
			if(sum == N) {
				cnt++;
				return;
			}else if(k == 0) {
				return;
			}
		}
		
		int lastIndex = bucket.length - k - 1;

		for(int i = 0; i < items.length; i++) {
			bucket[lastIndex + 1] = i;
			solution(items, bucket, k - 1);
		}
	}
	
}

/*
// DP
import java.io.*;
import java.util.*;

public class Test_11726 {
	
	static int n;
	static int[] dp;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int tc = Integer.parseInt(br.readLine());
		for(int t = 0; t < tc; t++) {
			n = Integer.parseInt(br.readLine());
			dp = new int[n + 1];
			int[] init = {1, 2, 4};
			int range = n > 3? 3: n;
			for(int i = 1; i <= range; i++)
				dp[i] = init[i - 1];
			
			for(int i = 4; i <= n; i++) {
				dp[i] = dp[i - 3] + dp[i - 2] + dp[i - 1];
			}
			System.out.println(dp[n]);
		}
	}
}
*/