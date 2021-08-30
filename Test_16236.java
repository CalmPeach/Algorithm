import java.util.*;
import java.io.*;

public class Test_16236 {
	
	static int n;
	static int[][] map;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		map = new int[n][n];
		StringTokenizer st;
		Pair start = null; // 시작위치
		int[] fcnt = new int[7]; // 물고기 크기에 따라 남아있는 물고기 수
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 9) {
					start = new Pair(0, i, j);
					map[i][j] = 0;
				} else if(map[i][j] >= 1 && map[i][j] <= 6) {
					fcnt[map[i][j]]++;
				}
			}
		}
		bfs(start, fcnt);
	}
	
	public static void bfs(Pair start, int[] fcnt) {
		int size = 2; // 상어 크기
		int sizeUp = 2; // 사이즈 업
		int[][] xy = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
		// 가까운 물고기 순으로 가까운 물고기, 가장 위에 있는 물고기, 가장 왼쪽에 있는 물고기 
		PriorityQueue<Pair> q = new PriorityQueue<Pair>(
				new Comparator<Pair>() {
					@Override
					public int compare(Pair p1, Pair p2) {
						if(p1.step == p2.step) {
							if(p1.y == p2.y) {
								return Integer.compare(p1.x, p2.x);
							}
							return Integer.compare(p1.y, p2.y);
						}
						return Integer.compare(p1.step, p2.step);
					}
				});
		q.add(start);
		boolean[][] visit = new boolean[n][n];
		visit[start.y][start.x] = true;
		int ans = 0;
		
		while(!q.isEmpty()) {
			Pair now = q.poll();
			if(map[now.y][now.x] != 0 && map[now.y][now.x] < size) {
				fcnt[size - 1]--;
				ans = now.step; // ans 갱신
				
				if(size < 7) { // 상어 크기 조절
					sizeUp--;
					if(sizeUp == 0) {
						size++;
						sizeUp = size;
						fcnt[size - 1] += fcnt[size - 2];
					}
				}
				
				if(fcnt[size - 1] == 0) // 더이상 먹을 물고기가 없다면
					break;
				
				map[now.y][now.x] = 0; // 물고기 먹음
				
				// 초기화
				q.clear();
				visit = new boolean[n][n];
				q.add(now);
				visit[now.y][now.x] = true;
			}
			
			for(int i = 0; i < 4; i++) {
				int y = now.y + xy[i][0];
				int x = now.x + xy[i][1];
				if(y < 0 || y >= n || x < 0 || x >= n)
					continue;
				if(map[y][x] > size || visit[y][x])
					continue;
				q.add(new Pair(now.step + 1, y, x));
				visit[y][x] = true;
			}
		}
		
		System.out.println(ans);
	}
	
	public static class Pair{
		int step;
		int y;
		int x;
		public Pair(int step, int y, int x) {
			this.step = step;
			this.y = y;
			this.x = x;
		}
	}
}