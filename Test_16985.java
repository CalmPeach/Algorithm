import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
 
public class Test_16985 {
	
	static int[][][] map;
	static int min;
	static int[][] xyz = {{-1, 0, 0}, {1, 0, 0}, {0, -1, 0}, {0, 1, 0}, {0, 0, -1}, {0, 0, 1}};
	
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        map = new int[5][5][5];
        for(int i = 0; i < 5; i++) {
        	for(int j = 0; j < 5; j++) {
        		StringTokenizer st = new StringTokenizer(br.readLine());
        		for(int k = 0; k < 5; k++) {
        			map[i][j][k] = Integer.parseInt(st.nextToken());
        		}
        	}
        }
        min = Integer.MAX_VALUE;
        rotateSolution(new int[5], 5);
        System.out.println(min == Integer.MAX_VALUE? -1: min);
    }
    
    // 입구에서 출구까지 최소경로 찾는 메서드
    public static int bfs(int[][][] mapClone) {
    	// 입구와 출구가 막혀있는 경우
    	if(mapClone[0][0][0] == 0 || mapClone[4][4][4] == 0)
    		return -1;
    	
    	Queue<Pair> q = new LinkedList<Pair>();
    	q.add(new Pair(0, 0, 0));
    	int[][][] visit = new int[5][5][5];
    	visit[0][0][0] = 1;
    	
    	while(!q.isEmpty()) {
    		Pair p = q.poll();
    		if(p.z == 4 && p.y == 4 && p.x == 4)
    			break;
    		for(int i = 0; i < xyz.length; i++) {
    			int z = p.z + xyz[i][0];
    			int y = p.y + xyz[i][1];
    			int x = p.x + xyz[i][2];
    			if(z < 0 || z >= 5 || y < 0 || y >= 5 || x < 0 || x >= 5)
    				continue;
    			if(mapClone[z][y][x] == 0 || visit[z][y][x] != 0)
    				continue;
    			visit[z][y][x] = visit[p.z][p.y][p.x] + 1;
    			q.add(new Pair(z, y, x));
    		}
    	}
    	return visit[4][4][4] - 1;
    }
    
    // 쌓는 순서 순열
    public static void stepSolution(int[][][] mapClone, int[] bucket, int k) {
    	if(k == 0) {
    		int[][][] mapClone2 = new int[5][5][5];
    		for(int i = 0; i < 5; i++) {
    			mapClone2[i] = mapClone[bucket[i]];
    		}
    		int cnt = bfs(mapClone2);
    		if(cnt != -1 && min > cnt)
    			min = cnt;
    		return;
    	}
    	
    	int lastIndex = bucket.length - k - 1;
    	for(int i = 0; i < 5; i++) {
    		int flag = 0;
    		for(int j = 0; j <= lastIndex; j++) {
    			if(i == bucket[j]) {
    				flag = 1;
    				break;
    			}
    		}
    		if(flag == 1)
    			continue;
    		bucket[lastIndex + 1] = i;
    		stepSolution(mapClone, bucket, k - 1);
    	}
    }
    
    // 판들을 돌리는 중복순열
    public static void rotateSolution(int[] bucket, int k) {
    	if(k == 0) {
    		int[][][] mapClone = new int[5][5][5];
    		for(int i = 0; i < bucket.length; i++) {
    			int[][] arr = rotateArr(i, bucket[i]);
    			mapClone[i] = arr;
    		}
    		stepSolution(mapClone, new int[5], 5);
    		return;
    	}
    	
    	int lastIndex = bucket.length - k - 1;
    	for(int i = 0; i < 4; i++) {
    		bucket[lastIndex + 1] = i;
    		rotateSolution(bucket, k - 1);
    	}
    }
    
    // 판을 돌리는 메서드 (step: 층, c: 방향)
    public static int[][] rotateArr(int step, int c) {
    	int[][] arr = new int[5][5];
    	if(c == 1) { // 오른쪽 90도
    		for(int i = 0; i < 5; i++) {
    			for(int j = 0; j < 5; j++) {
    				arr[i][j] = map[step][j][4 - i];
    			}
    		}
    	} else if(c == 2) { // 오른쪽 180도
    		for(int i = 0; i < 5; i++) {
    			for(int j = 0; j < 5; j++) {
    				arr[i][j] = map[step][4 - i][4 - j];
    			}
    		}
    	} else if(c == 3) { // 오른쪽 270도
    		for(int i = 0; i < 5; i++) {
    			for(int j = 0; j < 5; j++) {
    				arr[i][j] = map[step][4 - j][i];
    			}
    		}
    	} else { // 오른쪽 360도
    		for(int i = 0; i < 5; i++) {
    			for(int j = 0; j < 5; j++) {
    				arr[i][j] = map[step][i][j];
    			}
    		}
    	}
    	return arr;
    }
    
    public static class Pair{
    	int z;
    	int y;
    	int x;
    	public Pair(int z, int y, int x) {
    		this.z = z;
    		this.y = y;
    		this.x = x;
    	}
    }
}