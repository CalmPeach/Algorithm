import java.io.*;
import java.util.*;
 
public class Test_2468 {
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[][] map = new int[n][n];
		Set<Integer> height = new HashSet<Integer>(); // 높이 
		height.add(0);
		for(int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(!height.contains(map[i][j])) {
					height.add(map[i][j]);
				}
			}
		}
		
		int max = 0;
		for(int h : height) {
			int[][] mapCopy = new int[n][n];
			for(int i = 0; i < n; i++) {
				for(int j = 0; j < n; j++) {
					if(map[i][j] <= h) // h이하는 물에 잠김
						mapCopy[i][j] = -1;
				}
			}
			
			int cnt = 0; // 영역의 수 
			for(int i = 0; i < n; i++) {
				for(int j = 0; j < n; j++) {
					if(mapCopy[i][j] == 0) {
						cnt++;
						bfs(mapCopy, new Pair(i, j), n);
					}
				}
			}
			if(cnt > max)
				max = cnt;
		}
		System.out.println(max);
	}
	
	public static void bfs(int[][] mapCopy, Pair s, int n) {
		Queue<Pair> q = new LinkedList<Pair>();
		q.add(s);
		mapCopy[s.y][s.x] = 1;
		
		while(!q.isEmpty()) {
			Pair p = q.poll();
			int[][] xy = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
			for(int i = 0; i < 4; i++) {
				int y = p.y + xy[i][0];
				int x = p.x + xy[i][1];
				if(y < 0 || y >= n || x < 0 || x >= n)
					continue;
				if(mapCopy[y][x] == -1 || mapCopy[y][x] == 1)
					continue;
				mapCopy[y][x] = 1;
				q.add(new Pair(y, x));
			}
		}
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