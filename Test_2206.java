import java.io.*;
import java.util.*;
 
public class Test_2206 {
	
	static int[][] xy = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int[][] map = new int[n][m];
		for(int i = 0; i < n; i++) {
			String input = br.readLine();
			for(int j = 0; j < m; j++) {
				map[i][j] = input.charAt(j) - '0';
			}
		}
		
		int result = bfs(map, n, m);
		System.out.println(result);
	}
	
	public static int bfs(int[][] map, int n, int m) {
		Queue<Pair> q = new LinkedList<Pair>();
		q.add(new Pair(0, 0, 1));
		int[][][] visit = new int[n][m][2]; // [n][m] 위치, [2] 부쉈는지 여부
		visit[0][0][1] = 1;
		Pair now = null;
		
		while(!q.isEmpty()) {
			now = q.poll();
			if(now.y == n - 1 && now.x == m - 1) // 이동 완료
				break;
			
			for(int i = 0; i < 4; i++) {
				int y = xy[i][0] + now.y;
				int x = xy[i][1] + now.x;
				if(y < 0 || y >= n || x < 0 || x >= m)
					continue;
				
				if(map[y][x] == 1) { // 벽이라면 
					if(now.drill != 1 || visit[y][x][0] != 0) // 벽을 1번 부쉈거나 이미 방문했다면 
						continue;
					visit[y][x][0] = visit[now.y][now.x][now.drill] + 1;
					q.add(new Pair(y, x, 0));
				} else { // 벽이 아니라면
					if(visit[y][x][now.drill] != 0)
						continue;
					visit[y][x][now.drill] = visit[now.y][now.x][now.drill] + 1;
					q.add(new Pair(y, x, now.drill));
				}
			}
		}
		return visit[n - 1][m - 1][now.drill] == 0? -1: visit[n - 1][m - 1][now.drill];
	}
	
	public static class Pair{
		int y;
		int x;
		int drill; // 1:벽 안 부숨 0: 벽 부숨
		public Pair(int y, int x, int drill) {
			this.y = y;
			this.x = x;
			this.drill = drill;
		}
	}
}