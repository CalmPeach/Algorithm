import java.io.*;
import java.util.*;

public class Test_2146 {
	
	static int n;
	static int[][] map;
	static int[][] xy = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	
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
		
		int v = 1;
		// 섬 나누기
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				if(map[i][j] == 1) {
					v++;
					bfsLabel(new Pair(i, j), v);
				}
			}
		}
		
		
		int min = Integer.MAX_VALUE;
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				if(map[i][j] != 0) {
					int result = bfs(new Pair(i, j), map[i][j]); // 섬에서 가장 가까운 다른 섬 찾기
					if(result != -1 && min > result)
						min = result;
				}
			}
		}
		System.out.println(min);
	}
	
	// s 위치에서 가장 가까운 다른 섬 찾기: s 위치, v 섬 번호
	public static int bfs(Pair s, int v) {
		Queue<Pair> q = new LinkedList<Pair>();
		boolean[][] visit = new boolean[n][n];
		q.add(s);
		visit[s.y][s.x] = true;
		
		int cnt = 0;
		int flag = 0;
		loop:
		while(!q.isEmpty()) {
			int size = q.size();
			for(int j = 0; j < size; j++) {
				Pair p = q.poll();
				if(map[p.y][p.x] != 0 && map[p.y][p.x] != v) { // 가장 가까운 다른 섬을 찾았다면 
					flag = 1; 
					break loop;
				}
				
				for(int i = 0; i < 4; i++) {
					int y = xy[i][0] + p.y;
					int x = xy[i][1] + p.x;
					if(y < 0 || y >= n || x < 0 || x >= n)
						continue;
					if(map[y][x] == v)
						continue;
					if(visit[y][x] == true)
						continue;
					q.add(new Pair(y, x));
					visit[y][x] = true;
				}
			}
			cnt++;
		}
		return flag == 0? -1: cnt - 1;
	}
	
	public static void bfsLabel(Pair s, int v) {
		Queue<Pair> q = new LinkedList<Pair>();
		q.add(s);
		map[s.y][s.x] = v;
		
		while(!q.isEmpty()) {
			Pair p = q.poll();
			for(int i = 0; i < 4; i++) {
				int y = xy[i][0] + p.y;
				int x = xy[i][1] + p.x;
				if(y < 0 || y >= n || x < 0 || x >= n)
					continue;
				if(map[y][x] != 1)
					continue;
				map[y][x] = v;
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