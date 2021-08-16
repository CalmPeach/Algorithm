import java.io.*;
import java.util.*;

public class Test_15683{
	
	static int n, m;
	static int min;
	static ArrayList<Pair> cctvInfo;
	static int[] dr = {0, -1, 0, 1};
	static int[] dc = {-1, 0, 1, 0};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		int map[][] = new int[n][m];
		cctvInfo = new ArrayList<Pair>(); // cctv의 위치를 저장하는 HashMap
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] != 0 && map[i][j] != 6) {
					cctvInfo.add(new Pair(i, j));
				}
			}
		}
		
		min = Integer.MAX_VALUE;
		solution(map, 0);
		System.out.println(min);
	}
	
	public static void solution(int prev[][], int cctvIdx) {
		// 모든 cctv의 방향을 선택함
		if(cctvIdx == cctvInfo.size()) {
			int cnt = 0;
			for(int i = 0; i < prev.length; i++) {
				for(int j = 0; j < prev[i].length; j++)
					if(prev[i][j] == 0)
						cnt++;
			}
			if(cnt < min)
				min = cnt;
			return;
		}
		
		int next[][] = new int[n][m];
		Pair cctv = cctvInfo.get(cctvIdx);
		switch(prev[cctv.y][cctv.x]) {
			case 1:
				// 한쪽 방향만 감시
				for(int k = 0; k < 4; k++) {
					for(int i = 0; i < n; i++) {
						next[i] = Arrays.copyOf(prev[i], m);
					}
					watch(next, cctv.y, cctv.x, k);
					solution(next, cctvIdx + 1);
				}	
				break;
			case 2:
				// 두 방향을 감시 (서로 반대방향)
				for(int k = 0; k < 2; k++) {
					for(int i = 0; i < n; i++) {
						next[i] = Arrays.copyOf(prev[i], m);
					}
					watch(next, cctv.y, cctv.x, k);
					watch(next, cctv.y, cctv.x, k + 2);
					solution(next, cctvIdx + 1);
				}	
				break;
			case 3:
				// 두 방향을 감시 (직각방향)
				for(int k = 0; k < 4; k++) {
					for(int i = 0; i < n; i++) {
						next[i] = Arrays.copyOf(prev[i], m);
					}
					watch(next, cctv.y, cctv.x, k);
					watch(next, cctv.y, cctv.x, (k + 1) % 4);
					solution(next, cctvIdx + 1);
				}	
				break;
			case 4:
				// 세 방향을 감시
				for(int k = 0; k < 4; k++) {
					for(int i = 0; i < n; i++) {
						next[i] = Arrays.copyOf(prev[i], m);
					}
					watch(next, cctv.y, cctv.x, k);
					watch(next, cctv.y, cctv.x, (k + 1) % 4);
					watch(next, cctv.y, cctv.x, (k + 2) % 4);
					solution(next, cctvIdx + 1);
				}	
				break;
			case 5:
				// 네 방향을 감시
				for(int i = 0; i < n; i++) {
					next[i] = Arrays.copyOf(prev[i], m);
				}
				watch(next, cctv.y, cctv.x, 0);
				watch(next, cctv.y, cctv.x, 1);
				watch(next, cctv.y, cctv.x, 2);
				watch(next, cctv.y, cctv.x, 3);
				solution(next, cctvIdx + 1);
				break;
		}
		
	}
	
	// 방향에 따라 감시
	public static void watch(int[][] map, int i, int j, int d) {
		int nr = i;
		int nc = j;
		
		while(true) {
			nr += dr[d];
			nc += dc[d];
			
			if(nr < 0 || nr >= n || nc < 0 || nc >= m || map[nr][nc] == 6)
				break;
			
			if(map[nr][nc] == 0)
				map[nr][nc] = 7;
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