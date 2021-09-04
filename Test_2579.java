import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
 
public class Test_2579 {
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[] stairs = new int[n + 1];
		for(int i = 1; i < n + 1; i++)
			stairs[i] = Integer.parseInt(br.readLine());
		int[] dp = new int[n + 1];
		dp[1] = stairs[1];
		if(n >= 2)
			dp[2] = stairs[1] + stairs[2];
		
		for(int i = 3; i < n + 1; i++) {
			dp[i] = Integer.max(dp[i - 2] + stairs[i], dp[i - 3] + stairs[i - 1] + stairs[i]);
		}
		System.out.println(dp[n]);
	}
}
