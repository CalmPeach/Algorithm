import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
 
public class Test_3055 {
	
	static int n, m;
	static char[][] map;
	
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new char[n][m];
        ArrayList<Pair> pairs = new ArrayList<Pair>();
        for(int i = 0; i < n; i++) {
        	String input = br.readLine();
        	for(int j = 0; j < m; j++) {
        		map[i][j] = input.charAt(j);
        		if(map[i][j] == '*') // 물
        			pairs.add(new Pair(i, j));
        		else if(map[i][j] == 'S') // 고슴도치 위치
        			pairs.add(0, new Pair(i, j));
        	}
        }
        bfs(pairs);
    }
    
    public static void bfs(ArrayList<Pair> pairs) {
    	int[][] xy = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    	Queue<Pair> q1 = new LinkedList<Pair>(); // 물 위치
    	for(int i = 1; i < pairs.size(); i++) {
    		q1.add(pairs.get(i));
    	}
    	Queue<Pair> q2 = new LinkedList<Pair>(); // 고슴도치 위치
    	q2.add(pairs.get(0));
    	int ans = -1;
    	int sec = 0;
    	
    	loop:
    	while(!q2.isEmpty()) { // 고슴도치가 존재하는 동안
    		// 물이 있는 칸과 인접해있는 비어있는 칸 물 채우기 
    		int size = q1.size();
    		for(int s = 0; s < size; s++) {
    			Pair now = q1.poll();
    			
    			for(int i = 0; i < 4; i++) {
    				int y = now.y + xy[i][0];
    				int x = now.x + xy[i][1];
    				if(y < 0 || y >= n || x < 0 || x >= m)
    					continue;
    				if(map[y][x] == 'X' || map[y][x] == '*' || map[y][x] == 'D')
    					continue;
    				map[y][x] = map[now.y][now.x];
    				q1.add(new Pair(y, x));
    			}
    		}
    		
    		// 고슴도치 이동
    		size = q2.size();
    		for(int s = 0; s < size; s++) {
    			Pair now = q2.poll();
    			
    			for(int i = 0; i < 4; i++) {
    				int y = now.y + xy[i][0];
    				int x = now.x + xy[i][1];
    				if(y < 0 || y >= n || x < 0 || x >= m)
    					continue;
    				if(map[y][x] == 'X' || map[y][x] == '*' || map[y][x] == 'S')
    					continue;
    				if(map[y][x] == 'D') { // 비버 굴 도착 
    					ans = sec + 1;
    					break loop;
    				}
    				map[y][x] = map[now.y][now.x];
    				q2.add(new Pair(y, x));
    			}
    		}
    		sec++;
    	}
    	
    	System.out.println(ans == -1? "KAKTUS": ans);
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