import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Test_15684 {
	
	static int n, h;
	static boolean isEnd;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken()); // 세로선
		int m = Integer.parseInt(st.nextToken()); // 이미 놓여진 가로선의 개수
		h = Integer.parseInt(st.nextToken()); // 가로선
		int[][] ladder = new int[h + 1][n + 1];
		
		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			ladder[a][b] = b + 1;
			ladder[a][b + 1] = b;
		}
		
		// 조합
		int k = 0;
		for(k = 0; k <= 3; k++) {
			combination(ladder, k);
			if(isEnd)
				break;
		}
		System.out.println(k == 4? -1: k);
	}
	
	public static void combination(int[][] ladder, int k) {
		if(isEnd)
			return;
		
		if(k == 0) {
			// 연결 확인
			isEnd = isCheck(ladder);
			return;
		}
		
		for(int i = 1; i <= h; i++) {
			for(int j = 1; j < n; j++) {
				if(ladder[i][j] == 0 && ladder[i][j + 1] == 0) {
					ladder[i][j] = j + 1;
					ladder[i][j + 1] = j;
					combination(ladder, k - 1);
					if(isEnd)
						return;
					ladder[i][j] = 0;
					ladder[i][j + 1] = 0;
				}
			}
		}
	}
	
	public static boolean isCheck(int[][] ladder) {
		for(int l = 1; l <= n; l++) {
			int j = l;
			for(int i = 1; i <= h; i++) {
				if(ladder[i][j] != 0)
					j = ladder[i][j];
			}
			if(j != l)
				return false;
		}
		return true;
	}
}