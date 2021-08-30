import java.io.*;
import java.util.*;

public class Test_1987 {
	
	public static int r;
	public static int c;
	public static int max;
	public static char[][] map;
	public static int[][] xy = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		map = new char[r][c];
		for(int i = 0; i < r; i++) {
			String input = br.readLine();
			for(int j = 0; j < c; j++) {
				map[i][j] = input.charAt(j);
			}
		}
		
		max = Integer.MIN_VALUE;
		dfs(new boolean[26], new Pair(0, 0), 0);
		System.out.println(max);
	}
	
	public static void dfs(boolean[] isCheck, Pair now, int cnt) {
		isCheck[map[now.y][now.x] - 'A'] = true;
		cnt++;
		
		for(int i = 0; i < 4; i++) {
			int y = xy[i][0] + now.y;
			int x = xy[i][1] + now.x;
			if(y < 0 || y >= r || x < 0 || x >= c) 
				continue;
			if(isCheck[map[y][x] - 'A'])
				continue;
			
			dfs(isCheck, new Pair(y, x), cnt);
			isCheck[map[y][x] - 'A'] = false;
		}
		
		if(max < cnt) // 어디가 끝인지 알 수 없다 매번 확인
			max = cnt;
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