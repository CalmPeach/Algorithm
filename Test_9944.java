import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
 
public class Test_9944 {
	
	static int n, m;
	static char[][] map;
	static int min;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = br.readLine();
		int t = 1;
		StringBuilder result = new StringBuilder();
		while(input != null) {
			StringTokenizer st = new StringTokenizer(input);
			
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			map = new char[n][m];
			int cnt = 0;
			for(int i = 0; i < n; i++) {
				String str = br.readLine();
				for(int j = 0; j < m; j++) {
					map[i][j] = str.charAt(j);
					if(map[i][j] == '*')
						cnt++;
				}
			}
			
			min = Integer.MAX_VALUE;
			for(int i = 0; i < n; i++) {
				for(int j = 0; j < m; j++) {
					if(map[i][j] == '.') { // 빈칸이라면 공 놓기 가능
						boolean[][] visit = new boolean[n][m];
						visit[i][j] = true;
						dfs(i, j, 0, n * m - cnt - 1, visit, 0);
					}
				}
			}
			result.append("Case " + t + ": " + (min == Integer.MAX_VALUE? -1: min) + "\n");
			t++;
			input = br.readLine();
		}
		System.out.println(result.toString());
	}
	
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	public static void dfs(int r, int c, int cnt, int emptyCnt, boolean[][] visit, int depth) {
		if(cnt > min) // 이동횟수의 최솟값보다 이동횟수가 크다면 return
			return;
		
		boolean isMove = false;
		for(int i = 0; i < 4; i++) {
			int tmpR = r;
			int tmpC = c;
			int nr = r + dr[i];
			int nc = c + dc[i];
			if(!isCheck(nr, nc, visit))
				continue;
			int visitCnt = 0;
			while(isCheck(nr, nc, visit)) { // 이동할 수 있을때까지 이동
				tmpR = nr;
				tmpC = nc;
				visitCnt++;
				visit[tmpR][tmpC] = true;
				nr += dr[i];
				nc += dc[i];
			}
			isMove = true;
			dfs(tmpR, tmpC, cnt + 1, emptyCnt - visitCnt, visit, depth + 1);
			
			// visit을 copy해서 dfs로 보내지 않고 변경된 부분을 지워줬을 때 시간이 더 빠르다.
			int d = i % 2 == 0? i + 1: i - 1;
			while(tmpR != r || tmpC != c) {
				visit[tmpR][tmpC] = false;
				tmpR += dr[d];
				tmpC += dc[d];
			}
		}
		
		if(!isMove && emptyCnt == 0) { // 더이상 빈칸이 없고 이동할 곳이 없다면 min 갱신
			min = min > cnt? cnt: min;
		}
	}
	
	public static boolean isCheck(int r, int c, boolean[][] visit) {
		if(r < 0 || r >= n || c < 0 || c >= m || visit[r][c] || map[r][c] == '*')
			return false;
		return true;
	}
}