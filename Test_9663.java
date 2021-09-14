import java.io.*;
import java.util.*;

public class Test_9663 {
	
	static int cnt;
	static int n;
	static int[] col;
	
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	n = Integer.parseInt(br.readLine());
    	
    	for(int i = 0; i < n; i++) {
    		col = new int[n];
    		col[0] = i; // i = 열 index가 행
    		dfs(0);
    	}
    	System.out.println(cnt);
    }
    
    public static void dfs(int row) {
    	if(row == n - 1) {
    		cnt++;
    	} else {
    		for(int i = 0; i < n; i++) {
    			col[row + 1] = i;
    			if(isPossible(row + 1)) {
    				dfs(row + 1);
    			} else {
    				col[row + 1] = 0;
    			}
    		}
    	}
    	col[row] = 0;
    }
    
    public static boolean isPossible(int c) {
    	// col[1]의 의미는 1행 *열이다.
        // col[1] = 1 => 1행 1열, col[2] = 3 => 2행 3열
     
    	// 이전 열들을 탐색하면서 배치 가능 여부 확인
        for (int i = 0; i < c; i++) {
        	// 같은  열
            if (col[i] == col[c]) {
                return false;
            }
            // 대각선
            if (Math.abs(col[i] - col[c]) == Math.abs(i - c)) {
                return false;
            }
        }
        return true;
    }
}

