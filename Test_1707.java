import java.io.*;
import java.util.*;

public class Test_1707 {
	
	public static int v;
	public static int e;
	public static ArrayList<ArrayList<Integer>> map;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int tc = Integer.parseInt(br.readLine());
		for(int t = 0; t < tc; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			v = Integer.parseInt(st.nextToken());
			e = Integer.parseInt(st.nextToken());
			map = new ArrayList<ArrayList<Integer>>();
			for(int i = 0; i < v; i++)
				map.add(new ArrayList<Integer>());
			
			for(int i = 0; i < e; i++) {
				st = new StringTokenizer(br.readLine());
				int v1 = Integer.parseInt(st.nextToken()) - 1;
				int v2 = Integer.parseInt(st.nextToken()) - 1;
				map.get(v1).add(v2);
				map.get(v2).add(v1);
			}
			if(bfs()) {
				System.out.println("YES");
			} else {
				System.out.println("NO");
			}
		}
	}
	
	public static boolean bfs() {
		Queue<Integer> q = new LinkedList<Integer>();
		int[] visit = new int[v]; // 집합 나누기 
		boolean flag = true; // 이분그래프 여부
		
		for(int i = 0; i < v; i++) {
			if(visit[i] == 0) { // 집합이 안정해져있다면
				q.add(i);
				visit[i] = 1;
				
				while(!q.isEmpty()) {
					int now = q.poll();
					
					for(int next : map.get(now)) {
						if(visit[next] == 0) { // now와 연결되어 있는 원소에 집합이 정해지지 않았다면
							visit[next] = visit[now] * -1; // 현재 집합과 반대집합 
							q.add(next);
						} else if(visit[next] == visit[now]) { // 연결되어 있는데 같은 집합이라면 
							flag = false;
							break;
						}
					}
				}
			}
		}
		
		return flag; 
	}
}