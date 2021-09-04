import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
 
public class Test_16956 {
	
	static char[][] map;
	static int n;
	static int m;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new char[n][m];
		for(int i = 0; i < n; i++) {
			String input = br.readLine();
			for(int j = 0; j < m; j++) {
				map[i][j] = input.charAt(j);
				if(map[i][j] == '.')
					map[i][j] = 'D';
			}
		}
		
		boolean result = false;
		loop:
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) {
				if(map[i][j] == 'W') {
					result = check(new Pair(i, j));
					if(result == true) {
						break loop;
					}
				}
			}
		}
		if(result) {
			System.out.println(0);
		} else {
			System.out.println(1);
			for(int i = 0; i < n; i++) {
				for(int j = 0; j < m; j++)
					System.out.print(map[i][j]);
				System.out.println();
			}
		}
	}
	
	// 늑대와 인접한 곳에 양이 있는지 확인
	public static boolean check(Pair p) {
		int[][] xy = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
		for(int i = 0; i < 4; i++) {
			int y = p.y + xy[i][0];
			int x = p.x + xy[i][1];
			if(y < 0 || y >= n || x < 0 || x >= m)
				continue;
			if(map[y][x] == 'S') // 인접한 곳에 양이 있다면
				return true;
		}
		return false;
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