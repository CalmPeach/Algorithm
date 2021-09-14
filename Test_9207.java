import java.io.*;
import java.util.*;

public class Test_9207 {
	
	static int[][] xy = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	static int[][] xy2 = {{-2, 0}, {2, 0}, {0, -2}, {0, 2}};
	static int min;
	static int cnt;
	
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	int tc = Integer.parseInt(br.readLine());
    	for(int t = 0; t < tc; t++) {
    		int pinCnt = 0;
    		char[][] map = new char[5][9];
    		for(int i = 0; i < 5; i++) {
    			String input = br.readLine();
    			for(int j = 0; j < 9; j++) {
    				map[i][j] = input.charAt(j);
    				if(map[i][j] == 'o')
    					pinCnt++;
    			}
    		}
    		min = Integer.MAX_VALUE;
    		cnt = Integer.MAX_VALUE;
    		dfs(map, pinCnt, 0);
    		System.out.println(min + " " + cnt);
    		br.readLine();
    	}
    }
    
    public static void dfs(char[][] map, int pinCnt, int moveCnt) {
    	boolean isEnd = true;
    	
    	for(int i = 0; i < 5; i++) {
    		for(int j = 0; j < 9; j++) {
    			if(map[i][j] != '#' && map[i][j] != '.') {
    				ArrayList<Integer> result = check(new Pair(i, j), map);
    				for(int k = 0; k < result.size(); k++) {
    					int d = result.get(k);
    					if(d == 4)
    						continue;
    					int y = i + xy2[d][0];
    					int x = j + xy2[d][1];
    					if(y < 0 || y >= 5 || x < 0 || x >= 9)
    						continue;
    					if(map[y][x] != '.')
    						continue;
    					isEnd = false;
    					map[y][x] = 'o';
    					map[i][j] = '.';
    					map[i + xy[d][0]][j + xy[d][1]] = '.';
    					dfs(map, pinCnt - 1, moveCnt + 1);
    					map[y][x] = '.';
    					map[i][j] = 'o';
    					map[i + xy[d][0]][j + xy[d][1]] = 'o';
    				}
    			}
    		}
    	}
    	
    	if(isEnd) {
    		if(min > pinCnt) {
    			min = pinCnt;
    			cnt = moveCnt;
    		} else if(min == pinCnt) {
    			if(cnt > moveCnt)
    				cnt = moveCnt;
    		}
    	}
    }
    
    // 나한테 인접한 애가 있는지 확인 
    public static ArrayList<Integer> check(Pair pair, char[][] map) {
    	ArrayList<Integer> closest = new ArrayList<Integer>();
    	for(int i = 0; i < 4; i++) {
    		int y = pair.y + xy[i][0];
    		int x = pair.x + xy[i][1];
    		if(y < 0 || y >= 5 || x < 0 || x >= 9)
    			continue;
    		if(map[y][x] != '#' && map[y][x] != '.')
    			closest.add(i);
    	}
    	if(closest.isEmpty())
    		closest.add(4);
    	return closest;
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

