import java.io.*;
import java.util.*;
 
public class Test_13460 {
	
	static int[][] xy = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		char[][] map = new char[n][m];
		Pair[] location = new Pair[2]; // 초기 빨간 구슬과 파란 구슬 위치
		Pair ans = null; // 구멍 위치 
		for(int i = 0; i < n; i++) {
			String input = br.readLine();
			for(int j = 0; j < m; j++) {
				map[i][j] = input.charAt(j);
				if(map[i][j] == 'R') {
					location[0] = new Pair(i, j);
				} else if(map[i][j] == 'B') {
					location[1] = new Pair(i, j);
				} else if(map[i][j] == 'O') {
					ans = new Pair(i, j);
				}
			}
		}
		System.out.println(bfs(new PairComb(location[0], location[1]), n, m, map, ans));
	}
	
	public static int bfs(PairComb node, int n, int m, char[][] map, Pair ans) {
		Queue<PairComb> q = new LinkedList<PairComb>();
		q.add(node);
		boolean[][][][] visit = new boolean[10][10][10][10];
		visit[node.red.r][node.red.c][node.blue.r][node.blue.c] = true;
		int cnt = 1; // 구슬을 움직인 횟수
		boolean flag = false; // 빨간 구슬이 구멍을 통해 빠져나갔는지 여부
		
		loop:
		while(!q.isEmpty()) {
			if(cnt == 11) // 10번 이하로 움직여서 빨간 구슬을 구멍을 통해 빼낼 수 없으면 -1 
				break;
			
			int size = q.size();
			for(int j = 0; j < size; j++) {
				PairComb now = q.poll();
				
				for(int i = 0; i < 4; i++) {
					Pair red = now.red;
					Pair blue = now.blue;
					Pair nextRed = new Pair(red.r, red.c);
					Pair nextBlue = new Pair(blue.r, blue.c);
					
					move(nextRed, map, i);
					move(nextBlue, map, i);
					
					if(map[nextBlue.r][nextBlue.c] == 'O') // 파란구슬이 빠졌다면 실패 
						continue;
					else if (map[nextRed.r][nextRed.c] == 'O'){
						flag = true;
						break loop;
					}
					
					reassign(red, blue, nextRed, nextBlue, i);
					
					if(visit[nextRed.r][nextRed.c][nextBlue.r][nextBlue.c] == false)
						q.add(new PairComb(nextRed, nextBlue));
				}
			}
			cnt++;
		}
		return flag? cnt: -1;
	}
	
	public static void reassign(Pair nowRed, Pair nowBlue, Pair nextRed, Pair nextBlue, int d) {
		if(nextRed.r == nextBlue.r && nextRed.c == nextBlue.c) { // 이동 후 빨간구슬과 파란구슬의 위치가 같다면 재배정 필요
			switch(d) {
				case 0:
					if(nowRed.r < nowBlue.r)
						nextBlue.r += 1;
					else
						nextRed.r += 1;
					break;
				case 1:
					if(nowRed.r < nowBlue.r)
						nextRed.r -= 1;
					else
						nextBlue.r -= 1;
					break;
				case 2:
					if(nowRed.c < nowBlue.c)
						nextBlue.c += 1;
					else
						nextRed.c += 1;
					break;
				case 3:
					if(nowRed.c < nowBlue.c)
						nextRed.c -= 1;
					else
						nextBlue.c -= 1;
					break;
			}
		}
	}
	
	public static void move(Pair now, char[][] map, int d) {
		while(map[now.r + xy[d][0]][now.c + xy[d][1]] != '#') { // 구슬을 벽까지 이동 
			now.r += xy[d][0];
			now.c += xy[d][1];
			if(map[now.r][now.c] == 'O')
				break;
		}
	}
	
	public static class PairComb{
		Pair red;
		Pair blue;
		public PairComb(Pair red, Pair blue) {
			this.red = red;
			this.blue = blue;
		}
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