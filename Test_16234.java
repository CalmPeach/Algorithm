import java.io.*;
import java.util.*;
 
public class Test_16234 {
	
	static int n;
	static int l;
	static int r;
	static int map[][];
	static int visit[][];
	
    public static void main(String[] args) {
    	Scanner scan = new Scanner(System.in);
    	
    	n = scan.nextInt(); 
    	l = scan.nextInt(); // l 이상
    	r = scan.nextInt(); // r 이하
    	map = new int[n][n];
    	for(int i = 0; i < n; i++)
    		for(int j = 0; j < n; j++)
    			map[i][j] = scan.nextInt();
    	
    	int moveCnt = 0; // 인구 이동이 며칠 동안 발생하는지
    	while(true) {
	    	int step = 1;
	    	visit = new int[n][n];
	    	HashMap<Integer, Result> hash = new HashMap<Integer, Result>(); // 연합 번호, 연합의 인원수, 연합을 이루고 있는 칸의 개수 
	    	for(int i = 0; i < n; i++) {
	    		for(int j = 0; j < n; j++) {
	    			if(visit[i][j] == 0) {
	    				Result r = solution(new Pair(i, j), step);
	    				hash.put(step, r);
	    				step++;
	    			}
	    		}
	    	}
	    	
	    	if(step == n * n + 1) // 인구 이동이 없다면
	    		break;
	    	
	    	// 인구이동 
	    	for(int i = 0; i < n; i++) {
	    		for(int j = 0; j < n; j++) {
	    			Result r =  hash.get(visit[i][j]);
	    			map[i][j] = r.sum / r.cnt;
	    		}
	    	}
	    	
	    	moveCnt++;
    	}
    	
    	System.out.println(moveCnt);
    }
    
    // 연합의 인원수와 연합을 이루고 있는 칸의 개수를 구하는 메서드
    public static Result solution(Pair start, int v) {
    	Queue<Pair> q = new LinkedList<Pair>();
    	q.add(start);
    	visit[start.y][start.x] = v;
    	int sum = map[start.y][start.x];
    	int cnt = 1;
    	
    	while(!q.isEmpty()) {
    		Pair p = q.poll();
    		
    		int closest[][] = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    		for(int i = 0; i < 4; i++) {
    			int y = p.y + closest[i][0];
    			int x = p.x + closest[i][1];
    			
    			if(y < 0 || y >= n || x < 0 || x >= n)
    				continue;
    			if(visit[y][x] == 0) {
    				int diff = abs(map[p.y][p.x], map[y][x]);
    				if(diff >= l && diff <= r) {
    					visit[y][x] = v;
    					q.add(new Pair(y, x));
    					sum += map[y][x];
    					cnt++;
    				}
    			}
    		}
    	}

    	return new Result(sum, cnt);
    }
    
    public static int abs(int n1, int n2) {
    	if(n1 > n2)
    		return n1 - n2;
    	return n2 - n1;
    }
    
    public static class Result{
    	int sum;
    	int cnt;
    	public Result(int sum, int cnt) {
    		this.sum = sum;
    		this.cnt = cnt;
    	}
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