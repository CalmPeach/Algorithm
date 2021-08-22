import java.io.*;
import java.util.*;
 
public class Test_17142 {
	
	static int n;
	static int m; // 바이러스 개수
	static int wallCnt; // 벽의 개수
	static int min;
	static int[][] map;
	static int dir[][] = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	static ArrayList<Pair> virus; // 바이러스를 놓을 수 있는 위치
	
    public static void main(String[] args) {
    	Scanner scan = new Scanner(System.in);
    	
    	n = scan.nextInt();
    	m = scan.nextInt(); 
    	map = new int[n][n];
    	virus = new ArrayList<Pair>(); 
    	
    	wallCnt = 0;
    	for(int i = 0; i < n; i++)
    		for(int j = 0; j < n; j++) {
    			map[i][j] = scan.nextInt();
    			if(map[i][j] == 2)
    				virus.add(new Pair(i, j));
    			else if(map[i][j] == 1)
    				wallCnt++;
    		}
    	
    	min = Integer.MAX_VALUE;
    	solution(new int[m], m);
    	System.out.println(min == Integer.MAX_VALUE? -1: min);
    }
    
    // 조합: 바이러스를 놓을 수 있는 모든 위치에서 m개를  뽑는다
    public static void solution(int bucket[], int k) {
    	if(k == 0) {
    		int s = getTime(bucket); 
    		if(s != -1 && min > s)
    			min = s;
    		return;
    	}
    	
    	int lastIndex = bucket.length - k - 1;
    	for(int i = 0; i < virus.size(); i++) {
    		if(k == bucket.length) {
    			bucket[0] = i;
    			solution(bucket, k - 1);
    		}else if(bucket[lastIndex] < i) {
    			bucket[lastIndex + 1] = i;
    			solution(bucket, k - 1);
    		}
    	}
    }
    
    // 모든 빈칸에 바이러스가 있게 되는 최소 시간을 출력
    public static int getTime(int[] selected) {
    	Queue<Pair> q = new LinkedList<Pair>();
    	int visit[][] = new int[n][n];
    	for(int i = 0; i < selected.length; i++) {
    		Pair p = virus.get(selected[i]);
    		q.add(p);
    		visit[p.y][p.x] = 1;
    	}
    	
    	int cnt = n * n - wallCnt - virus.size(); // 빈칸 개수
    	int s = 1; // 초
    	while(!q.isEmpty()) {
    		Pair p = q.poll();
    		
    		if(cnt == 0) { // 바이러스가 없는 빈칸이 없으면 종료
    			break;
    		}
    		
    		for(int i = 0; i < 4; i++) {
    			int y = p.y + dir[i][0];
    			int x = p.x + dir[i][1];
    			if(y < 0 || y >= n || x < 0 || x >= n)
    				continue;
    			if(map[y][x] == 1 || visit[y][x] != 0)
    				continue;

    			visit[y][x] = visit[p.y][p.x] + 1;
    			cnt = map[y][x] == 0? cnt - 1: cnt;
    			s = visit[y][x] > s? visit[y][x]: s;
    			q.add(new Pair(y, x));
    		}
    	}
    	
    	// 바이러스가 있는 빈칸이 존재 
    	if(cnt != 0)
    		return -1;
    	
    	return s - 1;
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