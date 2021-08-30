import java.io.*;
import java.util.*;

public class Test_1937 {
	
	static int n;
	static int[][] xy = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	static int[][] map;
	static int[][] dp;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		map = new int[n][n];
		for(int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int max = -1;
		dp = new int[n][n];
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				if(dp[i][j] == 0) { // 탐색하지 않았다면 
					int cnt = dfs(new Pair(i, j));
					if(max < cnt)
						max = cnt;
				}
			}
		}
		System.out.println(max);
	}
	
	public static int dfs(Pair now) {
		if(dp[now.y][now.x] != 0)
			return dp[now.y][now.x];
		
		dp[now.y][now.x] = 1;
		
		for(int i = 0; i < 4; i++) {
			int y = now.y + xy[i][0];
			int x = now.x + xy[i][1];
			if(y < 0 || y >= n || x < 0 || x >= n)
				continue;
			if(map[y][x] <= map[now.y][now.x])
				continue;
			int cnt = dfs(new Pair(y, x));
			dp[now.y][now.x] = Math.max(cnt + 1, dp[now.y][now.x]);
		}
		return dp[now.y][now.x];
	}
	
	public static class Pair{
		int y;
		int x;
		public Pair(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}
}