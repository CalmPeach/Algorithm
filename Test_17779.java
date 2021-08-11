import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Test_17779 {
	
	static int n;
	static int[][] map;
	static int minDiff;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		n = Integer.parseInt(br.readLine());
		
		map = new int[n + 1][n + 1];
		for(int i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 1; j <= n; j++)
				map[i][j] = Integer.parseInt(st.nextToken());
		}
		
		minDiff = Integer.MAX_VALUE;
		// 가능한 d1, d2, x, y 모두 구하기
		for(int d1 = 1; d1 <= n; d1++) {
			for(int d2 = 1; d2 <= n; d2++) {
				for(int x = 1; x <= n; x++) {
					if(x >= x + d1 + d2 || x + d1 + d2 > n)
						continue;
					for(int y = 1; y <= n; y++) {
						if(1 > y - d1 || y - d1 >= y || y >= y + d2 || y + d2 > n)
							continue;
						divide(d1, d2, x, y);
					}
				}
			}
		}
		System.out.println(minDiff);
	}
	
	// 구역 나누기
	public static void divide(int d1, int d2, int x, int y) {
		int[][] init = {{x, y}, {x, y}, {x + d1, y - d1}, {x + d2, y + d2}};
		int[][] dir = {{1, -1}, {1, 1}, {1, 1}, {1, -1}};
		int[] range = {d1, d2, d2, d1};
		int[][] group = new int[n + 1][n + 1];
		
		// 경계선 만들기
		for(int i = 0; i < 4; i++) {
			int r = init[i][0];
			int c = init[i][1];
			int er = r + dir[i][0] * range[i] + dir[i][0];
			int ec = c + dir[i][1] * range[i] + dir[i][1];
			
			while(r != er && c != ec) {
				if(r < 1 || r > n || c < 1 || c > n)
					break;
				group[r][c] = 5;
				r += dir[i][0];
				c += dir[i][1];
			}
		}
		
		// 경계선 채우기
		for(int i = 1; i <= n; i++) {
			int sc = 0;
			for(int j = 1; j <= n; j++)
				if(group[i][j] == 5) {
					sc = j;
					break;
				}
			if(sc != 0) {
				int ec = n + 1;
				for(int j = n; j >= 1; j--)
					if(group[i][j] == 5) {
						ec = j;
						break;
					}
				
				for(int j = sc; j <= ec; j++)
					group[i][j] = 5;
			}
		}
		
		// 구역에 선거구 지정 및 해당 선거구에 인원수 더하기
		int[] cnt = new int[6];
		for(int i = 1; i <= n; i++) {
			for(int j = 1; j <= n; j++) {
				if(group[i][j] == 5) {
					cnt[5] += map[i][j];
				} else {
					int groupNum = 0;
					if(i < x + d1 && j <= y)
						groupNum = 1;
					else if(i <= x + d2 && y < j && j <= n)
						groupNum = 2;
					else if(x + d1 <= i && i <= n && j < y - d1 + d2)
						groupNum = 3;
					else
						groupNum = 4;
					group[i][j] = groupNum;
					cnt[groupNum] += map[i][j];
				}
			}
		}
		
		// 가장 인원수가 많은 선거구와 적은 선거구 구하기
		int minGroup = 1;
		int maxGroup = 1;
		for(int i = 2; i <= 5; i++) {
			if(cnt[minGroup] > cnt[i])
				minGroup = i;
			if(cnt[maxGroup] < cnt[i])
				maxGroup = i;
		}
		int diff = cnt[maxGroup] - cnt[minGroup];
		if(diff < minDiff)
			minDiff = diff;
//		print(group);
	}
	
	public static void print(int[][] arr) {
		for(int i = 1; i <= n; i++) {
			for(int j = 1; j <= n; j++)
				System.out.print(arr[i][j] + " ");
			System.out.println();
		}
	}
}