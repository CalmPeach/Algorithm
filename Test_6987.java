import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
 
public class Test_6987 {
	
	static boolean isEnd;
	static int[] game1;
	static int[] game2;
	static int[] win;
	static int[] draw;
	static int[] lose;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for(int tc = 0; tc < 4; tc++) {
			isEnd = false;
			game1 = new int[15]; // 라운드마다 경기하는 팀1를 저장
			game2 = new int[15]; // 라운드마다 경기하는 팀2를 저장
			int idx = 0;
			for(int i = 0; i < 5; i++) {
				for(int j = i + 1; j < 6; j++) {
					if(j == i)
						continue;
					game1[idx] = i;
					game2[idx] = j;
					idx++;
				}
			}
			StringTokenizer st = new StringTokenizer(br.readLine());
			win = new int[6]; // 각 팀의 이긴 횟수
			draw = new int[6]; // 각 팀의 무승부 횟수
			lose = new int[6]; // 각 팀의 패배한 횟수
			int sum = 0;
			for(int i = 0; i < 6; i++) {
				for(int j = 0; j < 3; j++) {
					int val = Integer.parseInt(st.nextToken());
					sum += val;
					switch(j) {
						case 0:
							win[i] = val;
							break;
						case 1:
							draw[i] = val;
							break;
						case 2:
							lose[i] = val;
							break;
					}
				}
			}
			
			if(sum != 30) {
				System.out.println(0);
			} else {
				solution(0);
				if(isEnd) {
					System.out.println(1);
				} else {
					System.out.println(0);
				}
			}
		}
	}
	
	public static void solution(int game) {
		if(isEnd)
			return;
		
		if(game == 15) {
			isEnd = true;
			return;
		}
		
		int c1 = game1[game];
		int c2 = game2[game];
		// c1이 승리한 경우
		if(win[c1] > 0 && lose[c2] > 0) {
			win[c1]--;
			lose[c2]--;
			solution(game + 1);
			win[c1]++;
			lose[c2]++;
		} 
		// c2가 승리한 경우
		if(lose[c1] > 0 && win[c2] > 0) {
			lose[c1]--;
			win[c2]--;
			solution(game + 1);
			lose[c1]++;
			win[c2]++;
		}
		// 무승부
		if(draw[c1] > 0 && draw[c2] > 0) {
			draw[c1]--;
			draw[c2]--;
			solution(game + 1);
			draw[c1]++;
			draw[c2]++;
		}
	}
}
