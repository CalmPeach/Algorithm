import java.io.*;
import java.util.*;

public class Test_1405 {
	
	static int n;
	static int[][] xy = {{0, -1}, {0, 1}, {1, 0}, {-1, 0}};
	static double sum;
	static double[] per;
	static boolean[][] visit;
	static int y;
	static int x;
	
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	n = Integer.parseInt(st.nextToken());
    	per = new double[4]; // 동서남북
    	for(int i = 0; i < 4; i++)
    		per[i] = Integer.parseInt(st.nextToken()) / (double) 100;
    	
    	
    	sum = 0;
    	visit = new boolean[n * 2 + 1][n * 2 + 1];
    	visit[n][n] = true;
    	y = n;
    	x = n;
    	solution(new int[n], n);
    	System.out.println(sum);
    }
    
    public static void solution(int[] bucket, int k) {	
    	// 단순하다
    	if(k == 0) {
    		double result = per[bucket[0]];
    		for(int i = 1; i < bucket.length; i++) {
    			result = result * per[bucket[i]];
    		}
    		sum += result;
    		return;
    	}
    	
    	int lastIdx = bucket.length - k - 1;
    	
    	for(int i = 0; i < 4; i++) {
    		bucket[lastIdx + 1] = i;
    		y += xy[i][0];
    		x += xy[i][1];
    		if(!visit[y][x]) {
    			visit[y][x] = true;
    			solution(bucket, k - 1);
    			visit[y][x] = false;
    		}
    		y -= xy[i][0];
    		x -= xy[i][1];
    	}
    }
}