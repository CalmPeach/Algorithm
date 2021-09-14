import java.io.*;
import java.util.*;

public class Test_3190 {
	
	static int[][] xy = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
	static int[][] map;
	static int[][] change;
	static int n;
	
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
    	n = Integer.parseInt(br.readLine()); // 보드 크기
    	int k = Integer.parseInt(br.readLine()); // 사과 개수
    	map = new int[n][n];
    	for(int i = 0; i < k; i++) {
    		StringTokenizer st = new StringTokenizer(br.readLine());
    		int row = Integer.parseInt(st.nextToken()) - 1;
    		int col = Integer.parseInt(st.nextToken()) - 1;
    		map[row][col] = 1; // 사과
    	}
    	int l = Integer.parseInt(br.readLine());
    	change = new int[l][2];
    	for(int i = 0; i < l; i++) {
    		StringTokenizer st = new StringTokenizer(br.readLine());
    		change[i][0] = Integer.parseInt(st.nextToken());
    		// L : 0 D : 1
    		change[i][1] = st.nextToken().equals("L")? 0: 1;
    	}
    	
    	solution();
    }
 
    public static void solution() {
    	// 처음 방향 오른쪽 설정
    	int d = 0;
    	// 변경이 일어날 초를 가리키는 인덱스
    	int secIdx = 0;
    	map[0][0] = 2; //��
    	Queue<Pair> q = new LinkedList<Pair>(); // 뱀 머리
    	q.add(new Pair(0, 0, 0)); 
    	Deque<Pair> dq = new ArrayDeque<Pair>(); // 뱀 전부
    	dq.add(new Pair(0, 0, 0));
    	int ans = 0;
    	
    	while(!dq.isEmpty()) {
    		Pair now = q.poll(); //머리를 뺌
    		
    		int y = now.y + xy[d][0];
    		int x = now.x + xy[d][1];
    		if(y < 0 || y >= n || x < 0 || x >= n || map[y][x] == 2) { // 벽 또는 자기 자신과 부딪히면
    			ans = now.sec + 1;
    			break;
    		}
    		
    		if(map[y][x] == 0) { // 이동한 칸에 사과가 없다면
    			Pair p = dq.peekLast();
    			map[p.y][p.x] = 0; // 꼬리가 위치한 칸을 비워줌
    			dq.pollLast();
    		}
    		map[y][x] = 2; // 뱀의 몸길이가 존재
    		if(secIdx < change.length && change[secIdx][0] == now.sec + 1) { // x초가 끝난 뒤에 90도 방향으로 회전
    			if(change[secIdx][1] == 0) { // 왼쪽으로 회전
    				d = d - 1 < 0? 3: d - 1;
    			} else { // 오른쪽으로 회전
    				d = d + 1 > 3? 0: d + 1;
    			}
    			secIdx++;
    		}
    		dq.addFirst(new Pair(y, x, now.sec + 1));
    		q.add(new Pair(y, x, now.sec + 1));
    	}
    	
    	System.out.println(ans);
    }
    
    public static class Pair{
    	int y;
    	int x;
    	int sec;
    	public Pair(int y, int x, int sec) {
    		this.y = y;
    		this.x = x;
    		this.sec = sec;
    	}
    }
}