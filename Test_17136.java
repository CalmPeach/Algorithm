import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Test_17136 {
	
	static int[] cnts; // 크기별 색종이 수 
	static int[][] map; // 10 x 10 종이
	static int result;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		map = new int[10][10];
		for(int i = 0; i < 10; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < 10; j++)
				map[i][j] = Integer.parseInt(st.nextToken());
		}
		
		cnts = new int[5];
		Arrays.fill(cnts, 5);
		result = Integer.MAX_VALUE;
		
		solution(0, 0);
		System.out.println(result == Integer.MAX_VALUE? -1: result);
	}
	
	// 위치, 사용한 색종이 수
	public static void solution(int index, int cnt) {
		// 마지막 칸까지 도달함 
		if(index >= 100) {
			if(result > cnt)
				result = cnt;
			return;
		}
		
		// 현재 사용한 색종이 수가 색종이 최소 개수보다 큰 경우 더 이상 탐색할 필요 없다.
		if(cnt >= result)
			return;
		
		int i = index / 10;
		int j = index % 10;
		
		if(map[i][j] == 1) {
			for(int k = cnts.length - 1; k >= 0; k--) {
				// (k + 1) x (k + 1) 색종이의 남은 개수가 0보다 큰 경우 색종이를 사용할 수 있다.
				if(cnts[k] > 0) {
					if(isCheck(i, j, k)) {
						fill(i, j, k, 0);
						cnts[k]--;
						solution(index + 1, cnt + 1);
						cnts[k]++;
						fill(i, j, k, 1);
					}
				}
			}
		}
		else {
			solution(index + 1, cnt);
		}
	}
	
	// 색종이로 덮을 수 있는지 확인
	public static boolean isCheck(int i, int j, int plus) {
		if(i + plus >= 10 || j + plus >= 10)
			return false;
		
		for(int m = 0; m <= plus; m++)
			for(int n = 0; n <= plus; n++)
				if(map[i + m][j + n] == 0)
					return false;
		
		return true;
	}
	
	// 색종이로 종이 덮기 
	public static void fill(int i, int j, int step, int f_n) {
		for(int m = 0; m <= step; m++)
			for(int n = 0; n <= step; n++)
				map[i + m][j + n] = f_n;
	}
	
}