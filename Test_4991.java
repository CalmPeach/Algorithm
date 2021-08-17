import java.io.*;
import java.util.*;

public class Test_4991 {
	
	static int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	static int w, h;
	static int[][] map;
	static ArrayList<Pair> dirt;
	static int min;
	
	public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
    	while(true) {
	    	StringTokenizer st = new StringTokenizer(br.readLine());
	    	w = Integer.parseInt(st.nextToken());
	    	h = Integer.parseInt(st.nextToken());
	    	if(w == 0 && h == 0)
	    		break;
	    	map = new int[h][w];
	    	dirt = new ArrayList<Pair>(); // 더러운 칻과 청소기의 위치를 저장하는 ArrayList
	    	for(int i = 0; i < h; i++) {
	    		String input = br.readLine();
	    		for(int j = 0; j < w; j++) {
	    			map[i][j] = input.charAt(j);
	    			if(map[i][j] == '*') {
	    				dirt.add(new Pair(i, j));
	    			} else if(map[i][j] == 'o') {
	    				dirt.add(0, new Pair(i, j));
	    			}
	    		}
	    	}
	    	
	    	int[][] distance = new int[dirt.size()][dirt.size()]; // 더러운 칸들 사이의 거리, 청소기와 더러운 칸들 사이의 거리를 저장하는 배별
	    	boolean isDirt = false;
	    	loop:
	    	for(int i = 0; i < dirt.size() - 1; i++) {
	    		int[][] d = bfs(dirt.get(i)); // i번 칸에서 다른 모든 칸 사이의 거리를 저장한 배열
	    		for(int j = i + 1; j < dirt.size(); j++) {
	    			Pair p = dirt.get(j);
	    			distance[i][j] = d[p.y][p.x] - 1; // i번 칸과 j번 칸 사이의 거리를 저장
	    			distance[j][i] = d[p.y][p.x] - 1;
	    			if(distance[i][j] == -1) { // 더러운 칸을 방문할 수 없다.
	    				isDirt = true;
	    				break loop;
	    			}
	    		}
	    	}
	    	
	    	if(isDirt) { // 방문할 수 없는 더러운 칸이 존재한다면
	    		System.out.println(-1);
	    		continue;
	    	}
	    	
	    	min = Integer.MAX_VALUE;
	    	permutation(distance, new int[dirt.size() - 1], dirt.size() - 1);
	    	System.out.println(min);
    	}
	}
	
	// 칸들을 방문하는 순서를 정한 순열
	public static void permutation(int[][] dist, int[] bucket, int k) {
		if(k == 0) {
			int sum = 0;
			int idx = 0;
			for(int i = 0; i < bucket.length; i++) {
				sum += dist[idx][bucket[i]];
				idx = bucket[i];
			}
			if(sum < min)
				min = sum;
			return;
		}
		
		int lastIdx = bucket.length - k - 1;
		for(int i = 1; i < dirt.size(); i++) {
			int flag = 0;
			for(int j = 0; j <= lastIdx; j++) {
				if(bucket[j] == i) {
					flag = 1;
					break;
				}
			}
			if(flag == 1)
				continue;
			bucket[lastIdx + 1] = i;
			permutation(dist, bucket, k - 1);
		}
	}
	
	public static int[][] bfs(Pair start) {
		Queue<Pair> q = new LinkedList<Pair>();
		q.add(start);
		int[][] visit = new int[h][w];
		visit[start.y][start.x] = 1;
		
		while(!q.isEmpty()) {
			Pair now = q.poll();
			
			for(int i = 0; i < 4; i++) {
				int y = now.y + dir[i][0];
				int x = now.x + dir[i][1];
				if(y < 0 || y >= h || x < 0 || x >= w)
					continue;
				if(map[y][x] == 'x' || visit[y][x] != 0)
					continue;
				visit[y][x] += visit[now.y][now.x] + 1;
				q.add(new Pair(y, x));
			}
		}
		return visit;
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