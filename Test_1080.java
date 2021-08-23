import java.io.*;
import java.util.*;
 
public class Test_1080 {
	
	static int n;
	static int m;
	static int[][] mapB;
	
    public static void main(String[] args) throws NumberFormatException, IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer s = new StringTokenizer(br.readLine());
    	n = Integer.parseInt(s.nextToken());
    	m = Integer.parseInt(s.nextToken());
    	int[][] mapA = new int[n][m]; // 행렬 A
    	mapB = new int[n][m]; // 행렬 B
    	
    	for(int i = 0; i < n; i++) {
    		String str = br.readLine();
    		for(int j = 0; j < m; j++) {
    			mapA[i][j] = str.charAt(j) - '0';
    		}
    	}
    	
    	for(int i = 0; i < n; i++) {
    		String str = br.readLine();
    		for(int j = 0; j < m; j++) {
    			mapB[i][j] = str.charAt(j) - '0';
    		}
    	}
    	
    	int min = solution(mapA);
     	System.out.println(min == Integer.MAX_VALUE? -1 : min);
    }
    
    public static int solution(int[][] mapA) {
    	int ans = 0;
    	loop:
    	for(int i = 0; i < n; i++) {
    		for(int j = 0; j < m; j++) {
    			if(mapA[i][j] != mapB[i][j]) { // 서로 다르면 행렬 변환
    				if(i + 3 > n || j + 3 > m) { // 더이상 변환을 진행할 수 없는데 서로 다름
    					ans = -1;
    					break loop;
    				}
    				change(mapA, i, j);
    				ans++;
    			}
    		}
    	}
    	
    	return ans;
    }
    
    public static void change(int map[][], int y, int x) {
    	for(int i = 0; i < 3; i++) {
    		for(int j = 0; j < 3; j++) {
    			map[y + i][x + j] = map[y + i][x + j] == 0? 1: 0;
    		}
    	}
    }
}
