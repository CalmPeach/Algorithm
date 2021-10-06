import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 백준 다리 만들기 2
public class Test_17472 {

	static int n, m;
	static int[][] map;
	static int[][] dist;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new int[n][m];
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int areaNum = 2; // 구역 번호
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) {
				if(map[i][j] == 1) {
					divideArea(new Pair(i, j), areaNum++);
				}
			}
		}
		
		dist = new int[areaNum][areaNum];
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) {
				int aNum = map[i][j]; 
				if(map[i][j] != 0) {
					for(int d = 0; d < 4; d++) {
						findRoute(new Pair(i, j), d, aNum, 0);
					}
				}
			}
		}
		System.out.println(prime(areaNum));
	}
	
	// 모든 섬을 연결하는 가장 최솟값 찾기 : 섬의 개수
	public static int prime(int cnt) {
		PriorityQueue<Island> pq = new PriorityQueue<Island>();
		pq.add(new Island(2, 0));
		boolean[] visit = new boolean[cnt];
		int sum = 0;
		
		while(!pq.isEmpty()) {
			Island now = pq.poll();
			
			if(visit[now.num])
				continue;
			sum += now.dist;
			
			for(int i = 2; i < cnt; i++) {
				if(i == now.num || dist[now.num][i] <= 1)
					continue;
				if(!visit[i]) {
					pq.add(new Island(i, dist[now.num][i]));
				}
			}
			
			visit[now.num] = true;
		}
		
		for(int i = 2; i < cnt; i++) 
			if(!visit[i]) // 방문하지 못한 섬이 있다면 
				return -1;
		
		return sum;
	}
	
	// areaNum 섬에서 다른 섬으로 가는 다리 만들기 : 현재 위치, 이동 방향, 섬 이름, 다리 길이 -> 반복문으로 변환 
	public static void findRoute(Pair now, int dir, int areaNum, int len) {
		if(map[now.r][now.c] != 0 && map[now.r][now.c] != areaNum) { // 다른 섬이라면
			if(len - 1 == 1) // 다리 길이가 1이라면 
				return;
			
			int endAreaNum = map[now.r][now.c]; // 도착 섬
			if(dist[areaNum][endAreaNum] == 0 || dist[areaNum][endAreaNum] > len - 1)
				dist[areaNum][endAreaNum] = len - 1;
			return;
		}
		
		int nr = now.r + dr[dir];
		int nc = now.c + dc[dir];
		if(nr < 0 || nr >= n || nc < 0 || nc >= m || map[nr][nc] == areaNum)
			return;
		findRoute(new Pair(nr, nc), dir, areaNum, len + 1);
	}
	
	// 구역 나누기 : 현재 위치, 구역 번호
	public static void divideArea(Pair now, int areaNum) {
		map[now.r][now.c] = areaNum;
		
		for(int i = 0; i < 4; i++) {
			int nr = now.r + dr[i];
			int nc = now.c + dc[i];
			
			if(nr < 0 || nr >= n || nc < 0 || nc >= m || map[nr][nc] != 1)
				continue;
			
			divideArea(new Pair(nr, nc), areaNum);
		}
	}
	
	public static class Island implements Comparable<Island>{
		int num;
		int dist;
		
		public Island(int num, int dist) {
			this.num = num;
			this.dist = dist;
		}
		
		@Override
		public int compareTo(Island o) {
			return Integer.compare(this.dist, o.dist);
		}
	}
	
	public static class Pair{
		int r;
		int c;
		public Pair(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
}
