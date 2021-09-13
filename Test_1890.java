import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
 
public class Test_1890 {
	
	static int n;
	static int[][] board;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		board = new int[n][n];
		for(int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j = 0; j < n; j++)
				board[i][j] = Integer.parseInt(st.nextToken());
		}
		
		long[][] dp = new long[n][n];
		dp[0][0] = 1;
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				int[][] xy = {{board[i][j], 0}, {0, board[i][j]}};
				if(i == n - 1 && j == n - 1)
					continue;
				for(int k = 0; k < 2; k++) {
					int y = i + xy[k][0];
					int x = j + xy[k][1];
					if(y >= n || x >= n)
						continue;
					dp[y][x] += dp[i][j];
				}
			}
		}
		System.out.println(dp[n - 1][n - 1]);
	}
}
