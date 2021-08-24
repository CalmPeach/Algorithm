import java.io.*;
import java.util.*;
 
public class Test_3109 {
	
	static int n;
	static int m;
	static int[][] xy = {{-1, 1}, {0, 1}, {1, 1}};
	static int cnt;
	static int[][] map;
	
    public static void main(String[] args) throws NumberFormatException, IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	n = Integer.parseInt(st.nextToken());
    	m = Integer.parseInt(st.nextToken());
    	map = new int[n][m];
    	for(int i = 0; i < n; i++) {
    		String s = br.readLine();
    		for(int j = 0; j < m; j++) {
    			if(s.charAt(j) == 'x')
    				map[i][j] = 1;
    		}
    	}
    	
    	cnt = 0;
    	for(int i = 0; i < n; i++) {
    		dfs(i, 0);
    	}
    	System.out.println(cnt);
    }
    
    public static boolean dfs(int r, int c) {
    	if(c == m - 1) {
    		cnt++;
    		return true;
    	}
    		
    	for(int i = 0; i < 3; i++) {
    		int y = r + xy[i][0];
    		int x = c + xy[i][1];
    		
    		if(y >= n || y < 0 || x >= m || x < 0 || map[y][x] != 0)
    			continue;
    		map[y][x] = 2; // 방문여부 체크
    		if(dfs(y, x)) // 가스관에 연결을 완료했으면 return
    			return true;
    	}
    	return false;
    }
}   