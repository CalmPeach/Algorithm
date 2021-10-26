import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import javax.print.attribute.HashAttributeSet;

public class Test_1938 {
	
	static int n;
	static char[][] map;
	static int[] dr = {-1, 1, 0, 0, -1, -1, 1, 1};
	static int[] dc = {0, 0, -1, 1, -1, 1, -1, 1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		map = new char[n][];
		Pair[] starts = new Pair[3];
		int sIdx = 0;
		for(int i = 0; i < n; i++) {
			map[i] = br.readLine().toCharArray();
			for(int j = 0; j < n; j++) {
				if(map[i][j] == 'B') {
					starts[sIdx++] = new Pair(i, j);
					map[i][j] = '0';
				}
			}
		}
		
		// 0 세로 1 가로
		starts[1].d = starts[0].c == starts[1].c - 1? 1: 0;
		System.out.println(bfs(starts[1]));
	}
	
	public static int bfs(Pair start) {
		Queue<Pair> q = new LinkedList<Pair>();
		q.add(start);
		boolean[][][] visit = new boolean[2][n][n];
		visit[start.d][start.r][start.c] = true;
		
		int nr, nc, nd, i, ans = 0;
		while(!q.isEmpty()) {
			Pair now = q.poll();
			
			if(map[now.r][now.c] == 'E' && map[now.r + dr[now.d * 2]][now.c + dc[now.d * 2]] == 'E' && map[now.r + dr[now.d * 2 + 1]][now.c + dc[now.d * 2 + 1]] == 'E') {
				ans = now.s;
				break;
			}
			
			// 회전 가능여부 확인
			for(i = 0; i < 8; i++) {
				nr = now.r + dr[i];
				nc = now.c + dc[i];
				if(nr < 0 || nr >= n || nc < 0 || nc >= n || map[nr][nc] == '1')
					break;
			}
			
			nd = now.d ^ 1;
			if(i == 8 && !visit[nd][now.r][now.c]) { // 회전 가능하고 방문하지 않았다면 
				visit[nd][now.r][now.c] = true;
				q.add(new Pair(nd, now.r, now.c, now.s + 1));
			}
			
			// 상하 좌우 이동
			for(i = 0; i < 4; i++) {
				nr = now.r + dr[i];
				nc = now.c + dc[i];
				
				if(check(nr, nc) 
						&& check(nr + dr[now.d * 2], nc + dc[now.d * 2]) 
						&& check(nr + dr[now.d * 2 + 1], nc + dc[now.d * 2 + 1])
						&& !visit[now.d][nr][nc]) {
					visit[now.d][nr][nc] = true;
					q.add(new Pair(now.d, nr, nc, now.s + 1));
				}
			}
		}
		
		return ans;
	}
	
	// 이동 가능한지 여부 판단
	public static boolean check(int r, int c) {
		return r >= 0 && r < n && c >= 0 && c < n && map[r][c] != '1';
	}
	
	public static class Pair{
		int d; // 방향: 0 세로 1 가로
		int r; 
		int c;
		int s; // 이동 동작 횟수
		public Pair(int d, int r, int c, int s) {
			this.d = d;
			this.r = r;
			this.c = c;
			this.s = s;
		}
		public Pair(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
}