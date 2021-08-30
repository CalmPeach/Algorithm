import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.*;
 
public class Test_1022 {
	
	static int n;
	static int m;
	static int size;
	static int num;
	static int plus;
	static int maxNum;
	static int cnt;
	static int[][] map;
	static int[] dr = {0, -1, 0, 1};
	static int[] dc = {1, 0, -1, 0};
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int r1 = Integer.parseInt(st.nextToken()); // 가장 왼쪽 위칸
		int c1 = Integer.parseInt(st.nextToken());
		int r2 = Integer.parseInt(st.nextToken()); // 가장 오른쪽 아래칸
		int c2 = Integer.parseInt(st.nextToken());
		n = r2 - r1 + 1; // 가로길이
		m = c2 - c1 + 1; // 세로 길이
		map = new int[n][m];
		Pair s = new Pair(0 - r1, 0 - c1); // 0, 0 위치
		solution(s);
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) {
				String num = Integer.toString(map[i][j]);
				if(size > num.length()) {
					for(int k = 0; k < size - num.length(); k++)
						System.out.print(" ");
				}
				System.out.print(num + " ");
			}
			System.out.print("\n");
		}
	}
	
	public static void solution(Pair s) {
		cnt = n * m; // 채워야하는 칸의 개수 
		num = 1; // 칸에 들어갈 번호 
		if(checkRange(s)) {
			map[s.y][s.x] = num;
			cnt--;
		}
		
		plus = 1; // 반복 횟수  
		maxNum = 1; // 제일 큰 숫자
		while(cnt > 0) {
			move(s);
		}
		
		size = Integer.toString(maxNum).length();
	}
	
	public static void move(Pair now) {
		for(int d = 0; d < 4; d++) {
			for(int i = 1; i <= plus; i++) {
				now.y += dr[d];
				now.x += dc[d];
				num++;
				if(checkRange(now)) {
					map[now.y][now.x] = num;
					if(maxNum < map[now.y][now.x])
						maxNum = map[now.y][now.x];
					cnt--;
				}
			}
			
			if(d % 2 != 0)
				plus++;
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
	
	public static boolean checkRange(Pair p) {
		if(p.y < 0 || p.y >= n || p.x < 0 || p.x >= m)
			return false;
		return true;
	}
}
