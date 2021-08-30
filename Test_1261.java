import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
 
public class Test_1261 {
	
	static int n;
	static int m;
	static int[][] map;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		m = Integer.parseInt(st.nextToken());
		n = Integer.parseInt(st.nextToken());
		map = new int[n][m];
		for(int i = 0; i < n; i++) {
			String input = br.readLine();
			for(int j = 0; j < m; j++)
				map[i][j] = input.charAt(j) - '0';
		}
		bfs();
	}
	
	public static void bfs() {
		Queue<Pair> q = new LinkedList<Pair>();
		q.add(new Pair(0, 0));
		int[][] visit = new int[n][m];
		visit[0][0] = 1;
		
		int[][] xy = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
		while(!q.isEmpty()) {
			Pair now = q.poll();
			
			for(int i = 0; i < 4; i++) {
				int y = now.y + xy[i][0];
				int x = now.x + xy[i][1];
				if(y < 0 || y >= n || x < 0 || x >= m)
					continue;
				if(map[y][x] == 0) { // 벽이 아니라면
					if(visit[y][x] == 0 || visit[y][x] > visit[now.y][now.x]) {
						visit[y][x] = visit[now.y][now.x];
						q.add(new Pair(y, x));
					}
				} else { // 벽이라면
					if(visit[y][x] == 0 || visit[y][x] > visit[now.y][now.x] + 1) {
						visit[y][x] = visit[now.y][now.x] + 1;
						q.add(new Pair(y, x));
					}
				}
			}
		}
		
		System.out.println(visit[n - 1][m - 1] - 1);
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