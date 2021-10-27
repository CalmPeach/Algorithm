import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import javax.print.attribute.HashAttributeSet;

public class Test_14499 {
	
	// 동, 서, 남, 북 순으로 이동
	static int[] dr = {0, 0, -1, 1};
	static int[] dc = {1, -1, 0, 0};
	static int[][] diceMove = {{3, 1, 0, 5, 4, 2},
					{2, 1, 5, 0, 4, 3},
					{4, 0, 2, 3, 5, 1},
					{1, 5, 2, 3, 0, 4}}; // 동으로 주사위를 굴릴 경우 3번칸이 0번칸이 된다
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken()); // 지도의 세로크기
		int m = Integer.parseInt(st.nextToken()); // 지도의 가로크기
		int x = Integer.parseInt(st.nextToken()); // 주사위 좌표 x
		int y = Integer.parseInt(st.nextToken()); // 주사위 좌표 y
		int k = Integer.parseInt(st.nextToken()); // 명령의 개수
		
		int[][] map = new int[n][m];
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < m; j++)
				map[i][j] = Integer.parseInt(st.nextToken());
		}
		
		int[] dice = new int[6]; // 주사위
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < k; i++) {
			int c = Integer.parseInt(st.nextToken()) - 1; // 명령어
			
			int nr = x + dr[c];
			int nc = y + dc[c];
			if(nr < 0 || nr >= n || nc < 0 || nc >= m)
				continue;
			
			int tmp = dice[diceMove[c][0]]; // 0번칸에 들어갈 값
			int idx = diceMove[c][0]; // 0번칸에 들어갈 칸의 번호
			while(idx != 0) {
				dice[idx] = dice[diceMove[c][idx]];
				idx = diceMove[c][idx];
			}
			dice[0] = tmp;
			
			if(map[nr][nc] == 0) { // 지도의 칸에 정수가 쓰여있지 않다면
				map[nr][nc] = dice[5]; // 주사위 바닥면이 복사
			} else {
				dice[5] = map[nr][nc]; // 주사위 바닥면에 지도에 쓰여 있는 수가 복사
				map[nr][nc] = 0; // 지도에 쓰여 있는 수 지워짐
			}
			
			x = nr;
			y = nc;
			System.out.println(dice[0]);
		}
	}
	
}