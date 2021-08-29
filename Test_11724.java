import java.io.*;
import java.util.*;
 
public class Test_11724 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int[][] link = new int[n][n];
		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int y = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			link[y - 1][x - 1] = 1;
			link[x - 1][y - 1] = 1;
		}
		
		int cnt = 0;
		int[] visit = new int[n];
		for(int i = 0; i < n; i++) {
			if(visit[i] == 0) {
				cnt++;
				bfs(link, visit, i, n);
			}
		}
		System.out.println(cnt);
	}
	
	public static void bfs(int[][] link, int[] visit, int s, int n) {
		Queue<Integer> q = new LinkedList<Integer>();
		q.add(s);
		visit[s] = 1;
		
		while(!q.isEmpty()) {
			int num = q.poll();
			for(int i = 0; i < n; i++) {
				if(link[num][i] == 1 && visit[i] == 0) {
					visit[i] = 1;
					q.add(i);
				}
			}
		}
	}
}