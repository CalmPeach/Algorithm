import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.*;
 
public class Test_1010 {
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int tc = Integer.parseInt(br.readLine());
		
		for(int t = 0; t < tc; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			int[][] dp = new int[n + 1][m + 1];
			for(int i = 1; i <= m; i++)
				dp[1][i] = i;
			for(int i = 2; i <= n; i++) {
				for(int j = 1; j <= m; j++) {
					for(int k = 1; k < j; k++) {
						dp[i][j] += dp[i - 1][k];
					}
				}
			}
			System.out.println(dp[n][m]);
		}
	}
}

/*
 * public class Main {
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int tc = Integer.parseInt(br.readLine());
		
		for(int t = 0; t < tc; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			
			BigInteger nf = new BigInteger("1");
			for(int i = 1; i <= m; i++) {
				nf = nf.multiply(new BigInteger(Integer.toString(i)));
			}
			BigInteger rf = new BigInteger("1");
			for(int i = 1; i <= n; i++) {
				rf = rf.multiply(new BigInteger(Integer.toString(i)));
			}
			BigInteger rnf = new BigInteger("1");
			for(int i = 1; i <= m - n; i++) {
				rnf = rnf.multiply(new BigInteger(Integer.toString(i)));
			}
			System.out.println(nf.divide(rf.multiply(rnf)));
		}
	}
}
*/