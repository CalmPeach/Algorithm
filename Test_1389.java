import java.io.*;
import java.util.*;
 
public class Test_1389{
	
	public static int[][] visit;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int[][] network = new int[n][n];
		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int f1 = Integer.parseInt(st.nextToken()) - 1;
			int f2 = Integer.parseInt(st.nextToken()) - 1;
			network[f1][f2] = 1;
			network[f2][f1] = 1;
		}
		
		visit = new int[n][n];
		int idx = 0;
		int min = Integer.MAX_VALUE;
		for(int i = 0; i < n; i++) {
			int sum = bfs(network, i, n);
			if(sum < min) {
				idx = i;
				min = sum;
			}
		}
		System.out.println(idx + 1);
	}
	
	public static int bfs(int[][] network, int s, int n) {
		Queue<Integer> q = new LinkedList<Integer>();
		q.add(s);
		int cnt = n - 1;
		visit[s][s] = 0;
		
		while(!q.isEmpty()) {
			int f = q.poll();
			if(cnt == 0)
				break;
			for(int i = 0; i < n; i++) {
				if(i == s)
					continue;
				if(network[f][i] == 1 && visit[s][i] == 0) {
					cnt--;
					visit[s][i] = visit[s][f] + 1;
					q.add(i);
				}
			}
		}
		
		int sum = 0;
		for(int i = 0; i < n; i++) {
			sum += visit[s][i];
		}
		return sum;
	}
}