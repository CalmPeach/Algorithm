import java.io.*;
import java.util.*;

public class Test_16986{
	
	static int n; // 손 동작수
	static int win[][]; // 승패 판
	static int hands[]; // 손 사용 여부
	static int winCnt[]; // 각 플레이어의 우승수
	static int k ; // 승수
	static int player[][]; // 각 프렐이어의 손 동작 순서
	static boolean isEnd; // 게임 종료 여부
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		win = new int[n][n]; 
		player = new int[3][20];
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < n; j++)
				win[i][j] = Integer.parseInt(st.nextToken());
		}
		
		for(int j = 0; j < 20; j++) {
			player[0][j] = -1;
		}
		// 경희, 민호 손동작 순서
		for(int i = 1; i < 3; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < 20; j++) {
				player[i][j] = Integer.parseInt(st.nextToken()) - 1;
			}
		}
		
		isEnd = false;
		solution(n);
		System.out.println(isEnd == true? 1: 0);
	}
	
	// 지우의 손동작 순열 구하는 메서드
	public static void solution(int num) { 	
		if(isEnd)
			return;
		
		if(num == 0) {
			isEnd = false;
			hands = new int[3];
			winCnt = new int[3];
			
			if(simulation(0, 1)) {
				isEnd = true;
			}
			
			return;
		}
		
		int lastIndex = n - num - 1;
		for(int i = 0; i < n; i++) {
			int flag = 0;
			for(int j = 0; j <= lastIndex; j++) {
				if(player[0][j] == i) {
					flag = 1;
					break;
				}
			}
			if(flag == 1)
				continue;
			
			player[0][lastIndex + 1] = i;
			solution(num - 1);
		}
	}
	
	// p1: 플레이어 번호, p2: 플레이어 번호 
	public static boolean simulation(int p1, int p2) {
		boolean isWinner = false; // 지우가 우승했는지 여부를 나타냄
		while(true) {
			// 지우가 우승
			if(winCnt[0] >= k) {
				isWinner = true;
				break;
			}
			
			// 지우 모든 손동작 사용 혹은 경희 우승 혹은 민호 우승
			if(hands[0] >= n || winCnt[1] >= k || winCnt[2] >= k) 
				break;
			
			int winner = findWinner(p1, p2, player[p1][hands[p1]], player[p2][hands[p2]]);
			int nextPlayer = findPlayer(p1, p2);
			winCnt[winner]++;
			hands[p1]++;
			hands[p2]++;
			p1 = winner;
			p2 = nextPlayer;
		}
		return isWinner;
	}
	
	// 우승자 찾기 
	public static int findWinner(int p1, int p2, int p1_n, int p2_n) {
		int r = win[p1_n][p2_n];
		if(r == 2)
			return p1;
		else if(r == 0)
			return p2;
		else 
			return p1 < p2? p2: p1; // 비긴 경우 뒷 순서가 우승자
	}
	
	// 다음 플레이어 찾기
	public static int findPlayer(int p1, int p2) {
		int nums[] = new int[3];
		nums[p1]++;
		nums[p2]++;
		int i;
		for(i = 0; i < 3; i++)
			if(nums[i] == 0)
				break;
		return i;
	}
	
}