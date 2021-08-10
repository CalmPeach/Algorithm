import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
 
public class Test_16946 {
	
	static int n;
	static int m;
	static int[][] map;
	static int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        for(int i = 0; i < n; i++) {
        	String input = br.readLine();
        	for(int j = 0; j < m; j++) {
        		map[i][j] = input.charAt(j) - '0';
        	}
        }
        
        // 구역의 번호와 개수를 저장하기 위한 HashMap
        HashMap<Integer, Integer> group = new HashMap<Integer, Integer>();
        int groupNum = 1;
        for(int i = 0; i < n; i++) {
        	for(int j = 0; j < m; j++) {
        		if(map[i][j] == 0) {
        			groupNum++;
        			int cnt = bfs(new Pair(i, j), groupNum);
        			group.put(groupNum, cnt);
        		}
        	}
        }
        
        StringBuilder result = new StringBuilder();
        for(int i = 0; i < n; i++) {
        	for(int j = 0; j < m; j++) {
        		// 해당 위치가 벽이라면
        		if(map[i][j] == 1) {
        			int total = 0; // 벽을 부수고 이동할 수 있는 칸의 개수 
        			Set<Integer> g = new HashSet<Integer>(); // 포함된 그룹을 저장하는 Set
        			for(int k = 0; k < 4; k++) {
        				int y = i + dir[k][0];
        				int x = j + dir[k][1];
        				if(y < 0 || y >= n || x < 0 || x >= m)
            				continue;
            			if(map[y][x] == 1)
            				continue;
            			if(g.contains(map[y][x]))
            				continue;
            			total += group.get(map[y][x]);
            			g.add(map[y][x]);
        			}
        			result.append((total + 1) % 10 + "");
        		} else {
        			result.append("0");
        		}
        	}
        	result.append("\n");
        }
        System.out.println(result);
    }
    
    // 구역의 개수를 알아오는 BFS
    public static int bfs(Pair s, int v) {
    	Queue<Pair> q = new LinkedList<Pair>();
    	q.add(s);
    	map[s.r][s.c] = v;
    	int cnt = 1;
    	
    	while(!q.isEmpty()) {
    		Pair p = q.poll();
    		
    		for(int i = 0; i < 4; i++) {
    			int y = p.r + dir[i][0];
    			int x = p.c + dir[i][1];
    			if(y >= 0 && y < map.length
						&& x >= 0 && x < map[0].length) {
					if(map[y][x] == 0) {
						map[y][x] = map[p.r][p.c];
						q.add(new Pair(y, x));
						cnt++;
					}
				}
    		}
    	}
    	return cnt;
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