import java.io.*;
import java.util.*;

public class Test_1113 {
	
	static int[][] pool;
	static int[][] xy = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	static int n;
	static int m;
	
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	n = Integer.parseInt(st.nextToken());
    	m = Integer.parseInt(st.nextToken());
    	pool = new int[n][m];
    	int maxHeight = 0;
    	for(int i = 0; i < n; i++) {
    		String input = br.readLine();
    		for(int j = 0; j < m; j++) {
    			pool[i][j] = input.charAt(j) - '0';
    			maxHeight = maxHeight < pool[i][j]? pool[i][j]: maxHeight;
    		}
    	}
    	
    	int sum = 0;
    	for(int h = 2; h <= maxHeight; h++) {
    		for(int i = 0; i < n; i++) {
    			for(int j = 0; j < m; j++) {
    				if(pool[i][j] != -1 && pool[i][j] < h) {
    					sum += bfs(new Pair(i, j), h);
    				}
    			}
    		}
    	}
    	System.out.println(sum);
    }
    
    public static int bfs(Pair s, int h) {
    	Queue<Pair> q = new LinkedList<Main.Pair>();
    	q.add(s);
    	pool[s.y][s.x] += 1;
    	ArrayList<Pair> smallPool = new ArrayList<Pair>();
    	smallPool.add(s);
    	boolean isFlood = false;
    	
    	loop:
    	while(!q.isEmpty()) {
    		Pair now = q.poll();
    		
    		for(int i = 0; i < 4; i++) {
    			int y = xy[i][0] + now.y;
    			int x = xy[i][1] + now.x;
    			
    			if(y < 0 || y >= n || x < 0 || x >= m || pool[y][x] == -1) {
    				isFlood = true;
    				break loop;
    			}
    			
    			if(pool[y][x] >= h)
    				continue;
    			q.add(new Pair(y, x));
    			smallPool.add(new Pair(y, x));
    			pool[y][x] += 1;
    		}
    	}
    	
    	if(isFlood) { // 홍수라면 
    		for(int j = 0; j < smallPool.size(); j++) {
				Pair p = smallPool.get(j);
				pool[p.y][p.x] = -1;
			}
    		return 0;
    	} 
    	return smallPool.size();
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

