import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
 
public class Test_1194 {
	
	static char[][] map;
	static int n;
	static int m;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new char[n][m];
		Pair start = null;
		for(int i = 0; i < n; i++) {
			String input = br.readLine();
			for(int j = 0; j < m; j++) {
				map[i][j] = input.charAt(j);
				if(map[i][j] == '0') {
					int[] keys = {0, 0, 0, 0, 0, 0};
					start = new Pair(keys, i, j);
				}
			}
		}
		int result = bfs(start);
		System.out.println(result);
	}
	
	public static int bfs(Pair start) {
		Queue<Pair> q = new LinkedList<Pair>();
		q.add(start);
		int[][][][][][][][] visit = new int[2][2][2][2][2][2][n][m]; // 해당하는 문에 열쇠가 있는가, n * m
		visit[0][0][0][0][0][0][start.y][start.x] = 1;
		
		int ans = -1;
		int[][] xy = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
		while(!q.isEmpty()) {
			Pair now = q.poll();
			if(map[now.y][now.x] == '1') {
				ans = visit[now.keys[0]][now.keys[1]][now.keys[2]][now.keys[3]][now.keys[4]][now.keys[5]][now.y][now.x];
				break;
			}
			for(int i = 0; i < 4; i++) {
				int y = xy[i][0] + now.y;
				int x = xy[i][1] + now.x;
				if(y < 0 || y >= n || x < 0 || x >= m)
					continue;
				if(map[y][x] == '.' || map[y][x] == '1' || map[y][x] == '0') { // 갈 수 있는 곳이라면
					if(visit[now.keys[0]][now.keys[1]][now.keys[2]][now.keys[3]][now.keys[4]][now.keys[5]][y][x] != 0) // 이미 방문했다면
						continue;
					Pair next = new Pair(now.keys, y, x);
					q.add(next);
					visit[now.keys[0]][now.keys[1]][now.keys[2]][now.keys[3]][now.keys[4]][now.keys[5]][y][x] = visit[now.keys[0]][now.keys[1]][now.keys[2]][now.keys[3]][now.keys[4]][now.keys[5]][now.y][now.x] + 1;
				} else if(map[y][x] == '#') { // 벽이라면 
					continue;
				} else if(map[y][x] >= 'a' && map[y][x] <= 'f') { // 열쇠가 있는 곳이라면 
					int idx = map[y][x] - 97; // 열쇠에 해당하는 index
					int[] nums = new int[6]; // key 복사 
					for(int j = 0; j < 6; j++)
						nums[j] = now.keys[j];
					nums[idx] = 1; // key 소유
					if(visit[nums[0]][nums[1]][nums[2]][nums[3]][nums[4]][nums[5]][y][x] != 0) // 이미 방문했다면
						continue;
					else { // 방문하지 않았다면 
						Pair next = new Pair(nums, y, x);
						q.add(next);
						visit[nums[0]][nums[1]][nums[2]][nums[3]][nums[4]][nums[5]][y][x] = visit[now.keys[0]][now.keys[1]][now.keys[2]][now.keys[3]][now.keys[4]][now.keys[5]][now.y][now.x] + 1;
					}
					nums = null;
				} else if(map[y][x] >= 'A' && map[y][x] <= 'F') { // 문이 있는 곳이라면
					if(visit[now.keys[0]][now.keys[1]][now.keys[2]][now.keys[3]][now.keys[4]][now.keys[5]][y][x] != 0) // 방문했다면 
						continue;
					int idx = map[y][x] - 65; // 문에 해당하는 열쇠의 index
					if(now.keys[idx] == 1) { // 키를 가지고 있다면
						Pair next = new Pair(now.keys, y, x);
						q.add(next);
						visit[now.keys[0]][now.keys[1]][now.keys[2]][now.keys[3]][now.keys[4]][now.keys[5]][y][x] = visit[now.keys[0]][now.keys[1]][now.keys[2]][now.keys[3]][now.keys[4]][now.keys[5]][now.y][now.x] + 1;
					} else { // 키가 없다면
						continue;
					}
				}
			}
		}
		return ans == -1? -1: ans - 1;
	}
	
	public static class Pair{
		int[] keys = new int[8];
		int y;
		int x;
		public Pair(int[] keys, int y, int x) {
			this.keys = keys;
			this.y = y;
			this.x = x;
		}
	}
}