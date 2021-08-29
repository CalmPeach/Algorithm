import java.io.*;
import java.util.*;
 
public class Test_2178 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	int n = Integer.parseInt(st.nextToken());
    	int m = Integer.parseInt(st.nextToken());
    	int[][] map = new int[n][m];
    	for(int i = 0; i < n; i++) {
    		String input = br.readLine();
    		for(int j = 0; j < m; j++) 
    			map[i][j] = input.charAt(j) - '0';
    	}
    	
    	System.out.println(bfs(n, m, map));
	}
	
	public static int bfs(int n, int m, int [][] map) {
		Queue<Pair> q = new LinkedList<Pair>();
		q.add(new Pair(0, 0));
		int[][] visit = new int[n][m];
		visit[0][0] = 1;
		
		while(!q.isEmpty()) {
			Pair p = q.poll();
			
			if(p.y == n - 1 && p.x == m - 1)
				break;
			
			int[][] xy = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
			for(int i = 0; i < 4; i++) {
				int y = p.y + xy[i][0];
				int x = p.x + xy[i][1];
				if(y < 0 || y >= n || x < 0 || x >= m)
					continue;
				if(visit[y][x] != 0 || map[y][x] == 0)
					continue;
				visit[y][x] = visit[p.y][p.x] + 1;
				q.add(new Pair(y, x));
			}
		}
		return visit[n - 1][m - 1];
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