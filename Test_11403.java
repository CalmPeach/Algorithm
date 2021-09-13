import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
 
public class Test_11403 {
	
	static int n;
	static int[][] map;
	static int[][] answer;
	static boolean[] visit;
	
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
		
		for(int i = 0; i < n; i++) {
			visit = new boolean[n];
			for(int j = 0; j < n; j++) {
				if(map[i][j] == 1) {
					bfs(i, j);
				}
			}
		}
		
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	public static void bfs(int first, int second) {
		Queue<Integer> q = new LinkedList<Integer>();
		q.add(second);
		visit[second] = true;
		
		while(!q.isEmpty()) {
			int now = q.poll();
			for(int i = 0; i < n; i++) {
				if(map[now][i] == 1 && visit[i] == false) {
					visit[i] = true;
					map[first][i] = 1;
					q.add(i);
				}
			}
		}
	}
}
